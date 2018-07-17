package com.example.shirodemo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConstantUtil {
    public static final String DATE_OBJ_2= "yyyy-MM-dd HH:mm:ss";


    //date转String
    public static String dateToString(Date date, String obj){
        SimpleDateFormat sdf = new SimpleDateFormat(obj);
        String time = "";
        try {
            if(date != null)
                time = sdf.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return time;
    }

}
