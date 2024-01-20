package com.bot.telegram.message.custom;

import com.bot.telegram.utils.PushSwitch;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.bot.telegram.common.TelegramConst.COMMAND_PUSH;


@Component
public class PushNotiMessage implements CustomMessage{

    @Override
    public boolean isSupport(String text) {
        return text.contains(COMMAND_PUSH);
    }

    @Override
    public SendMessage getMessage(Update update) {
        String text = update.getMessage().getText();
        String chatId = update.getMessage().getChatId().toString();

        String messageT = text + " 는 없는 명령어입니다.";
        if (text.contains("on")) {
            PushSwitch.setSendPush(true);
            messageT = "push stats : on";
        }

        if (text.contains("off")){
            PushSwitch.setSendPush(false);
            messageT = "push stats : off";
        }

        return SendMessage.builder()
                .chatId(chatId)
                .text(messageT)
                .build();
    }

}
