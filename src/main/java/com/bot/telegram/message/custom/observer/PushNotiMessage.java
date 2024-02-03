package com.bot.telegram.message.custom.observer;

import com.bot.telegram.message.custom.CustomMessage;
import com.bot.telegram.message.custom.TokenManager;
import com.bot.telegram.utils.PushSwitch;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.bot.telegram.common.TelegramConst.COMMAND_PUSH;


@Component
public class PushNotiMessage extends TokenManager implements CustomMessage {

    @Override
    public boolean isSupport(String text, String botToken) {
        return text.contains(COMMAND_PUSH) && this.serverBotToken.equals(botToken);
    }
    @Override
    public SendMessage getMessage(Update update) {
        String text = update.getMessage().getText();
        String chatId = update.getMessage().getChatId().toString();

        String messageT = text + " 는 없는 명령어입니다.";
        if (text.contains("on")) {
            PushSwitch.setServerPush(true);
            messageT = "push stats : on";
        }

        if (text.contains("off")){
            PushSwitch.setServerPush(false);
            messageT = "push stats : off";
        }

        return SendMessage.builder()
                .chatId(chatId)
                .text(messageT)
                .build();
    }

}
