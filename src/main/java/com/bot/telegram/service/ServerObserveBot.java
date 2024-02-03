package com.bot.telegram.service;


import com.bot.telegram.bot.ServerBotInfo;
import com.bot.telegram.message.MessageProvider;
import com.bot.telegram.message.custom.CustomMessage;
import com.bot.telegram.message.custom.observer.ShellMessage;
import com.bot.telegram.utils.PushSwitch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

import static com.bot.telegram.common.TelegramConst.COMMAND_STATS;
import static com.bot.telegram.common.TelegramConst.FAIL;


@Slf4j
@Component
@RequiredArgsConstructor
public class ServerObserveBot extends ServerBotInfo {

    private final MessageProvider messageProvider;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    /**
     * 사용자가 보낸 메세지에 대해 답변메세지 전달
     * @param update Update received
     */
    @Override
    public void onUpdateReceived(Update update) {
        CustomMessage customMessage = messageProvider.getCustomMessage(update, getBotToken());
        sendMessageToUser(customMessage.getMessage(update));
    }

    /**
     * 서버 상태 체크
     */
    public void checkServerStatus() {
        if (PushSwitch.isNotServerPush()) { //push off
            return;
        }
        Update update = initDummyUpdate();
        update.getMessage().setText(COMMAND_STATS);
        update.getMessage().getChat().setId(Long.parseLong(chatId));

        CustomMessage statsMessage = messageProvider.getCustomMessage(update, getBotToken());
        SendMessage sendMessage = (SendMessage) statsMessage.getMessage(update);

        //만약 체크 시 실패값이 있으면 메세지를 보낸다.
        if (sendMessage.getText().contains(FAIL)) {
            sendMessageToUser(sendMessage);
        }
    }

}
