package app.songy.com.lib_rn.bridge;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;


import app.songy.com.lib_rn.ReactContainerActivity;
import app.songy.com.lib_rn.helper.CheckPermissionUtils;

import static android.app.Activity.RESULT_OK;


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
      final   Activity mCurrentActivity=mContext.getCurrentActivity();

        if (param!=null ){
            Log.d(TAG,param.toString());
                JSONObject jsonResult=JSON.parseObject(param.toString());
                if (jsonResult!=null){
                    JSONObject jsonObject=jsonResult.getJSONObject("NativeMap");
                    if (jsonObject!=null){
                            Log.d(TAG+":transfer:",JSON.toJSONString(jsonObject));
                            if (jsonObject.containsValue("RN") && jsonObject.containsValue("close")){
                                if (mCurrentActivity!=null){
                                    Log.d(TAG,"close ReactContainerActivity:");
                                    mCurrentActivity.finish();
                                }

                            } else {
                                Postcard postcard=ARouter.getInstance().build("/action/handler");
                                postcard.withString("result",JSON.toJSONString(jsonObject));

                                ARouter.getInstance().navigation(mCurrentActivity, postcard, 0x11, new NavigationCallback() {
                                    @Override
                                    public void onFound(Postcard postcard) {

                                    }

                                    @Override
                                    public void onLost(Postcard postcard) {

                                    }

                                    @Override
                                    public void onArrival(Postcard postcard) {

                                    }

                                    @Override
                                    public void onInterrupt(Postcard postcard) {

                                    }
                                });
                            }

                    }

                }
        }



    }


    @ReactMethod
    public void debug(String param, Callback callback){
        Log.d(TAG,param.toString());
    }
}
