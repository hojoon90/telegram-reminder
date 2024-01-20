package com.bot.telegram.message.custom;

import com.bot.telegram.utils.TeleStringUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.bot.telegram.common.TelegramConst.*;


@Component
public class ShellMessage implements CustomMessage{

    @Override
    public boolean isSupport(String text) {
        return text.contains(COMMAND_SHELL);
    }

    /**
     * docker 관련 명령어를 처리.
     * <p>
     * ex) <br>
     * docker ps <br>
     * docker node ls <br>
     * docker service ls <br>
     * docker service ps <br>
     *</p>
     * @param update
     * @return
     */
    @Override
    public List<SendMessage> makeMultipleMessage(Update update) {
        String text = update.getMessage().getText();
        String chatId = update.getMessage().getChatId().toString();
        List<SendMessage> sendMessages = new ArrayList<>();

        text = text.replaceAll("/"+ COMMAND_SHELL, "");
        text = TeleStringUtils.convertStringData(REMOTE_COMMAND, text);
        List<String> textList = makeMessageText(text);

        textList.forEach(sendText ->
                sendMessages.add(
                    SendMessage.builder()
                            .text(sendText)
                            .chatId(chatId)
                            .build()
                ));
        return sendMessages;
    }

    /**
     * 허용 안되는 명령어 조건
     * update , rm , stop , run, create  ==> 사용안됨
     * @param text
     * @return
     */
    private boolean isNotPermittedText(String text) {
        return text.contains(COMMAND_UPDATE)
                || text.contains(COMMAND_RM)
                || text.contains(COMMAND_STOP)
                || text.contains(COMMAND_RUN)
                || text.contains(COMMAND_CREATE)
                || !text.contains(COMMAND_DOCKER) // not
                ;
    }

    /**
     * 메세지 생성 메서드
     * @param text
     * @return
     */
    private List<String> makeMessageText(String text) {
        String[] returnTxt = new String[100];
        String[] cmd = new String[]{"/bin/sh", "-c", text};

        if (isNotPermittedText(text)) {
            returnTxt[0] = text + " 는 사용할 수 없는 명령어입니다.";
            return Arrays.asList(returnTxt);
        }

        try {
            int i = 0;
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "MS949"));
            Scanner scanner = new Scanner(br);
            scanner.useDelimiter(System.getProperty("line.separator"));
            while (scanner.hasNext()) {
                if (returnTxt[i / 8] == null) returnTxt[i / 8] = "";
                returnTxt[i / 8] += scanner.next() + "\n";
                i++;
            }
            scanner.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Arrays.asList(returnTxt);
    }

}
