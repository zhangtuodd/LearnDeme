package com.example.base.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class UIHelper {

    /**
     * 显示Toast的hook
     */
    public interface ToastHook {
        /**
         * 显示Toast的时候调用
         *
         * @param context  Context
         * @param iconId   icon的id
         * @param message  toast内容
         * @param duration 时长
         * @param gravity  {@link Gravity}
         * @return 如果hook处理了toast，返回true，否则返回false
         */
        boolean showToast(Context context, int iconId, CharSequence message, long duration, int gravity);
    }

    public static final char ELLIPSIS_CHAR = '\u2026';
    public static final int LAYOUT_PARAMS_KEEP_OLD = -3;

    public static final int NOT_CHANGE = -100;

    public static final float FAVORITE_RATIO = 9 / 16f;

    public static final boolean API_ET_20 = Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT;

    private static ToastHook sToastHook;

    /**
     * 设置Toast的hook
     */
    public static void setToastHook(ToastHook toastHook) {
        sToastHook = toastHook;
    }








    public static float sp2px(Context context, float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    public static int dip2Px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static void expandClickRegion(final View view, final int left, final int top, final int right,
                                         final int bottom) {
        view.post(new Runnable() {
            @Override
            public void run() {
                Rect delegateArea = new Rect();
                view.getHitRect(delegateArea);
                delegateArea.top += top;
                delegateArea.bottom += bottom;
                delegateArea.left += left;
                delegateArea.right += right;
                TouchDelegate expandedArea = new TouchDelegate(delegateArea, view);
                // give the delegate to an ancestor of the view we're delegating
                // the area to
                if (View.class.isInstance(view.getParent())) {
                    ((View) view.getParent()).setTouchDelegate(expandedArea);
                }
            }
        });
    }


    public static void setViewBackgroundWithPadding(View view, int resid) {
        if (view == null) {
            return;
        }
        int left = view.getPaddingLeft();
        int right = view.getPaddingRight();
        int top = view.getPaddingTop();
        int bottom = view.getPaddingBottom();
        view.setBackgroundResource(resid);
        view.setPadding(left, top, right, bottom);
    }

    public static void setViewBackgroundWithPadding(View view, Resources res, int colorid) {
        if (view == null) {
            return;
        }
        int left = view.getPaddingLeft();
        int right = view.getPaddingRight();
        int top = view.getPaddingTop();
        int bottom = view.getPaddingBottom();
        view.setBackgroundColor(res.getColor(colorid));
        view.setPadding(left, top, right, bottom);
    }

    @SuppressWarnings("deprecation")
    public static void setViewBackgroundWithPadding(View view, Drawable drawable) {
        if (view == null) {
            return;
        }
        int left = view.getPaddingLeft();
        int right = view.getPaddingRight();
        int top = view.getPaddingTop();
        int bottom = view.getPaddingBottom();
        view.setBackgroundDrawable(drawable);
        view.setPadding(left, top, right, bottom);
    }

    public final static String getDisplayCount(int count) {
        if (count > 10000) {
            String result = String.format("%.1f", 1.0 * count / 10000);
            if ('0' == result.charAt(result.length() - 1)) {
                return result.substring(0, result.length() - 2) + "万";
            } else {
                return result + "万";
            }
        }
        return String.valueOf(count);
    }

    public final static int getScreenWidth(Context context) {
        if (context == null) {
            return 0;
        }

        DisplayMetrics dm = new DisplayMetrics();
        if (context instanceof Activity) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
            return dm.widthPixels;
        }
        dm = context.getResources().getDisplayMetrics();

        return (dm == null) ? 0 : dm.widthPixels;
    }

    public final static int getRatioOfScreen(Context context, float ratio) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        if (dm == null) {
            return 0;
        }
        return (int) (dm.widthPixels * ratio);
    }

    public static boolean isInUIThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static void assertInUIThread() {
        boolean isInUIThread = Looper.myLooper() == Looper.getMainLooper();
        if (isInUIThread) {
            return;
        }
        LogUtils.i("not in UI thread");
    }

    public final static int getScreenHeight(Context context) {
        if (context == null) {
            return 0;
        }

        DisplayMetrics dm = new DisplayMetrics();
        if (context instanceof Activity) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
            return dm.heightPixels;
        }
        dm = context.getResources().getDisplayMetrics();
        return (dm == null) ? 0 : dm.heightPixels;
    }

//    private static String sScreenResolution = StringHelper.EMPTY;
//
//    public static String getScreenResolution(Context context) {
//        if (StringHelper.isEmpty(sScreenResolution)) {
//            if (context != null) {
//                int width = getScreenWidth(context);
//                int height = getScreenHeight(context);
//                if (width > 0 && height > 0) {
//                    sScreenResolution = width + "*" + height;
//                }
//            }
//        }
//        return sScreenResolution;
//    }

    private static int DPI = -1;

    public static int getDpi(Context context) {
        if (DPI == -1) {
            if (context != null) {
                DPI = context.getApplicationContext().getResources().getDisplayMetrics().densityDpi;
            }
        }
        return DPI;
    }

    public static int getDiggBuryWidth(Context context) {
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        screenWidth = screenWidth * 1375 / 10000 + (int) (UIHelper.dip2Px(context, 20));
        return screenWidth;
    }

    public final static int getStatusBarHeight(Context context) {
        if (context == null) {
            return 0;
        }
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getNavigationBarHeight(Context context) {
        try {
            if (context == null) {
                return 0;
            }
            int result = 0;
            int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = context.getResources().getDimensionPixelSize(resourceId);
            }
            return result;
        } catch (Exception e) {
            return 0;
        }

    }

    public static boolean isNavigationBarShow(Activity activity) {
        if (activity == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Display display = activity.getWindowManager().getDefaultDisplay();
            Point size = new Point();
            Point realSize = new Point();
            display.getSize(size);
            display.getRealSize(realSize);
            return realSize.y != size.y;
        } else {
            boolean menu = ViewConfiguration.get(activity).hasPermanentMenuKey();
            boolean back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            if (menu || back) {
                return false;
            } else {
                return true;
            }
        }
    }

    private static boolean visibilityValid(int visiable) {
        return visiable == View.VISIBLE || visiable == View.GONE || visiable == View.INVISIBLE;
    }

//    public final static void setViewVisibility(final View v, final int visiable) {
//        if (v == null || v.getVisibility() == visiable || !visibilityValid(visiable)) {
//            return;
//        }
//        ThreadManager.getInstance().runOnMainThread(new Runnable() {
//            @Override
//            public void run() {
//                v.setVisibility(visiable);
//            }
//        });
//    }

    public final static boolean isViewVisible(View view) {
        if (view == null) {
            return false;
        }

        return view.getVisibility() == View.VISIBLE;
    }

    /**
     * get location of view relative to given upView. get center location if
     * getCenter is true.
     */
    public static void getLocationInUpView(View upView, View view, int[] loc, boolean getCenter) {
        if (upView == null || view == null || loc == null || loc.length < 2) {
            return;
        }
        upView.getLocationInWindow(loc);
        int x1 = loc[0];
        int y1 = loc[1];
        view.getLocationInWindow(loc);
        int x2 = loc[0] - x1;
        int y2 = loc[1] - y1;
        if (getCenter) {
            int w = view.getWidth();
            int h = view.getHeight();
            x2 = x2 + w / 2;
            y2 = y2 + h / 2;
        }
        loc[0] = x2;
        loc[1] = y2;
    }

    public static void updateLayout(View view, int w, int h) {
        if (view == null) {
            return;
        }

        LayoutParams params = view.getLayoutParams();
        if (params == null) {
            return;
        }

        boolean changed = false;
        if (w != NOT_CHANGE && params.width != w) {
            changed = true;
            params.width = w;
        }
        if (h != NOT_CHANGE && params.height != h) {
            changed = true;
            params.height = h;
        }

        if (changed) {
            view.setLayoutParams(params);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                view.setMinimumWidth(params.width);
                view.setMinimumHeight(params.height);
            }
        }
    }

    public static void updateLayoutMargin(View view, int left, int top, int right, int bottom) {
        if (view == null) {
            return;
        }
        LayoutParams params = view.getLayoutParams();
        if (params == null) {
            return;
        }
        if (params instanceof RelativeLayout.LayoutParams) {
            updateMargin(view, (RelativeLayout.LayoutParams) params, left, top, right, bottom);
        } else if (params instanceof LinearLayout.LayoutParams) {
            updateMargin(view, (LinearLayout.LayoutParams) params, left, top, right, bottom);
        } else if (params instanceof FrameLayout.LayoutParams) {
            updateMargin(view, (FrameLayout.LayoutParams) params, left, top, right, bottom);
        } else if (params instanceof ViewGroup.MarginLayoutParams) {
            updateMargin(view, (ViewGroup.MarginLayoutParams) params, left, top, right, bottom);
        }
    }

    private static void updateMargin(View view, ViewGroup.MarginLayoutParams params, int l, int t, int r, int b) {
        boolean changed = false;
        if (l != NOT_CHANGE && params.leftMargin != l) {
            changed = true;
            params.leftMargin = l;
        }
        if (t != NOT_CHANGE && params.topMargin != t) {
            changed = true;
            params.topMargin = t;
        }
        if (r != NOT_CHANGE && params.rightMargin != r) {
            changed = true;
            params.rightMargin = r;
        }
        if (b != NOT_CHANGE && params.bottomMargin != b) {
            changed = true;
            params.bottomMargin = b;
        }
        if (changed) {
            view.setLayoutParams(params);
        }
    }

    public static void updatePadding(View view, int l, int t, int r, int b) {
        if (view == null) {
            return;
        }
        int pl = view.getPaddingLeft();
        int pt = view.getPaddingTop();
        int pr = view.getPaddingRight();
        int pb = view.getPaddingBottom();

        boolean changed = false;
        if (l != NOT_CHANGE && pl != l) {
            changed = true;
            pl = l;
        }
        if (t != NOT_CHANGE && pt != t) {
            changed = true;
            pt = t;
        }
        if (r != NOT_CHANGE && pr != r) {
            changed = true;
            pr = r;
        }
        if (b != NOT_CHANGE && pb != b) {
            changed = true;
            pb = b;
        }
        if (changed) {
            view.setPadding(pl, pt, pr, pb);
        }
    }

    public static void updateBrightness(Activity activity, float brightness) {
        if (activity == null || activity.isFinishing()) {
            return;
        }

        Window window = activity.getWindow();
        if (window.getAttributes().screenBrightness != brightness) {
            window.getAttributes().screenBrightness = brightness;
            window.setAttributes(window.getAttributes());
        }
    }

    public static void detachFromParent(View view) {
        if (view == null || view.getParent() == null) {
            return;
        }
        ViewParent parent = view.getParent();
        if (!(parent instanceof ViewGroup)) {
            return;
        }
        try {
            ((ViewGroup) parent).removeView(view);
        } catch (Exception e) {
        }
    }

    @SuppressLint("NewApi")
    public static void setViewMinHeight(View view, int minHeight) {
        if (view == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 16 && view.getMinimumHeight() == minHeight) {
            return;
        }
        view.setMinimumHeight(minHeight);
    }



    public static int setColorAlpha(int color, int alpha) {
        if (alpha > 0xff) {
            alpha = 0xff;
        } else if (alpha < 0) {
            alpha = 0;
        }
        return (color & 0xffffff) | (alpha * 0x1000000);
    }



    public static int floatToIntBig(float value) {
        return (int) (value + 0.999f);
    }

    public static class EllipsisMeasureResult {
        public String ellipsisStr;
        public int length;
    }

    public static EllipsisMeasureResult sTempEllipsisResult = new EllipsisMeasureResult();

    public static void requestOrienation(Activity activity, boolean landscape) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        activity.setRequestedOrientation(landscape ?
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (landscape) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    public static int getIndexInParent(View view) {
        if (view == null || view.getParent() == null) {
            return -1;
        }
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            return ((ViewGroup) parent).indexOfChild(view);
        }
        return -1;
    }

    public static boolean clearAnimation(View view) {
        if (view == null || view.getAnimation() == null) {
            return false;
        }
        view.clearAnimation();
        return true;
    }


    public static void setClickListener(boolean clickable, View view, View.OnClickListener clickListener) {
        if (view == null) {
            return;
        }
        if (clickable) {
            view.setOnClickListener(clickListener);
            view.setClickable(true);
        } else {
            view.setOnClickListener(null);
            view.setClickable(false);
        }
    }

    public static int getStatusBarHeight(Context context, boolean inPixel) {
        if (context == null) return 0;
        int result = 0;
        try {
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = inPixel ? context.getResources().getDimensionPixelSize(resourceId) :
                        (int) (context.getResources().getDimension(resourceId) / context.getResources().getDisplayMetrics().density);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int defaultValue = inPixel ? (int) UIHelper.dip2Px(context, 25) : 25;
        return result == 0 ? defaultValue : result;
    }







    private static int getHuaweiMagicWindowScreenWidth(Activity activity) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }


    public static int getDimen(Resources resources, int dimenId) {
        if (dimenId > 0) {
            return resources.getDimensionPixelSize(dimenId);
        } else {
            return 0;
        }
    }




    public static boolean isMIUI() {
        String manufacturer = Build.MANUFACTURER;
        if ("xiaomi".equalsIgnoreCase(manufacturer)) {
            return true;
        }
        return false;
    }

}
