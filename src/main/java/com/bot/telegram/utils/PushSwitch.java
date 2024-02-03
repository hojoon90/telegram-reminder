package com.bot.telegram.utils;

public class PushSwitch {
    private static boolean serverPush;
    public static boolean isServerPush() {
        return serverPush;
    }
    public static boolean isNotServerPush() {
        return !serverPush;
    }
    public static void setServerPush(boolean serverPush) {
        PushSwitch.serverPush = serverPush;
    }

}
