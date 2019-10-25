package com.daluobo.com.mvp.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;

import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;

import com.daluobo.com.R;
import com.daluobo.com.mvp.XDroidConf;
import com.daluobo.com.mvp.event.BusProvider;
import com.daluobo.com.mvp.kit.KnifeKit;
import com.daluobo.com.mvp.manager.FinishActivityManager;
import com.daluobo.com.mvp.router.Router;
import com.daluobo.com.mvp.utils.HideSoftKeyboard;
import com.daluobo.com.weight.MyLoadingNoBgDialog;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.OSUtils;
import com.noober.background.BackgroundLibrary;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import butterknife.Unbinder;


/**
 * Created by wanglei on 2016/12/29.
 */

public abstract class XActivity<P extends IPresent> extends RxAppCompatActivity implements IView<P> {

    private VDelegate vDelegate;
    private P p;
    protected Activity context;
    private RxPermissions rxPermissions;
    private Unbinder unbinder;
    public MyLoadingNoBgDialog mypDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 通过标签直接生成shape，无需再写shape.xml
        BackgroundLibrary.inject(this);
        super.onCreate(savedInstanceState);
        context = this;

        getP();

        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            bindUI(null);
            bindEvent();
        }
        initData(savedInstanceState);
        FinishActivityManager.getManager().addActivity(this);
        handleSSLHandshake();
    }

    @Override
    public void bindUI(View rootView) {
        unbinder = KnifeKit.bind(this);
        //初始化沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }

    protected VDelegate getvDelegate() {
        if (vDelegate == null) {
            vDelegate = VDelegateBase.create(context);
        }
        return vDelegate;
    }

    protected P getP() {
        if (p == null) {
            p = newP();
        }
        if (p != null) {
            if (!p.hasV()) {
                p.attachV(this);
            }
        }
        return p;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (useEventBus()) {
            BusProvider.getBus().register(this);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        getvDelegate().resume();

        // 非必加
        // 如果你的app可以横竖屏切换，适配了华为emui3系列系统手机，并且navigationBarWithEMUI3Enable为true，
        // 请在onResume方法里添加这句代码（同时满足这三个条件才需要加上代码哦：1、横竖屏可以切换；2、华为emui3系列系统手机；3、navigationBarWithEMUI3Enable为true）
        // 否则请忽略
        if (OSUtils.isEMUI3_x() && isImmersionBarEnabled()) {
            ImmersionBar.with(this).init();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        getvDelegate().pause();
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        FinishActivityManager.getManager().finishActivity(this);
        if (useEventBus()) {
            BusProvider.getBus().unregister(this);
        }
        if (getP() != null) {
            getP().detachV();
        }
        getvDelegate().destory();
        p = null;
        vDelegate = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getOptionsMenuId() > 0) {
            getMenuInflater().inflate(getOptionsMenuId(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    protected RxPermissions getRxPermissions() {
        rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(XDroidConf.DEV);
        return rxPermissions;
    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    @Override
    public void bindEvent() {

    }

    //不带参数的UI跳转
    protected void launch(Class<?> clazz) {
        Router.newIntent(context)        //context表示当前上下文
                .to(clazz)    //to()指定目标context
                .launch();
    }


    //StartActivityForResult类型的跳转
    //通过 requestCode()方法指定请求码
    protected void launch(Class<?> clazz, String key, String value,
                          int requestCode) {
        Router.newIntent(context)
                .to(clazz)
                .putString(key, value)
                .requestCode(requestCode)
                .launch();
    }
    //overridePendingTransition()类型的指定动画
    //通过anim()指定动画

    protected void launch(Class<?> clazz, String key, String value,
                          int enterAnim, int exitAnim) {
        Router.newIntent(context)
                .to(clazz)
                .putString(key, value)
                .anim(enterAnim, exitAnim)
                .launch();
    }

    //设置flag类型跳转
    //通过addFlags()方法指定flag
    protected void launch(Activity activity, Class<?> clazz, int flags) {
        Router.newIntent(activity)
                .to(clazz)
                .addFlags(flags)
                .launch();
    }

    // 设置场景动画跳转
    // 通过options()方法指定场景动画
    protected void launch(Activity activity, Class<?> clazz, ActivityOptionsCompat options) {
        Router.newIntent(activity)
                .to(clazz)
                .options(options)
                .launch();
    }

    public void launch(Class<?> clz, Bundle bundle) {
        Router.newIntent(this)
                .to(clz)
                .data(bundle)
                .launch();
    }

    protected void launchForResult(Class<?> clz, int requestCode) {
        Router.newIntent(this)
                .to(clz)
                .requestCode(requestCode)
                .launch();
    }

    protected void launchForResult(Class<?> clz, Bundle bundle, int requestCode) {
        Router.newIntent(this)
                .to(clz)
                .data(bundle)
                .requestCode(requestCode)
                .launch();
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        ImmersionBar.with(this)
                .transparentStatusBar()
                .statusBarDarkFont(true)
                .navigationBarColor(R.color.white) //导航栏颜色，不写默认黑色
                .init();   //状态栏字体是深色，不写默认为亮色.init();
    }


    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (HideSoftKeyboard.isShouldHideInput(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /**
     * 忽略https
     */
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("TLS");
            // trustAllCerts信任所有的证书
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }

    }
}
