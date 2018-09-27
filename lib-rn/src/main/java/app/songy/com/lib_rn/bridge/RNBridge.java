package app.songy.com.lib_rn.bridge;

import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

/**
 * Description:
 * Created by song on 2018/9/26.
 * email：bjay20080613@qq.com
 */
@ReactModule(name="RNBridge")
public class RNBridge extends ReactContextBaseJavaModule {
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
        Toast.makeText(getCurrentActivity(),"react lib"+msg,Toast.LENGTH_LONG).show();
    }


}
