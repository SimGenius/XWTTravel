package cn.simgenius.commons.internetutilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Genius on 4/6/16.
 */
public class ParamBuilder {

    Map<String,String> map;

    public ParamBuilder() {
        map = new HashMap<String, String>();
    }

    public ParamBuilder add(String key, String value){
        map.put(key,value);
        return this;
    }

    public ParamBuilder add(String key, long value){
        map.put(key,Long.toString(value));
        return this;
    }

    public ParamBuilder add(String key, int value){
        map.put(key,Integer.toString(value));
        return this;
    }

    public ParamBuilder add(String key, boolean value){
        map.put(key,Boolean.toString(value));
        return this;
    }

    public Map<String,String> build(){
        return map;
    }
}


