package app.songy.com.lib_rn.bridge;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.hwangjr.rxbus.RxBus;

import app.songy.com.lib_rn.BusActions;
import app.songy.com.lib_rn.MyReactActivity;

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
    public void callNative(String msg){

        Activity mCurrentActvity=getCurrentActivity();
        if (mCurrentActvity instanceof MyReactActivity){
            RxBus.get().post(BusActions.BUS_UPDATE_UI,msg);
        }
    }


}
