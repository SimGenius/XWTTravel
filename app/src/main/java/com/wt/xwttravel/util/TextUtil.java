package com.wt.xwttravel.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Genius on 2/2/16.
 */
public class TextUtil {

    public static String getPriceText(float price){
        return  "￥"+Float.toString(price).replace(".0","");
    }

    public static String getOrderStatusText(int status){
        switch (status){
            case 1:
                return "已支付";
            case 2:
                return "等待支付";
            case 3:
                return "已取消";
            default:
                return "正在处理";
        }
    }

    public static String getDateString(long date){

        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH,0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        today.setHours(0);
        today.setMinutes(0);
        today.setSeconds(0);
        if(date>=today.getTime()){
            SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm");
            return timeFormat.format(new Date(date));
        }else {
            long d = today.getTime() - date;
            if(d <= 86400000){
                return "昨天";
            }else if(d > 86400000 && d <= 2*86400000){
                return "前天";
            }else if(date < calendar.getTimeInMillis()){
                DateFormat dateFormat  = new SimpleDateFormat("yyyy-M-d");
                return dateFormat.format(date);
            }else {
                DateFormat dateFormat  = new SimpleDateFormat("M-d");
                return dateFormat.format(date);
            }

        }
    }

}
