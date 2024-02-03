package com.bot.telegram.message.custom;

import org.springframework.beans.factory.annotation.Value;

public abstract class TokenManager {
    @Value("${telegram.server-observe-bot.token}")
    protected String serverBotToken;
}
