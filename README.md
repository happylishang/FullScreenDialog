Dialog是APP开发中常用的控件，同Activity类似，拥有独立的Window窗口，但是Dialog跟Activity还是有一定区别的，最明显的就是：默认情况下Dialog不是全屏的，所以布局实现不如Activity舒服，比如顶部对齐，底部对齐、边距、宽度、高度等。如果将Dialog定义成全屏的就会省去很多问题，可以完全按照常用的布局方式来处理。网上实现方式有不少，一般情况下也都能奏效，不过可能会有不少疑虑，比如：为什么有些窗口属性（隐藏标题）必须要在setContentView之前设置才有效，相反，也有些属性（全屏）要在之后设置才有效。这里挑几个简单的实现方式，然后说下原因，由于Android的窗口管理以及View绘制是挺大的一块，这里不过多深入。先看实现效果：

![全屏Dialog](http://upload-images.jianshu.io/upload_images/1460468-81c39c48fa4e52e9.gif?imageMogr2/auto-orient/strip)

# 全屏Dialog实现方法

这里对象分为两种，一种是针对传统的Dialog，另一种是针对DialogFragment（推荐）,方法也分为两种一种是利用代码实现，另一种是利用主题样式Theme来实现。

> 针对Dialog的实现方式

	public class FullScrreenDialog extends Dialog {
	    public FullScrreenDialog(Context context) {
	        super(context);
	    }
	
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        <!--关键点1-->
	        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
	        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_full_screen, null);
	        <!--关键点2-->
	        setContentView(view);
	        <!--关键点3-->
	        getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
	        <!--关键点4-->
	        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
	    }
	}
	
这里牵扯到四个点，关键点1要在setContentView之前设置，主要是为了兼容一些低版本的，不让显示Title部分，关键点2就是常用的setContentView，关键点3根4就是为了全屏对话框做的修改，关键点4必须要放在setContent的后面，因为如果放在setContent该属性会被冲掉无效，原因再后面说。如果你想封装一个统一的全屏Dialog，那可以吧关键点1放在构造方法中，把关键点3与4放在onStart中，其实就是主要是保证setContentView的执行顺序，

	public class FullScreenDialog extends Dialog {
	    public FullScreenDialog(Context context) {
	        super(context);
	        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
	    }

	    @Override
	    protected void onStart() {
	        getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
	        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
	    }
	}

之后再看下DialogFragment的做法。

> 针对DialogFragment的实现方式

Android比较推荐采用DialogFragment实现对话框，它完全能够实现Dialog的所有需求，并且还能复用Fragment的生命周期管理，被后台杀死后还能自动恢复。其实现全屏的原理同Dialog一样，只不过是时机的把握

	public class FullScreen DialogFragment extends DialogFragment {
	
	    @Nullable
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        return inflater.inflate(R.layout.fragment_full_screen, container, false);
	    }
	
	    @Override
	    public void onActivityCreated(Bundle savedInstanceState) {
	    <!--关键点1-->
	        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
	        super.onActivityCreated(savedInstanceState);
	    <!--关键点2-->
	        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
	        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
	    }
	
	}
	
先看下这里为什么放在onActivityCreated中处理，如果稍微跟下DialogFragment的实现源码就会发现，其setContentView的时机是在onActivityCreated，看如下代码关键点1

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!mShowsDialog) {
            return;
        }
        View view = getView();
        if (view != null) {
            if (view.getParent() != null) {
                throw new IllegalStateException("DialogFragment can not be attached to a container view");
            }
            <!--关键点1-->
            mDialog.setContentView(view);
        }
        ...
    }

当然，也完全可以参考基类Dialog的实现方式，**其实关键就是把握 setContentView的调用时机**。之后来看第二种方案，利用Theme来实现。

>  利用Theme主题来实现全拼对话框

第一步在style中定义全屏Dialog样式

    <style name="Dialog.FullScreen" parent="Theme.AppCompat.Dialog">
        <item name="android:windowNoTitle">true</item>
        <item name="android:windowBackground">@color/transparent</item>
        <item name="android:windowIsFloating">false</item>
    </style>
    
第二步：设置样式，以DialogFragment为例，只需要在onCreate中setStyle(STYLE_NORMAL, R.style.Dialog_FullScreen)即可。（推荐使用DialogFragment，它复用了Fragment的声明周期，被杀死后，可以恢复重建）

	public class FragmentFullScreen extends DialogFragment {
	
	    @Override
	    public void onCreate(@Nullable Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setStyle(STYLE_NORMAL, R.style.Dialog_FullScreen);
	    }
	}
	
如果是在Dialog中,设置如下代码即可。

	public class FullScreenDialog extends Dialog {
		public FullScreenDialog(Context context) {
		    super(context);
		    getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		  }
	}

其实纯代码的效果跟这三个属性对应，那么这三个属性究竟有什么作用，设置的时机为何又有限制，下面就简单分析一下原因。

# 全屏Dialog实现原理

针对以下三个属性一步步分析。

        <item name="android:windowIsFloating">false</item>
        <item name="android:windowBackground">@color/transparent</item>
        <item name="android:windowNoTitle">true</item>

首先看下第一个属性，android:windowIsFloating，这个属性可能是Activity默认样式同Dialog最大的区别之一，对比一下默认的Dialog主题与Activity主题,两者都是继承Theme，在Theme中

> Theme

	 	<style name="Theme">
	 			...
	         <item name="windowIsFloating">false</item>
	    </style>

但是Dialog的一般都进行了覆盖，而Activity默认没有覆盖windowIsFloating属性
    
> Base.V7.Theme.AppCompat.Dialog

    <style name="Base.V7.Theme.AppCompat.Dialog" parent="Base.Theme.AppCompat">
        ...
        <item name="android:windowIsFloating">true</item>
    </style>

也就是说Activity采用了默认的 <item name="windowIsFloating">false</item>，而Dialog的一般是True，这两者在创建Window的时候有什么区别呢？进入PhoneWindow.java中，当Window在第一次创建DecorView的时候是需要根据该属性去创建顶层布局参数的，也就是RootMeasureSpec，Window被新建的时候，WindowManager.LayoutParams默认采用的是MATCH_PARENT，但是如果windowIsFloating 被设置为True，WindowManager.LayoutParams参数中的尺寸就会被设置成WRAP_CONTENT，具体源码如下：

    protected ViewGroup generateLayout(DecorView decor) {
        // Apply data from current theme.
        TypedArray a = getWindowStyle();
        mIsFloating = a.getBoolean(com.android.internal.R.styleable.Window_windowIsFloating, false);
        int flagsToUpdate = (FLAG_LAYOUT_IN_SCREEN|FLAG_LAYOUT_INSET_DECOR)
                & (~getForcedWindowFlags());
         <!--关键点1-->
        if (mIsFloating) {
            setLayout(WRAP_CONTENT, WRAP_CONTENT);
            setFlags(0, flagsToUpdate);
        } else {
            setFlags(FLAG_LAYOUT_IN_SCREEN|FLAG_LAYOUT_INSET_DECOR, flagsToUpdate);
        }
        ...       
    }

从关键点1可以看到，如果windowIsFloating被配置为true，就会通过setLayout(WRAP_CONTENT, WRAP_CONTENT)将Window的窗口属性WindowManager.LayoutParams设置为WRAP_CONTENT,**这个属性对于根布局MeasureSpec参数的生成起着关键作用**

    public void setLayout(int width, int height) {
        final WindowManager.LayoutParams attrs = getAttributes();
        attrs.width = width;
        attrs.height = height;
        if (mCallback != null) {
            mCallback.onWindowAttributesChanged(attrs);
        }
    }
    
**至于为什么要在setContentView之后设置参数，是因为generateLayout一般是通过setContentView调用的**，所以即使提前设置了压根没效果，PhoneWindow仍然是根据windowIsFloating来设置WindowManager.LayoutParams。其实View真正显示的点是在Activity resume的时候，让WMS添加View，其实是这里调用WindowManagerGlobal的addView，这里有个很关键的布局参数params，其实传就是WindowManager.LayoutParams l = r.window.getAttributes();如果是Dialog默认主题，该参数的宽高其实是WRAP_CONTENT，是测量最初限定参数值的起点，也就是说，一个Window究竟多大，这个参数是有最终话语权的，具体的View绘制流程这不详述，只看下View 的measureHierarchy，是如何利用window参数构造RootMeasureSpec的：

	measureHierarchy(final View host, final WindowManager.LayoutParams lp,
            final Resources res, final int desiredWindowWidth, final int desiredWindowHeight) { 
			 ...
			 <!--desiredWindowWidth一般是屏幕的宽高-->
      	   childWidthMeasureSpec = getRootMeasureSpec(desiredWindowWidth, lp.width);
         childHeightMeasureSpec = getRootMeasureSpec(desiredWindowHeight, lp.height);
       		... 
		 }  

desiredWindowWidth与desiredWindowHeight一般是屏幕的宽度与高度，而WindowManager.LayoutParams lp就是上面设置的参数，如果是Activity，默认是ViewGroup.LayoutParams.MATCH_PARENT，而如果是Dialog，就是ViewGroup.LayoutParams.WRAP_CONTENT，而根据MeasureSpec的默认生成规则，如下：

    private static int getRootMeasureSpec(int windowSize, int rootDimension) {
        int measureSpec;
        switch (rootDimension) {
        case ViewGroup.LayoutParams.MATCH_PARENT:
            measureSpec = MeasureSpec.makeMeasureSpec(windowSize, MeasureSpec.EXACTLY);
            break;
        case ViewGroup.LayoutParams.WRAP_CONTENT:
            measureSpec = MeasureSpec.makeMeasureSpec(windowSize, MeasureSpec.AT_MOST);
            break;
        default:
            measureSpec = MeasureSpec.makeMeasureSpec(rootDimension, MeasureSpec.EXACTLY);
            break;
        }
        return measureSpec;
    }

如果是Dialog，就是会之后就会利用MeasureSpec.makeMeasureSpec(windowSize, MeasureSpec.AT_MOST)生成RootMeasureSpec，也就是最大是屏幕尺寸，实际效果就是我们常用的wrap_content，之后会利用该RootMeasureSpec对DecorView进行测量绘制。

      private void performMeasure(int childWidthMeasureSpec, int childHeightMeasureSpec) {
        Trace.traceBegin(Trace.TRACE_TAG_VIEW, "measure");
        try {
            mView.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        } finally {
            Trace.traceEnd(Trace.TRACE_TAG_VIEW);
        }
    }
      
以上就是默认Dialog无法全屏的关键原因之一， 接着看第二属性 android:windowBackground，这个属性如果采用默认值，设置会有黑色边框，其实这里主要是默认背景的问题，默认采用了有padding的InsetDrawable,设置了一些边距，导致上面的状态栏，底部的导航栏，左右都有一定的边距

	<inset xmlns:android="http://schemas.android.com/apk/res/android"
	       android:insetLeft="16dp"
	       android:insetTop="16dp"
	       android:insetRight="16dp"
	       android:insetBottom="16dp">
	    <shape android:shape="rectangle">
	        <corners android:radius="2dp" />
	        <solid android:color="@color/background_floating_material_dark" />
	    </shape>
	</inset>

DecorView在绘制的时候，会将这里的边距考虑进去，而且对于windowIsFloating = false的Window，会将状态栏及底部导航栏考虑进去（这里不分析）。之后再来看最后遗留的一个问题，为什么么要Window.FEATURE_NO_TITLE属性，并且需要在setContentView被调用之前。

# 为什么需要在setContentView之前设置Window.FEATURE_NO_TITLE属性

如果不设置该属性，有可能出现如下效果：

![不设置Window.FEATURE_NO_TITLE](http://upload-images.jianshu.io/upload_images/1460468-e236884ccb835fd6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240) 

在上面的分析中我们知道，setContentView会进一步调用generateLayout创建根布局，Android系统默认实现了多种样式的根布局应，以应对不同的场景，选择的规则就是用户设置的主题样式（Window属性），比如需不需要Title，而布局样式在选定后就不能再改变了（大小可以），有些属性是选择布局文件的参考，如果是在setContentView之后再设定，就是失去了意义，另外Android也不允许在选定布局后，设置一些影响布局选择的属性，会抛出异常，原理如下。
     
        protected ViewGroup generateLayout(DecorView decor) {
        TypedArray a = getWindowStyle();
     		 ...
        if (a.getBoolean(com.android.internal.R.styleable.Window_windowNoTitle, false)) {
            requestFeature(FEATURE_NO_TITLE);
        } else if (a.getBoolean(com.android.internal.R.styleable.Window_windowActionBar, false)) {
            requestFeature(FEATURE_ACTION_BAR);
        }
        
     @Override
     public boolean requestFeature(int featureId) {
        if (mContentParent != null) {
            throw new AndroidRuntimeException("requestFeature() must be called before adding content");
        }
        ...
        }
                            
以上就是对全屏Dialog定制的一些处理以及对全屏原理的浅析（这里不包括对状态栏的处理，那部分涉及到SystemUI）。

# 创建沉浸式全屏Dialog

在全屏Dialog的基础上，我们可以创建沉浸式Dialog，也就是让Dialog的内容区域延展到状态栏下方，由于这里用到了fitsystemwindow，所以要牵扯DecorView及rootView的设置，在上面的基础上有两点需要处理，一是:内容区域延展上去；二是：状态栏变成透明（不透明你也看不见啊），如何延展内容区域，参考[全屏、沉浸式、fitSystemWindow使用及原理分析：全方位控制“沉浸式”的实现](https://www.jianshu.com/p/28f1954812b3)，具体实现如下：

	    @Override
	    public void show() {
	        if (getWindow() != null && getWindow().getDecorView() != null) {
	            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
	                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
	        }
	        super.show();
	    }

如何设置状态栏透明呢？（不兼容5.0以下），主题中添加如下属性，设置状态栏颜色透明即可，当然，代码中也可实现。

        <item name="android:statusBarColor">@color/transparent</item>

   效果如下

 ![21526023112_.pic.jpg](https://upload-images.jianshu.io/upload_images/1460468-12da06c391b736b2.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

作者：看书的小蜗牛
原文链接: [三句代码创建全屏Dialog或者DialogFragment：带你从源码角度实现](http://www.jianshu.com/p/3ecad4bfc55e)

# 能完全使用Java代码创建创建沉浸式全屏Dialog吗？

不能，原因如下：只有!mWindow.mIsFloating的时候，才能修改状态栏颜色，如果无法修改颜色，自然无法沉浸式


    WindowInsets updateColorViews(WindowInsets insets, boolean animate) {
        WindowManager.LayoutParams attrs = mWindow.getAttributes();
        int sysUiVisibility = attrs.systemUiVisibility | getWindowSystemUiVisibility();

        if (!mWindow.mIsFloating) {
            boolean disallowAnimate = !isLaidOut();
            disallowAnimate |= ((mLastWindowFlags ^ attrs.flags)
                    & FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS) != 0;
            mLastWindowFlags = attrs.flags;
				...
            boolean navBarToRightEdge = isNavBarToRightEdge(mLastBottomInset, mLastRightInset);
            boolean navBarToLeftEdge = isNavBarToLeftEdge(mLastBottomInset, mLastLeftInset);
            int navBarSize = getNavBarSize(mLastBottomInset, mLastRightInset, mLastLeftInset);
            updateColorViewInt(mNavigationColorViewState, sysUiVisibility,
                    mWindow.mNavigationBarColor, mWindow.mNavigationBarDividerColor, navBarSize,
                    navBarToRightEdge || navBarToLeftEdge, navBarToLeftEdge,
                    0 /* sideInset */, animate && !disallowAnimate, false /* force */);

            boolean statusBarNeedsRightInset = navBarToRightEdge
                    && mNavigationColorViewState.present;
            boolean statusBarNeedsLeftInset = navBarToLeftEdge
                    && mNavigationColorViewState.present;
            int statusBarSideInset = statusBarNeedsRightInset ? mLastRightInset
                    : statusBarNeedsLeftInset ? mLastLeftInset : 0;
            updateColorViewInt(mStatusColorViewState, sysUiVisibility,
                    calculateStatusBarColor(), 0, mLastTopInset,
                    false /* matchVertical */, statusBarNeedsLeftInset, statusBarSideInset,
                    animate && !disallowAnimate,
                    mForceWindowDrawsStatusBarBackground);
        }

**仅供参考，欢迎指正**

### 参考文档

[Android 官方推荐 : DialogFragment 创建对话框](http://blog.csdn.net/lmj623565791/article/details/37815413)
[如何控制宽度](http://blog.csdn.net/zhyh1986/article/details/48655885)
[ Android Project Butter分析](http://blog.csdn.net/innost/article/details/8272867)
[浅析 android 应用界面的展现流程（四）创建绘制表面](http://3dobe.com/archives/120/)
[浅析Android的窗口](http://bugly.qq.com/bbs/forum.php?mod=viewthread&tid=555)
