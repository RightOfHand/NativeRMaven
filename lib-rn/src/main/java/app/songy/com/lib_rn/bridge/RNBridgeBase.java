package app.songy.com.lib_rn.bridge;

import com.facebook.react.bridge.Callback;

/**
 * Description:
 * Created by song on 2018/10/16.
 * email：bjay20080613@qq.com
 */
public class RNBridgeBase {
    public static final int SUCESS = 0;//调用且逻辑处理成功
    public static final int FORMAT_ERROR = -1000;//调用格式错误（一般发生在APP端发现入参中的 module 或 method 参数为空时）
    public static final int NO_LOGIN = -996;//用户未登录（一般发生在接口需要用户处于登录状态，而当前用户未登录）
    public static final int MODEL_NOT_FOUND = -999;//   模块未找到（一般发生在H5的模块输入错误的时候，请检查相关的输入是否正确）
    public static final int METHOD_NOT_FOUND = -998;//    方法未找到（一般发生在H5的方法输入错误的时候，请检查相关的输入是否正确）
    public static final int PARAM_ERROR = -997;// 参数错误（一般发生H5带入的参数与接口需要的参数不致的时候，请检查参数是否正确）
    public static final int USER_CANCEL = -997;// 参数错误（一般发生H5带入的参数与接口需要的参数不致的时候，请检查参数是否正确）
    public static final int UNKNOW_ERROR = -1199;//   未知错误（一般发生在调用异常的情况下，这时候请找相关人员处理）
    public static final int INNER_ERROR = -1200;//内部逻辑错误

    protected JSResult getJSResult() {
        return new JSResult();
    }

    protected void postParamError(Callback callback) {
        if (callback != null) {
            JSResult result = new JSResult();
            result.setCode(PARAM_ERROR);
            result.setMsg("参数错误");
            callback.invoke(result.toJSONObject());
        }
    }
}
