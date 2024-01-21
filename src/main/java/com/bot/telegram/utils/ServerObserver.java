package com.bot.telegram.utils;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
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

    private HttpHeaders httpHeaders;
    private HttpEntity entity;
    private RestTemplate restTemplate;

    public String checkServerList(String send_txt) {
        //TODO 전역 처리 잘 되는지 확인 필ㅇ요
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        entity = new HttpEntity(null, httpHeaders);
        restTemplate  = new RestTemplate();

        return makeText(checkUrl, send_txt);
    }

    public String checkIpList(String send_txt) {
        return makeText(checkIp, send_txt);
    }

    public String makeText(String[] checkList, String send_txt) {
        for (String checkValue : checkList) {
            try {
                String source = checkValue.split(",")[1];
                boolean isAlive = checkAlive(source);
                send_txt += makeStatusMessage(isAlive, checkValue);
            } catch (Exception e) {
                send_txt += checkValue + " service : fail \n";
            }
        }
        return send_txt;
    }

    public boolean checkAlive(String source) throws IOException {
        boolean isAlive;
        //TODO IP, URL 구분 방식 체크 필요
        if(source.contains("\\.")){
            ResponseEntity<String> responseEntity = restTemplate.exchange(source, HttpMethod.GET, entity, String.class);
            isAlive = responseEntity.getStatusCode().value() == HttpStatus.OK.value();
        }else{
            InetAddress pingCheck = InetAddress.getByName(source);
            isAlive = pingCheck.isReachable(1000);
        }

        return isAlive;
    }

    public String makeStatusMessage(boolean isOk, String checkValue) {
        String value = isOk ? " : success \n" : " : fail \n";
        return checkValue.split(",")[0] + value ;
    }

}
