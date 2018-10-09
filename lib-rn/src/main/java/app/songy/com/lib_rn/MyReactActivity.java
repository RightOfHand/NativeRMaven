package app.songy.com.lib_rn;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;

import app.songy.com.lib_rn.bridge.RNBridgePackage;
import android.util.Log;

/**
 * Description:
 * Created by song on 2018/9/21.
 * email：bjay20080613@qq.com
 */
public class MyReactActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {
    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;
    private String url="http://localhost:8081/index.bundle?platform=android";
    private RelativeLayout mNavbar ;
    private ImageButton mImageButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react_index);
        RxBus.get().register(this);
        mReactRootView=(ReactRootView) findViewById(R.id.react_root);
        mNavbar=(RelativeLayout) findViewById(R.id.toolbar);
        mImageButton=(ImageButton) findViewById(R.id.toolbar_back);

        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle")
                .setJSMainModulePath("index")
                .addPackage(new MainReactPackage())
                .addPackage(new RNBridgePackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
        // 注意这里的moduleName必须对应“index.js”中的
        // “AppRegistry.registerComponent()”的第一个参数
        mReactRootView.startReactApplication(mReactInstanceManager, "android", null);

        back();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mReactInstanceManager!=null){
        }
    }

    public  void showNavBar(String msg){
        if (msg==null) return;


    }
    private void back(){
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Subscribe(tags = @Tag(BusActions.BUS_UPDATE_UI),thread = EventThread.MAIN_THREAD)
    public void accept(String msg){
        Log.d("accept","挂载了"+":"+msg);
        mNavbar.setVisibility(msg.equals("yes") ? View.VISIBLE:View.GONE);
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
