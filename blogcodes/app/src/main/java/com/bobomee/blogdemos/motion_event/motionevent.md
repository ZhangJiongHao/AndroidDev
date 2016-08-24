## 事件分发机制
---


首先,`ViewGroup` 会调用 `dispatchTouchEvent` 方法,在其方法中对事件进行分发,

`ViewGroup` 的 `dispatchTouchEvent`中调用了 `onInterceptTouchEvent`

```java
public boolean dispatchTouchEvent(MotionEvent ev) {
    //...
    boolean disallowIntercept = (mGroupFlags & FLAG_DISALLOW_INTERCEPT) != 0;
    if (action == MotionEvent.ACTION_DOWN) {
        //...
        if (disallowIntercept || !onInterceptTouchEvent(ev)) {
            //...
                        if (child.dispatchTouchEvent(ev))  {
                            mMotionTarget = child;
                            return true;
                        }
        }
    }
    //...
    if (target == null) {
        ev.setLocation(xf, yf);
       //ViewGroup没有拦截
       //调用View的dispatchTouchEvent
        return super.dispatchTouchEvent(ev);
    }
    if (!disallowIntercept && onInterceptTouchEvent(ev)) {
        final float xc = scrolledXFloat - (float) target.mLeft;
        final float yc = scrolledYFloat - (float) target.mTop;
        mPrivateFlags &= ~CANCEL_NEXT_UP_EVENT;
        ev.setAction(MotionEvent.ACTION_CANCEL);
        ev.setLocation(xc, yc);
        if (!target.dispatchTouchEvent(ev)) {
        }
        mMotionTarget = null;
        return true;
    }
    if (isUpOrCancel) {
        mMotionTarget = null;
    }
    final float xc = scrolledXFloat - (float) target.mLeft;
    final float yc = scrolledYFloat - (float) target.mTop;
    ev.setLocation(xc, yc);
    if ((target.mPrivateFlags & CANCEL_NEXT_UP_EVENT) != 0) {
        ev.setAction(MotionEvent.ACTION_CANCEL);
        target.mPrivateFlags &= ~CANCEL_NEXT_UP_EVENT;
        mMotionTarget = null;
    }
    return target.dispatchTouchEvent(ev);
}
```
其中`requestDisallowInterceptTouchEvent`可以对`disallowIntercept`进行修改

二,如果 `onInterceptTouchEvent(默认为false)`返回了 `false`,则会进入

```java
if (child.dispatchTouchEvent(ev))  {  
                            mMotionTarget = child;  
                            return true;  
                        }  
```

此时,`View`的`dispatchTouchEvent`则会执行
```java

```


一.onTouch和onTouchEvent有什么区别，先经过onTouch，再传递到onClick.

```java
public boolean dispatchTouchEvent(MotionEvent event) {
    if (mOnTouchListener != null && (mViewFlags & ENABLED_MASK) == ENABLED &&
            mOnTouchListener.onTouch(this, event)) {
        return true;
    }
    return onTouchEvent(event);
}
```
二.onTouch返回true，onTouchEvent(event)不执行,onClick就不再执行了

三.onTouchEvent中的ACTION_UP中调用performClick()

```java
public boolean performClick() {  
    sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_CLICKED);  
    if (mOnClickListener != null) {  
        playSoundEffect(SoundEffectConstants.CLICK);  
        mOnClickListener.onClick(this);  
        return true;  
    }  
    return false;  
}  
```

四.
ViewGroup.dispatchTouchEvent,其中requestDisallowInterceptTouchEvent可以对disallowIntercept进行修改,默认为false

```java
   if (disallowIntercept || !onInterceptTouchEvent(ev))
```
会调用

```java
child.dispatchTouchEvent(ev)
```

如果子`View`是可点击的,必然会返回`true`,就不会执行后面`ViewGroup`的 事件了

五.
