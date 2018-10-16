package app.songy.com.lib_rn.bridge;

import android.app.Activity;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.hwangjr.rxbus.RxBus;

import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.Map;

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

    private DataCallBack mDataCallBack;

    public RNBridge(ReactApplicationContext reactContext) {
        super(reactContext);
        this.mContext=reactContext;
    }

    public void setDataCallBack(DataCallBack mDataCallBack) {
        this.mDataCallBack = mDataCallBack;
    }

    @Override
    public String getName() {
        return "RNBridge";
    }

    @ReactMethod
    public void send(String data,Callback callback){
        Activity mCurrentActvity=getCurrentActivity();
        Map<String,Object> datas=new HashMap<>();
        datas.put(RNConstants.RN_MODULE_NAME,"RN");
        datas.put(RNConstants.RN_METHOD_NAME,"close");
        Map<String,Object> childData=new HashMap<>();
        childData.put(RNConstants.RN_PARAM_BUNDLE_NAME,"index.android.bundle");
        childData.put(RNConstants.RN_PARAM_JS_MAIN_PATH,"index");
        childData.put(RNConstants.RN_PARAM_MODULE_NAME,"android");
        datas.put(RNConstants.RN_PARAMS,childData);
        data=JSON.toJSONString(datas);
        Log.d(TAG,"native accepts data:"+data);
        if (data!=null){
            RxBus.get().post(RNConstants.BUS_UPDATE_U,data);
            JSONObject jsonObject=JSON.parseObject(data);
            String closeFlag=jsonObject.getString(RNConstants.RN_METHOD_NAME);
            if (closeFlag!=null && closeFlag.equals("close")){
                if (mCurrentActvity instanceof ReactContainerActivity){

                }
            }
//            JSONObject dataJson=JSON.parseObject(data);
//           Log.d(TAG,dataJson.get("params").toString());

        }


    }


}
