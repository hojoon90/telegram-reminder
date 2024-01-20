package com.bot.telegram.service;


import com.bot.telegram.message.MessageProvider;
import com.bot.telegram.message.custom.CustomMessage;
import com.bot.telegram.message.custom.ShellMessage;
import com.bot.telegram.utils.PushSwitch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

import static com.bot.telegram.common.TelegramConst.COMMAND_STATS;
import static com.bot.telegram.common.TelegramConst.FAIL;


@Slf4j
@Component
@RequiredArgsConstructor
public class ServerObserveBot extends TelegramLongPollingBot {

    @Value("${telegram.server-observe-bot.name}")
    private String telegramBotName;
    @Value("${telegram.server-observe-bot.token}")
    private String telegramBotToken;
    @Value("${telegram.server-observe-bot.chatId}")
    private String chatId;

    private final MessageProvider messageProvider;

    @Override
    public String getBotUsername() {
        return telegramBotName;
    }

    @Override
    public String getBotToken() {
        return telegramBotToken;
    }

    /**
     * 사용자가 보낸 메세지에 대해 답변메세지 전달
     * @param update Update received
     */
    @Override
    public void onUpdateReceived(Update update) {
        String text = update.getMessage().getText();
        CustomMessage customMessage = messageProvider.getCustomMessage(text);

        if (customMessage instanceof ShellMessage) {
            List<SendMessage> sendMessages = customMessage.makeMultipleMessage(update);
            sendMessages.forEach(message -> sendMessageToUser(message));
        } else {
            sendMessageToUser(customMessage.getMessage(update));
        }
    }

    /**
     * 서버 상태 체크
     */
    public void checkServerStatus() {
        if (PushSwitch.isSendPush()) {
            Update update = new Update();
            update.getMessage().getChat().setId(Long.parseLong(chatId));

            CustomMessage statsMessage = messageProvider.getCustomMessage(COMMAND_STATS);
            SendMessage sendMessage = statsMessage.getMessage(update);

            //만약 체크 시 실패값이 있으면 메세지를 보낸다.
            if(sendMessage.getText().contains(FAIL)){
                sendMessageToUser(sendMessage);
            }
        }
    }

    /**
     * 메세지 발송
     *
     * @param sendMessage
     */
    public void sendMessageToUser(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e1) {
            e1.printStackTrace();
        }
    }

}
