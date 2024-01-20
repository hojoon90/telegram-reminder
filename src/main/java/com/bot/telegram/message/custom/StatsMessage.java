package com.bot.telegram.message.custom;

import com.bot.telegram.domain.ResultData;
import com.bot.telegram.utils.PushSwitch;
import com.bot.telegram.utils.ServerObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.bot.telegram.common.TelegramConst.COMMAND_STATS;
import static com.bot.telegram.common.TelegramConst.DATE_PATTERN;


@Component
@RequiredArgsConstructor
public class StatsMessage implements CustomMessage {


    private final ServerObserver serverObserver;

    @Override
    public boolean isSupport(String text) {
        return text.contains(COMMAND_STATS);
    }

    /**
     * 서버 상태 체크 후 결과값 생성.
     * @param update
     * @return
     */
    @Override
    public SendMessage getMessage(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        ResultData resultData = makeResultData();

        return SendMessage.builder()
                .text(resultData.getSendMessage())
                .chatId(chatId)
                .build();
    }

    public ResultData makeResultData() {
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN));

        String send_txt = "==" + today + "==\n\n";
        // 서버 리스트 체크
        send_txt = serverObserver.checkServerList(send_txt);
        send_txt += "  \n";
        // IP port 체크
        send_txt = serverObserver.checkIpList(send_txt);
        send_txt += "\n push value = " + PushSwitch.isSendPush();

        return ResultData.builder()
                .sendMessage(send_txt)
                .build();
    }

}
