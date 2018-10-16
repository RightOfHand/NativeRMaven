package app.songy.com.lib_rn;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;

import app.songy.com.lib_rn.bridge.RNConstants;
import app.songy.com.lib_rn.bridge.RNBridgePackage;
import android.util.Log;


/**
 * Description:
 * Created by song on 2018/9/21.
 * email：bjay20080613@qq.com
 */
public class ReactContainerActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {
    private static final  String TAG=ReactContainerActivity.class.getSimpleName();
    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;
    private ReactInstanceManagerBuilder builder;

    private String url="http://localhost:8081/index.bundle?platform=android";
    private String bundleName;
    private String jsMainPath;
    private String moduleName;

    @Subscribe(tags = @Tag(RNConstants.BUS_UPDATE_U),thread = EventThread.MAIN_THREAD)
    public void accept(String data){
        JSONObject jsonObject=JSON.parseObject(data);
        JSONObject params=jsonObject.getJSONObject(RNConstants.RN_PARAMS);
        bundleName=params.getString(RNConstants.RN_PARAM_BUNDLE_NAME);
        jsMainPath=params.getString(RNConstants.RN_PARAM_JS_MAIN_PATH);
        moduleName=params.getString(RNConstants.RN_PARAM_MODULE_NAME);

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.get().register(this);
        Log.d(TAG,"onCreate:"+"................");
        getIntentParam();
        mReactRootView=new ReactRootView(this);
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName(bundleName)
                .setJSMainModulePath(jsMainPath)
                .addPackage(new MainReactPackage())
                .addPackage(new RNBridgePackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
        // 注意这里的moduleName必须对应“index.js”中的
        // “AppRegistry.registerComponent()”的第一个参数
        mReactRootView.startReactApplication(mReactInstanceManager, moduleName, null);
        setContentView(mReactRootView);
    }

    private void getIntentParam(){
        bundleName=getIntent().getStringExtra(RNConstants.RN_PARAM_BUNDLE_NAME);
        jsMainPath=getIntent().getStringExtra(RNConstants.RN_PARAM_JS_MAIN_PATH);
        moduleName=getIntent().getStringExtra(RNConstants.RN_PARAM_MODULE_NAME);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (mReactInstanceManager!=null){
        }
    }




    @Override
    protected void onResume() {
        super.onResume();
        if (mReactInstanceManager!=null){
            mReactInstanceManager.onHostResume(this,this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mReactInstanceManager!=null){
            mReactInstanceManager.onHostPause(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReactInstanceManager!=null){
            mReactInstanceManager.onHostDestroy(this);
        }
        if (mReactRootView!=null){
            mReactRootView.unmountReactApplication();
        }
        RxBus.get().unregister(this);
    }

    @Override
    public void onBackPressed() {
        if (mReactInstanceManager!=null){
            mReactInstanceManager.onBackPressed();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

}
