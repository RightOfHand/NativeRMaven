package app.songy.com.lib_rn.bridge;

import android.app.Activity;
import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;


import app.songy.com.lib_rn.ReactContainerActivity;


/**
 * Description:
 * Created by song on 2018/9/26.
 * email：bjay20080613@qq.com
 */
@ReactModule(name="RNBridge")
public class RNBridge extends ReactContextBaseJavaModule {
    private static final  String TAG=RNBridge.class.getSimpleName();
    private ReactApplicationContext mContext;

    public RNBridge(ReactApplicationContext reactContext) {
        super(reactContext);
        this.mContext=reactContext;
    }

    @Override
    public String getName() {
        return "RNBridge";
    }

    @ReactMethod
    public void send(ReadableMap param, Callback callback){
        Activity mCurrentActvity=getCurrentActivity();
        if (param!=null ){
                String moduleName=null;
                String methodName=null;
                if (param.hasKey("module")){
                    moduleName=param.getString("module");
                }

                if (param.hasKey("method")){
                    methodName=param.getString("method");
                }
                if (moduleName!=null && methodName!=null && moduleName.equals("RN") && methodName.equals("close")){
                    if (mCurrentActvity instanceof ReactContainerActivity){
                         mCurrentActvity.finish();
                    }
                }


        }



    }


}
