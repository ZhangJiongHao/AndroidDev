View Animation
------------------

## 通用属性

- android:duration 动画从开始到结束持续的时长，单位为毫秒
- android:detachWallpaper 设置是否在壁纸上运行，只对设置了壁纸背景的窗口动画(window animation)有效。设为true，则动画只在窗口运行，壁纸背景保持不变
- android:fillAfter 设置为true时，动画执行完后，View会停留在动画的最后一帧；默认为false；如果是动画集，需在<set>标签中设置该属性才有效
- android:fillBefore 设置为true时，动画执行完后，View回到动画执行前的状态，默认即为true
- android:fillEnabled 设置为true时，android:fillBefore的值才有效，否则android:fillBefore会被忽略
- android:repeatCount 设置动画重复执行的次数，默认为0，即不重复；可设为-1或infinite，表示无限重复
- android:repeatMode 设置动画重复执行的模式，可设为以下两个值其中之一：

- restart 动画重复执行时从起点开始，默认为该值
- reverse 动画会反方向执行
- android:startOffset 设置动画执行之前的等待时长，毫秒为单位；重复执行时，每次执行前同样也会等待一段时间

- android:zAdjustment 表示被设置动画的内容在动画运行时在Z轴上的位置，取值为以下三个值之一：

>- normal 默认值，保持内容在Z轴上的位置不变
>- top 保持在Z周最上层
- bottom 保持在Z轴最下层

- android:interpolator 设置动画速率的变化，比如加速、减速、匀速等，需要指定Interpolator资源，后面再详细讲解

- `<set>`标签还有个android:shareInterpolator属性，设置为true时则可将interpolator应用到所有子元素中

## Interpolator


|Interpolator class |	Resource ID	| Description|
|:--------:|:--------:|:--------:|
|AccelerateDecelerateInterpolator	|@android:anim/accelerate_decelerate_interpolator	|在动画开始与结束时速率改变比较慢，在中间的时候加速|
|AccelerateInterpolator	|@android:anim/accelerate_interpolator	|在动画开始时速率改变比较慢，然后开始加速|
|AnticipateInterpolator	|@android:anim/anticipate_interpolator	|动画开始的时候向后然后往前抛|
|AnticipateOvershootInterpolator|	@android:anim/anticipate_overshoot_interpolator	|动画开始的时候向后然后向前抛，会抛超过目标值后再返回到最后的值|
|BounceInterpolator	|@android:anim/bounce_interpolator	|动画结束的时候会弹跳|
|CycleInterpolator	|@android:anim/bounce_interpolator	|动画循环做周期运动，速率改变沿着正弦曲线|
|DecelerateInterpolator	|@android:anim/decelerate_interpolator	|在动画开始时速率改变比较快，然后开始减速|
|LinearInterpolator	|@android:anim/decelerate_interpolator	|动画匀速播放|
|OvershootInterpolator|	@android:anim/overshoot_interpolator	|动画向前抛，会抛超过最后值，然后再返回|

### 自定义Interpolator

一: 继承Interpolator或其子类

```java
private class DeceAcceInterpolator implements Interpolator{

    @Override public float getInterpolation(float input) {
      return ((4*input-2)*(4*input-2)*(4*input-2))/16f + 0.5f;
    }
  }
```

二: 自定义xml,更改属性

其中可设置属性包括

|Interpolator class |	Attribute Name	| 
|:--------:|:--------:|
|accelerateInterpolator|android:factor(浮点值，加速的速率，默认为1)|
|anticipateInterpolator|android:tension(浮点值，向后的拉力，默认为2，当设为0时，则不会有向后的动画了)                  |
|anticipateOvershootInterpolator|android:tension(同上),android:extraTension(浮点值，拉力的倍数，默认为1.5,当设为0时，则不会有向后的动画了)|
|cycleInterpolator|android:cycles (整数值，循环的次数，默认为1)|
|decelerateInterpolator|android:factor(浮点值，减速的速率，默认为1)|
|overshootInterpolator|android:tension(浮点值，超出终点后的拉力，默认为2)|

示例:

```xml
<!--res/anim/over_interpolator.xml-->
<?xml version="1.0" encoding="utf-8"?>
<overshootInterpolator xmlns:android="http://schemas.android.com/apk/res/android"
android:tension="7.0"/>

<!--res/anim/rotate_one_rotate.xml-->
<?xml version="1.0" encoding="utf-8"?>
<rotate xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="2000"
    android:fromDegrees="0"
    android:pivotX="50%"
    android:pivotY="50%"
    android:interpolator="@anim/my_interpolator" 
    android:toDegrees="360"/>
```
