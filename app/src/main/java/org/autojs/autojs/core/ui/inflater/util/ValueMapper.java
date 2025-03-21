package org.autojs.autojs.core.ui.inflater.util;

import android.view.InflateException;

import java.util.HashMap;

/**
 * Created by Stardust on Nov 3, 2017.
 */
public class ValueMapper<V> {

    private final HashMap<String, V> mHashMap = new HashMap<>();
    private final String mAttrName;

    public ValueMapper(String attrName) {
        mAttrName = attrName;
    }

    public ValueMapper<V> map(String key, V value) {
        mHashMap.put(key, value);
        return this;
    }

    public ValueMapper<V> mapDefault(String key, V value) {
        mHashMap.put(key, value);
        mHashMap.put("", value);
        return this;
    }

    public ValueMapper<V> mapDefault(V value) {
        mHashMap.put("", value);
        return this;
    }

    public V get(String key, V defValue) {
        V v = mHashMap.get(key);
        if (v == null) {
            return defValue;
        }
        return v;
    }

    public V get(String key) {
        V v = mHashMap.get(key);
        if (v == null) {
            throw new InflateException(String.format("Unknown value for %s: %s", mAttrName, key));
        }
        return v;
    }

    public int split(String str){
        int r = 0;
        for(String s : str.split("\\|")){
            r |= (Integer) get(s);
        }
        return r;
    }

}
