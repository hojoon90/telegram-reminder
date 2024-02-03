package com.bot.telegram.message.custom;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface CustomMessage {

    boolean isSupport(String text, String botToken);

    Object getMessage(Update update);

}
