package com.example.gingerbreadcooking.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Constant {
    public static boolean validateCardExpireDate( Context context,String card_exp_date){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR) % 100;
        int month = calendar.get(Calendar.MONTH)+1;
        String date = card_exp_date.replaceAll("/", "");
        if(Integer.parseInt((date.substring(2)))>=year){
            if(Integer.parseInt((date.substring(2)))==year){
                if(Integer.parseInt((date.substring(0,2)))>=month){
                    // Toast.makeText(context,"valid date",Toast.LENGTH_LONG).show();
                    return true;
                }else{
                    //Toast.makeText(context,"invalid",Toast.LENGTH_LONG).show();
                    return false;
                }
            }else{

                if(Integer.parseInt((date.substring(0,2)))<=12 && Integer.parseInt((date.substring(0,2)))!=00){
                    //Toast.makeText(context,"valid date",Toast.LENGTH_LONG).show();
                    return true;
                }
                else{
                    //Toast.makeText(context,"invalid",Toast.LENGTH_LONG).show();
                    return false;
                }

            }


        }else{
            // Toast.makeText(context,"invalid",Toast.LENGTH_LONG).show();
            return false;
        }
    }
    public static String getPremiumStatus(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("primum","");
    }

    public static void setPremiumStatus(Context context , String s){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("primum", s).commit();
    }
    public static String getLastUpdateDate(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("date","");
    }

    public static void setLastUpdateDate(Context context , String s){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("date", s).commit();
    }

    public static  String getCurrentDate(){
        SimpleDateFormat sdf =  new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        return  currentDateandTime;
    }

    public static boolean  chkForUpdate(Context context){
        SimpleDateFormat sdf =  new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        String lastDate =getLastUpdateDate(context);
        try {
            Date date1= sdf.parse(lastDate);
            Date date2 = sdf.parse(currentDateandTime);

          return   printDifference(date1, date2);
        } catch (ParseException e) {
            e.printStackTrace();
    }
        return false;
    }
    public static boolean printDifference(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;
               if(elapsedDays>=15){
                   return true;
               }
        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);
               return false;
    }
}
