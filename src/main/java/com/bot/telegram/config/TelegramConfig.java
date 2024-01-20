package com.bot.telegram.config;

import com.bot.telegram.message.MessageProvider;
import com.bot.telegram.message.custom.*;
import com.bot.telegram.service.ServerObserveBot;
import com.bot.telegram.utils.ServerObserver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class TelegramConfig {

    /**
     * 서버 챗봇 등록
     */

    /**
     * 메세지 등록기
     * @return
     */
    @Bean
    public MessageProvider messageProvider() {
        List<CustomMessage> messageList = new ArrayList<>();
        messageList.add(new HelpMessage());
        messageList.add(new PushNotiMessage());
        messageList.add(new StatsMessage(new ServerObserver()));
        messageList.add(new NoMessage());

        return new MessageProvider(messageList);
    }

    /**
     * 텔레그램 봇 공통 등록 빈
     * @return
     * @throws TelegramApiException
     */
    @Bean
    public void registerBot() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new ServerObserveBot(messageProvider()));
    }



}
