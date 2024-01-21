package com.bot.telegram.utils;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.List;

@Component
public class ServerObserver {

    public static String[] checkUrl = {
        //TODO URL 등록
    };

    public static String[] checkIp = {
        //TODO IP 등록
    };

    //TODO 공통화 고민
    public String checkServerList(String send_txt) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        send_txt = makeStatusMessage(send_txt, checkUrl);
        return send_txt;
    }

    //TODO 공통화 고민
    public String checkIpList(String send_txt) {
        send_txt = makeStatusMessage(send_txt, checkIp);
        return send_txt;
    }

    private boolean checkServerAlive(String sendUrl, HttpEntity entity, RestTemplate restTemplate) throws IOException {
        ResponseEntity<String> responseEntity = restTemplate.exchange(sendUrl, HttpMethod.GET, entity, String.class);
        boolean isAlive = responseEntity.getStatusCode().value() == HttpStatus.OK.value();

        return isAlive;
    }

    private boolean checkIpAlive(String sendIp) throws IOException {
        InetAddress pingCheck = InetAddress.getByName(sendIp);
        boolean isAlive = pingCheck.isReachable(1000);

        return isAlive;
    }

    public String makeStatusMessage(String send_txt, String[] list) {
        String temp = "";

        for (String source : list) {
            try {
                temp = source.split(",")[1] ;
                InetAddress pingCheck = InetAddress.getByName(temp);
                boolean isAlive = pingCheck.isReachable(1000);
                if (isAlive) {
                    send_txt += source.split(",")[0] + " : success \n";
                } else {
                    send_txt += source.split(",")[0] + " : fail \n";
                }
            } catch (Exception e) {
                send_txt += source + " service : fail \n";
            }
        }



        return send_txt;
    }

}
