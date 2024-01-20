package com.bot.telegram.utils;

public class PushSwitch {

    private static boolean sendPush;
    public static boolean isSendPush() {
        return sendPush;
    }
    public static void setSendPush(boolean sendPush) {
        PushSwitch.sendPush = sendPush;
    }

}
