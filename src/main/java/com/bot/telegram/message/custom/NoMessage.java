package com.bot.telegram.message.custom;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class NoMessage implements CustomMessage{

    @Override
    public boolean isSupport(String text) {
        return false;
    }

    @Override
    public SendMessage getMessage(Update update) {
        String text = update.getMessage().getText();
        String chatId = update.getMessage().getChatId().toString();

        String noMessage = text + " 는 없는 명령어입니다.";
        return SendMessage.builder()
                .chatId(chatId)
                .text(noMessage)
                .build();
    }
}
