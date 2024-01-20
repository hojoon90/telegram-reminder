package com.bot.telegram.utils;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.nio.charset.Charset;

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
        HttpEntity entity = new HttpEntity(null, httpHeaders);
        RestTemplate restTemplate  = new RestTemplate();
        String sendUrl = "";

        for (String url : checkUrl) {
            try{
                sendUrl = url.split(",")[1] ;
                ResponseEntity<String> responseEntity = restTemplate.exchange(sendUrl, HttpMethod.GET, entity, String.class);
                if (responseEntity.getStatusCode().value() == 200) {
                    send_txt += url.split(",")[0] + " : success \n";
                } else {
                    send_txt += url.split(",")[0] + " : fail \n";
                }

            } catch (Exception e) {
                send_txt += url + " service : fail \n";
            }
        }
        return send_txt;
    }

    //TODO 공통화 고민
    public String checkIpList(String send_txt) {
        String sendIp = "";

        for (String ip : checkIp) {
            try {
                sendIp = ip.split(",")[1] ;
                InetAddress pingCheck = InetAddress.getByName(sendIp);
                boolean isAlive = pingCheck.isReachable(1000);
                if (isAlive) {
                    send_txt += ip.split(",")[0] + " : success \n";
                } else {
                    send_txt += ip.split(",")[0] + " : fail \n";
                }
            } catch (Exception e) {
                send_txt += ip + " service : fail \n";
            }
        }

        return send_txt;
    }

}
