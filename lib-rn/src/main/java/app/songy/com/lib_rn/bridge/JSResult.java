package app.songy.com.lib_rn.bridge;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Description:返回 数据结构
 * Created by song on 2018/10/15.
 * email：bjay20080613@qq.com
 */
public class JSResult {
    private int code = 0;
    private Object data;
    private String msg;

    public JSResult() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static JSONObject putKeyValue(String key, Object object) {
        JSONObject jo = new JSONObject();
        jo.put(key, object);
        return jo;
    }

    public static JSONObject putKeyValue(JSONObject jo, String key, Object object) {
        if (jo == null) {
            jo = new JSONObject();
        }

        jo.put(key, object);
        return jo;
    }

    public JSONObject toJSONObject() {
        return JSON.parseObject(JSON.toJSONString(this));
    }
}
