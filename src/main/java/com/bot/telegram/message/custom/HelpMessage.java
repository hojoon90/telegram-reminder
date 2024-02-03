package com.bot.telegram.message.custom;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.bot.telegram.common.TelegramConst.COMMAND_HELP;
import static com.bot.telegram.common.TelegramConst.HELP_MESSAGE;

@Component
public class HelpMessage implements CustomMessage{


    @Override
    public boolean isSupport(String text, String botToken) {
        return text.contains(COMMAND_HELP);
    }

    @Override
    public SendMessage getMessage(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String text = HELP_MESSAGE;

        return SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build();
    }
}
