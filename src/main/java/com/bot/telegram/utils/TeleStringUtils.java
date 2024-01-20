package com.bot.telegram.utils;

public class TeleStringUtils {
    public static String convertStringData(String originStr, Object... args){
        for(int i = 0; i < args.length; i++){
            originStr = originStr.replaceFirst("\\{\\}", args[i].toString());
        }
        return originStr;
    }
}
