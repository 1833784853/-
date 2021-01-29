package com.imcode.common.model;

import java.util.HashMap;

public class R extends HashMap<String, Object> {
    public static R ok() {
        R r = new R();
        r.put("code",200);
        r.put("msg","请求成功");
        return r;
    }
    public static R ok(String msg) {
        R r = new R();
        r.put("code",200);
        r.put("msg",msg);
        return r;
    }

    public static R ok(String msg,Object data) {
        R r = new R();
        r.put("code",200);
        r.put("msg",msg);
        r.put("data",data);
        return r;
    }

    public static R error(String msg) {
        R r = new R();
        r.put("code",400);
        r.put("msg",msg);
        return r;
    }
    public static R Info(int code,String msg) {
        R r = new R();
        r.put("code",code);
        r.put("msg",msg);
        return r;
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
