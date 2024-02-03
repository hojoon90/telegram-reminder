package com.bot.telegram.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public abstract class BotManager extends TelegramLongPollingBot {

    /**
     * 더미 업데이트 생성
     *
     * @return
     */
    public Update initDummyUpdate() {
        Update dummyUpdate = new Update();
        dummyUpdate.setMessage(new Message());
        dummyUpdate.getMessage().setChat(new Chat());

        return dummyUpdate;
    }

    /**
     * 사용자에게 메세지 전달
     *
     * @param sendMessageObj
     */
    public void sendMessageToUser(Object sendMessageObj) {
        try {
            if (sendMessageObj instanceof List) {
                List<SendMessage> sendMessageList = (List<SendMessage>) sendMessageObj;
                for (SendMessage sendMessage : sendMessageList) {
                    execute(sendMessage);
                }
            } else {
                SendMessage sendMessage = (SendMessage) sendMessageObj;
                execute(sendMessage);
            }
        } catch (TelegramApiException e1) {
            e1.printStackTrace();
        }
    }

}
