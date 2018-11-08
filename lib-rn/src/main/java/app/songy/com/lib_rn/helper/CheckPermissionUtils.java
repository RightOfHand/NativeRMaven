package app.songy.com.lib_rn.helper;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;


/**
 *Description:
 *creator: song
 *Date: 2018/7/5 下午3:46
 */

public class CheckPermissionUtils {


    private final static String[] phonePermissions = {Manifest.permission.CALL_PHONE};//相机权限
    private final static String[] necessaryPermissions = {Manifest.permission.READ_PHONE_STATE
            , Manifest.permission.WRITE_EXTERNAL_STORAGE};//必要权限

    private static final int REQUEST_PHONE_PERMISSION = 0x11;

    public interface PermissionRequestCallback {
        void hasPermission();

        void noPermission();
    }

    /**
     * 通用检查权限方法
     *
     * @param context               BaseActivity
     * @param permissions           权限数组
     * @param requestPermissionCode 请求权限时的activity 请求码
     * @param permissionName        权限中文名称
     * @param callback              权限回调
     */
    public static void checkPermission(final Activity context, final String[] permissions, final int requestPermissionCode, final String permissionName, final PermissionRequestCallback callback) {
        if (hasPermission(context, permissions)) {
            Log.d("检查结果：有%s权限", permissionName);
            callback.hasPermission();
        } else {
            Log.d("检查结果：没有%s权限", permissionName);
            ActivityCompat.requestPermissions(context, permissions, requestPermissionCode);
        }
    }

    public static boolean shouldShowRequestPermissionRationale(Activity context, String[] permissions) {
        for (int i = 0; i < permissions.length; i++) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, permissions[i])) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasPermission(Activity context, String[] permissions) {
        for (int i = 0; i < permissions.length; i++) {
            if (ActivityCompat.checkSelfPermission(context, permissions[i])
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查权限结果
     *
     * @param grantResults    权限列表
     * @param permissionCount 申请权限数
     */
    private static boolean checkGrantResult(int[] grantResults, int permissionCount) {
        for (int i = 0; i < permissionCount; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static void checkPhonePermission(final Activity context, final PermissionRequestCallback callback) {
        checkPermission(context, phonePermissions, REQUEST_PHONE_PERMISSION, "拨打电话", callback);
    }

    /**
     * 是否有电话权限
     *
     * @param context Activity
     */
    public static boolean hasPhonePermission(Activity context) {
        return hasPermission(context, phonePermissions);
    }
}
