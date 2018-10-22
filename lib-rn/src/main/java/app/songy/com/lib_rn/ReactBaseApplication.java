package app.songy.com.lib_rn;

import android.app.Application;
import android.content.Context;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import java.util.Arrays;
import java.util.List;

import app.songy.com.lib_rn.bridge.RNBridgePackage;

/**
 * Description:
 * Created by song on 2018/9/26.
 * email：bjay20080613@qq.com
 */
public class ReactBaseApplication extends Application implements ReactApplication {
    private static final String FILE_NAME = "index.android";
    private static Context context;

    public static Context gedAppContext(){
        return context;
    }

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }


        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new RNBridgePackage()

            );
        }

        @Override
        protected String getJSMainModuleName() {
            return "index";
        }

        @javax.annotation.Nullable
        @Override
        protected String getJSBundleFile() {
            return super.getJSBundleFile();
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);
        context=getApplicationContext();
    }

}
