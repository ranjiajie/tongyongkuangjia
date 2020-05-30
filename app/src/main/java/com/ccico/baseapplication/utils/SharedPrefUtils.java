package com.ccico.baseapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefUtils {
    public static final String SHAREPRENFERENCE_NAME = "sharefile";
    private static SharedPreferences sp;

    /**
     * 保存数据到共享参数中
     * @param context
     * @param key  键
     * @param object  值
     */
    public static void setParams(Context context, String key, Object object){

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(object instanceof String){
            editor.putString(key, (String) object);
        }else if(object instanceof Integer){
            editor.putInt(key, (Integer) object);
        }else if(object instanceof Boolean){
            editor.putBoolean(key, (Boolean) object);
        }else if(object instanceof Float){
            editor.putFloat(key, (Float) object);
        }else if(object instanceof Long){
            editor.putLong(key, (Long) object);
        }

        editor.commit();
    }

    /**
     *  获取共享参数，根据默认数据类型获取相对应key的值
     * @param context
     * @param key  键
     * @param defaultObject  默认值
     * @return
     */
    public static Object getParams(Context context, String key, Object defaultObject){

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE);

        if(defaultObject instanceof String){
            return sharedPreferences.getString(key, (String) defaultObject);
        }else if(defaultObject instanceof Integer){
            return sharedPreferences.getInt(key, (Integer) defaultObject);
        }else if(defaultObject instanceof Boolean){
            return sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        }else if(defaultObject instanceof Float){
            return sharedPreferences.getFloat(key, (Float) defaultObject);
        }else if(defaultObject instanceof Long){
            return sharedPreferences.getLong(key, (Long) defaultObject);
        }
        return null;
    }


    //保存string
    public static void putString(Context context, String key, String values) {
        sp = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
        sp.edit().putString(key, values).commit();
    }

    //获取string
    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
        return sp.getString(key, "");
    }

    //保存string
    public static void putInt(Context context, String key, int values) {
        sp = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
        sp.edit().putInt(key, values).commit();
    }

    //获取string
    public static Integer getInt(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
        return sp.getInt(key,0);
    }



    //保存boolean
    public static void putBoolean(Context context, String key, boolean value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
        }
        sp.edit().putBoolean(key, value).commit();
    }

    //获取boolean
    public static boolean getBoolean(Context context, String key, boolean defult) {
        if (sp == null) {
            sp = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
        }
        return sp.getBoolean(key, defult);
    }
}