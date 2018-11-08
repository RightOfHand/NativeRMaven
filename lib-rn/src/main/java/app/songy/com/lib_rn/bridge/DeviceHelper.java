package app.songy.com.lib_rn.bridge;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

/**
 * Description:
 * Created by song on 2018/11/6.
 * email：bjay20080613@qq.com
 */
public class DeviceHelper {

    /**
     * 启动应用的设置
     */
    public static void startAppSettings(Activity activity, int requestCode) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivityForResult(intent, requestCode);
    }



    /**
     * 获取设备的制造商
     */
    public static String getFactory() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取系统版本号
     */
    public static String getPhoneOS() {
        return "Android " + getSysVersion() + " " + Build.VERSION.RELEASE;
    }

    /**
     * 版本是否在Android6.0 以上
     */
    public static boolean isOverMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * 获取Android API版本
     */
    public static String getSysVersion() {
        return Build.VERSION.SDK_INT + "";
    }

    /**
     * 获取Android API版本
     */
    public static int getSysVersionInt() {
        return Build.VERSION.SDK_INT;
    }


    /**
     * 打电话
     */
    public static void callPhone(Activity activity, String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
}
