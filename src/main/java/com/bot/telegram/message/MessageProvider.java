package com.bot.telegram.message;

import com.bot.telegram.message.custom.CustomMessage;
import com.bot.telegram.message.custom.NoMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 메세지 타입에 따라 알맞은 메세지 클래스를 찾은 후 반환해준다.
 * messageList는 MessageProvider를 Bean으로 등록할 때 등록한 메세지들의 리스트를 갖는다.
 * text에 알맞은 클래스가 없을 경우 NoMessage 클래스를 리턴한다.
 */
@Component
@RequiredArgsConstructor
public class MessageProvider {

    private final List<CustomMessage> messageList;

    public CustomMessage getCustomMessage(String text) {
        for (CustomMessage customMessage : messageList) {
            if (customMessage.isSupport(text)) {
                return customMessage;
            }
        }

        // 메세지에 포함되지 않으면 NoMessage 리턴
        return messageList.stream()
                .filter(message -> message instanceof NoMessage)
                .findFirst()
                .get();
    }

}
