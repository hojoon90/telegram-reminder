package com.bot.telegram.config;

import com.bot.telegram.message.MessageProvider;
import com.bot.telegram.message.custom.*;
import com.bot.telegram.message.custom.observer.PushNotiMessage;
import com.bot.telegram.message.custom.observer.ShellMessage;
import com.bot.telegram.message.custom.observer.StatsMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class TelegramConfig {

    private final HelpMessage helpMessage;
    private final NoMessage noMessage;

    private final PushNotiMessage pushNotiMessage;
    private final StatsMessage statsMessage;
    private final ShellMessage shellMessage;

    /**
     * 메세지 등록기
     *
     * @return
     */
    @Bean
    public MessageProvider messageProvider() {
        List<CustomMessage> messageList = new ArrayList<>();
        //범용 메세지
        messageList.add(helpMessage);
        messageList.add(noMessage);

        //서버봇 메세지
        messageList.add(pushNotiMessage);
        messageList.add(statsMessage);
        messageList.add(shellMessage);


        return new MessageProvider(messageList);
    }


}
