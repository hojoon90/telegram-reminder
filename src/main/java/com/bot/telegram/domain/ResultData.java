package com.bot.telegram.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultData {
    String sendMessage;
    int failCount;
}
