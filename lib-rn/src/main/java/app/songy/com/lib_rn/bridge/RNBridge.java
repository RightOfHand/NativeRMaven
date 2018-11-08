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
//            String moduleName=null;
//            String methodName=null;
//
//            if (param.hasKey("module")){
//                moduleName=param.getString("module");
//            }
//
//            if (param.hasKey("method")){
//                methodName=param.getString("method");
//            }
//            if (moduleName!=null && methodName!=null && moduleName.equals("RN") && methodName.equals("close")){
//                if (mCurrentActvity!=null && mCurrentActvity instanceof ReactContainerActivity){
//                    mCurrentActvity.finish();
//                }
//            }

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


// else if (jsonObject.containsValue("tools") && jsonObject.containsValue("callPhone")){
//                                Log.d(TAG+":support H5 protocol:",JSON.toJSONString(jsonObject));
//                                    if (jsonObject.containsKey("params")){
//                                        JSONObject paramObject=jsonObject.getJSONObject("params");
//
//                                        if (paramObject!=null && paramObject.containsKey("phone")){
//                                            final String phoneNumber=paramObject.getString("phone");
//                                            Log.d(TAG+"phone:",phoneNumber+"");
//                                            if (CheckPermissionUtils.hasPhonePermission(mCurrentActivity)){
//                                                if (phoneNumber!=null) DeviceHelper.callPhone(mCurrentActivity,phoneNumber);
//                                            }else {
//                                                CheckPermissionUtils.checkPhonePermission(mCurrentActivity,new CheckPermissionUtils.PermissionRequestCallback(){
//                                                    @Override
//                                                    public void hasPermission() {
//                                                        if (phoneNumber!=null) DeviceHelper.callPhone(mCurrentActivity,phoneNumber);
//                                                    }
//
//                                                    @Override
//                                                    public void noPermission() {
//
//                                                    }
//                                                });
//                                            }
//                                        }
//
//                                    }
//
//
//
//
//                            }



        }



    }


}
