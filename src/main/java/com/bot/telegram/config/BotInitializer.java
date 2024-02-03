package com.bot.telegram.config;

import com.bot.telegram.service.ServerObserveBot;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@Component
@RequiredArgsConstructor
public class BotInitializer {

    private final ServerObserveBot serverObserveBot;

    /**
     * 텔레그램 봇 초기화
     * @return
     * @throws TelegramApiException
     */
    @PostConstruct
    public void registerBot() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(serverObserveBot);
    }

}
