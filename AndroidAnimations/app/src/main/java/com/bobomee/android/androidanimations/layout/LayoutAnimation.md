Android 动画(一)LayoutAnimation与LayoutTransition
---------------------------------------------
## 概述
在Android的动画体系中,有补间动画,帧动画和属性动画,但是这些动画都是针对单个对象的,如果相对ViewGroup作动画,就要用到布局动画了

## LayoutAnimation

LayoutAnimation 是API Level 1 就已经有的，LayoutAnimation是对于ViewGroup控件所有的child view的操作，
也就是说它是用来控制ViewGroup中所有的child view 显示的动画。LayoutAnimation动画可以直接在xml中定义：

- 首先定义单个View的补间动画

```java
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="500">
  <translate
      android:fromXDelta="-50%p"
      android:toXDelta="0"/>
  <alpha
      android:fromAlpha="0.0"
      android:toAlpha="1.0"/>
</set>
```
- 定义layoutAnimation

```java
<?xml version="1.0" encoding="utf-8"?>
<layoutAnimation xmlns:android="http://schemas.android.com/apk/res/android"
    android:animation="@anim/slide_in_from_left"
    android:animationOrder="random"
    android:delay="1"/>
<!--
delay : ViewGroup 中单个Item动画的开始延时,取值是android:animation 所指定动画时长的倍数,可以是float,也可以是百分数,默认0.5
比如slide_in_from_left中定义的动画时长是500ms,这里delay=1,那么在上一个动画执行之后延时500ms执行下一个tem的动画

animationOrder : 动画开始顺序,normal(正序)、reverse(倒序)、random(随机)

animation : 指定动画资源animation,注意不能使用animator
-->
```

可以通过下面两种方式加载

1. 直接在ViewGroup的 layout xml 文件中设置：

```java
android:layoutAnimation="@anim/list_item_slide_layout_animation"
```

2. 使用代码设置

```java
Animation animation = AnimationUtils.loadAnimation(mActivity,R.anim.slide_in_from_left);
LayoutAnimationController animationController = new LayoutAnimationController(animation,
         1f);
animationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
mList.setLayoutAnimation(animationController);
mList.startLayoutAnimation();
```

> 同时,系统也提供了`gridLayoutAnimation`用于给Gridview设置布局动画,
需要注意的是布局动画,在创建ViewGroup的时候生效,后续添加的子View是没有动画效果的


## LayoutTransition

LayoutTransition 是API Level 11 才出现的。LayoutTransition的动画效果，只有当ViewGroup中有View添加、删除、隐藏、显示的时候才会体现出来。
是一个布局改变动画
LayoutTransition类中主要有五种容器转换动画类型，具体如下：
 
```java
LayoutTransition.APPEARING：当View出现或者添加的时候View出现的动画。  
LayoutTransition.CHANGE_APPEARING：当添加View导致布局容器改变的时候整个布局容器的动画。  
LayoutTransition.DISAPPEARING：当View消失或者隐藏的时候View消失的动画。  
LayoutTransition.CHANGE_DISAPPEARING：当删除或者隐藏View导致布局容器改变的时候整个布局容器的动画。  
LayoutTransition.CHANGE：当不是由于View出现或消失造成对其他View位置造成改变的时候整个布局容器的动画。  
```

LayoutTransition也有两种方式添加

- 在xml中直接添加

```java
// 使用系统默认的LayoutTransition动画
android:animateLayoutChanges="true"  
```

- 在代码中使用

```java
// 使用系统默认的LayoutTransition动画
LayoutTransition mTransitioner = new LayoutTransition();  
mViewGroup.setLayoutTransition(mTransitioner);  
```

自定义LayoutTransition

当然也可以不是用系统提供的LayoutTransition,这时候就可以使用自定义布局动画了

```java
    // 生成自定义动画
    private void setupCustomAnimations() {
        // 动画：CHANGE_APPEARING
        // Changing while Adding
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0, 1);
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top", 0, 1);
        PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", 0,
                1);
        PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofInt("bottom",
                0, 1);
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX",
                1f, 0f, 1f);
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY",
                1f, 0f, 1f);

        final ObjectAnimator changeIn = ObjectAnimator.ofPropertyValuesHolder(
                this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhScaleX,
                pvhScaleY).setDuration(
                mTransitioner.getDuration(LayoutTransition.CHANGE_APPEARING));
        mTransitioner.setAnimator(LayoutTransition.CHANGE_APPEARING, changeIn);
        changeIn.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setScaleX(1f);
                view.setScaleY(1f);
            }
        });

        // 动画：CHANGE_DISAPPEARING
        // Changing while Removing
        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(.9999f, 360f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe(
                "rotation", kf0, kf1, kf2);
        final ObjectAnimator changeOut = ObjectAnimator
                .ofPropertyValuesHolder(this, pvhLeft, pvhTop, pvhRight,
                        pvhBottom, pvhRotation)
                .setDuration(
                        mTransitioner
                                .getDuration(LayoutTransition.CHANGE_DISAPPEARING));
        mTransitioner.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,
                changeOut);
        changeOut.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotation(0f);
            }
        });

        // 动画：APPEARING
        // Adding
        ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "rotationY", 90f,
                0f).setDuration(
                mTransitioner.getDuration(LayoutTransition.APPEARING));
        mTransitioner.setAnimator(LayoutTransition.APPEARING, animIn);
        animIn.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotationY(0f);
            }
        });

        // 动画：DISAPPEARING
        // Removing
        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "rotationX", 0f,
                90f).setDuration(
                mTransitioner.getDuration(LayoutTransition.DISAPPEARING));
        mTransitioner.setAnimator(LayoutTransition.DISAPPEARING, animOut);
        animOut.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotationX(0f);
            }
        });

    }
```
