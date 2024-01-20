package com.bot.telegram.batch;

import com.bot.telegram.service.ServerObserveBot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class BatchWork {

    private final ServerObserveBot serverCheckBot;

    @Scheduled(fixedRate = 1000 * 60 * 10)
    public void checkServer() {
        serverCheckBot.checkServerStatus();
    }


}
