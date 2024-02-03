package com.bot.telegram.common;

public class TelegramConst {

    //Command
    public static final String COMMAND_PUSH = "push";
    public static final String COMMAND_SHELL = "shell";
    public static final String COMMAND_STATS = "stats";
    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_UPDATE = "update";
    public static final String COMMAND_RM = "rm";
    public static final String COMMAND_STOP = "stop";
    public static final String COMMAND_RUN = "run";
    public static final String COMMAND_CREATE = "create";
    public static final String COMMAND_DOCKER = "docker";

    public static final String FAIL = "fail";

    // Message
    public static final String HELP_MESSAGE = "/stats 서버 healthCheck \n/push [on|off] 메시지 전송 여부 \n/shell [docker..] 서버 정보 확인";
    public static final String DATE_PATTERN = "yyyy년 MM월 dd일 HH시 mm분 ss초";
    public static final String REMOTE_COMMAND = "ssh -i ~/.ssh/id_rsa -T app@{} '{}'";


    public static final String HTTPS_COLON_SLASH = "https://";


}
