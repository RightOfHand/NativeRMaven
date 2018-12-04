package app.songy.com.lib_rn;import android.os.Bundle;import android.support.annotation.Nullable;import android.support.v7.app.AppCompatActivity;import android.text.TextUtils;import android.view.KeyEvent;import com.alibaba.android.arouter.facade.annotation.Route;import com.facebook.react.ReactInstanceManager;import com.facebook.react.ReactInstanceManagerBuilder;import com.facebook.react.ReactRootView;import com.facebook.react.common.LifecycleState;import com.facebook.react.devsupport.DevSupportManagerImpl;import com.facebook.react.devsupport.interfaces.DevOptionHandler;import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;import com.facebook.react.shell.MainReactPackage;import app.songy.com.lib_rn.bridge.RNConstants;import app.songy.com.lib_rn.bridge.RNBridgePackage;import app.songy.com.lib_rn.codepush.react.CodePush;import android.util.Log;/** * Description: * Created by song on 2018/9/21. * email：bjay20080613@qq.com */@Route(path = "/rn/loadView")public class ReactContainerActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {    private static final  String TAG=ReactContainerActivity.class.getSimpleName();    private ReactRootView mReactRootView;    private ReactInstanceManager mReactInstanceManager;    private ReactInstanceManagerBuilder builder;    private String moduleName;    private String serverKey;    private String ua;    @Override    protected void onCreate(@Nullable Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        Log.d(TAG,"onCreate:"+"................");        getIntentParam();        if (moduleName==null){            return;        }        mReactRootView=new ReactRootView(this);        mReactInstanceManager = ReactInstanceManager.builder()                .setApplication(getApplication())                .setBundleAssetName("index.android.bundle")                .addPackage(new MainReactPackage())                .addPackage(new RNBridgePackage())                .setJSBundleFile(CodePush.getJSBundleFile())                .setUseDeveloperSupport(BuildConfig.DEBUG)                .setInitialLifecycleState(LifecycleState.RESUMED)                .build();//        // 注意这里的moduleName必须对应“index.js”中的        // “AppRegistry.registerComponent()”的第一个参数            Bundle bundle=new Bundle();            bundle.putString(RNConstants.RN_INIT_SERVER_KEY,serverKey);            bundle.putString(RNConstants.RN_INIT_UA,ua);            mReactRootView.startReactApplication(mReactInstanceManager, moduleName, bundle);            setContentView(mReactRootView);    }    private void getIntentParam(){        if (getIntent()!=null){            moduleName=getIntent().getStringExtra(RNConstants.RN_PARAM_MODULE_NAME);            ua=getIntent().getStringExtra(RNConstants.RN_INIT_UA);            String serverTag=getIntent().getStringExtra(RNConstants.RN_INIT_SERVER_KEY);            getCurEnvKey(serverTag);            Log.d(TAG,serverTag);            Log.d(TAG,moduleName);            Log.d(TAG,ua);        }    }    /**     * Description:     * @author song     * @param serverTitle  环境title     * date:  2018/12/3     */    private void getCurEnvKey(String serverTitle){        if (!TextUtils.isEmpty(serverTitle)){            if ("线上环境".equals(serverTitle)){                serverKey="online";            }else if ("开发环境".equals(serverTitle)){                serverKey="develop";            }else if ("阿里云1".equals(serverTitle)){                serverKey="aliyun1";            }else if ("阿里云2".equals(serverTitle)){                serverKey="aliyun2";            }else {                serverKey="online";            }            Log.d(TAG,serverKey);        }    }    @Override    protected void onStart() {        super.onStart();        if (mReactInstanceManager!=null){        }    }    @Override    protected void onResume() {        super.onResume();        if (mReactInstanceManager!=null){            mReactInstanceManager.onHostResume(this,this);        }    }    @Override    protected void onPause() {        super.onPause();        if (mReactInstanceManager!=null){            mReactInstanceManager.onHostPause(this);        }    }    @Override    protected void onDestroy() {        super.onDestroy();        if (mReactInstanceManager!=null){            mReactInstanceManager.onHostDestroy(this);        }        if (mReactRootView!=null){            mReactRootView.unmountReactApplication();        }    }    @Override    public void onBackPressed() {        if (mReactInstanceManager!=null){            mReactInstanceManager.onBackPressed();        }else {            super.onBackPressed();        }    }    @Override    public boolean onKeyUp(int keyCode, KeyEvent event) {        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {            mReactInstanceManager.showDevOptionsDialog();            return true;        }        return super.onKeyUp(keyCode, event);    }    @Override    public void invokeDefaultOnBackPressed() {        super.onBackPressed();    }}