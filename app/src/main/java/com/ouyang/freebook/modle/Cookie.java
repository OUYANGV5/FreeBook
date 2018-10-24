package com.ouyang.freebook.modle;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cookie {
    private static Map<String,String> cookies;
    static {
        cookies=new HashMap<>();
    }
    public static Map<String,String> getCookieMap(){
        return cookies;
    }
    public static String getCookieString(){
        StringBuilder stringBuilder=new StringBuilder();
        Set<Map.Entry<String, String>> set = cookies.entrySet();
        int x=0;
        for(Map.Entry<String, String> s:set){
            stringBuilder.append(s.getKey()+"="+s.getValue());
            x++;
            if(x<set.size()){
                stringBuilder.append(';');
            }
        }
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }
    public static void putCookie(String key,String cookie){
        if(cookie!=null&&cookie.length()>0)
            cookies.put(key,cookie);
    }
}
