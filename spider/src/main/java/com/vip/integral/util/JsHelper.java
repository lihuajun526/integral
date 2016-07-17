package com.vip.integral.util;

import javax.script.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsHelper {

    private static ScriptEngine engine;

    static {
        ScriptEngineManager manager = new ScriptEngineManager();
        engine = manager.getEngineByName("nashorn");
        Bindings engineScope = engine.getBindings(ScriptContext.ENGINE_SCOPE);
        engineScope.put("window", engineScope);
        engineScope.put("navigator", engineScope);
    }

    static {
        InputStream linksStr = JsHelper.class.getClassLoader().getResourceAsStream("js/links.js");
        BufferedReader linksBr = new BufferedReader(new InputStreamReader(linksStr));
        try {
            engine.eval(linksBr);
        } catch (ScriptException ignored) {
            ignored.printStackTrace();
        }
    }

    public static String exe(String method, String str)
            throws ScriptException, NoSuchMethodException {
        Invocable invocable = (Invocable) engine;
        Object result = invocable.invokeFunction(method, str);
        return result.toString();
    }

    public static void main(String[] args) {
        try {
            System.out.println(exe("gfLink", "/AdvForwardServlet?id=20602875&link=http://www.cgbchina.com.cn/Info/20602843"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
