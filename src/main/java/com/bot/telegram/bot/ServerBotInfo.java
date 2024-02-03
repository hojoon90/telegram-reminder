package com.bot.telegram.bot;

import org.springframework.beans.factory.annotation.Value;

public abstract class ServerBotInfo extends BotManager {
    @Value("${telegram.server-observe-bot.name}")
    public String botName;
    @Value("${telegram.server-observe-bot.token}")
    public String botToken;
    @Value("${telegram.server-observe-bot.chatId}")
    public String chatId;
}
