package com.bot.telegram.message.custom;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

public interface CustomMessage {

    boolean isSupport(String text);
    default SendMessage getMessage(Update update){
        return new SendMessage();
    }
    default List<SendMessage> makeMultipleMessage(Update update){
        return new ArrayList<>();
    }
}
