package com.allstars.pudge.service;

import cn.hutool.core.date.DateUtil;
import com.allstars.pudge.client.HuoBiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author zz
 * @date 2020/7/16 3:07 下午
 */
@Service
public class HuobiImpl implements Huobi {
    @Autowired
    private HuoBiClient huoBiClient;
    static final DateTimeFormatter DT_FORMAT = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss");

    static final ZoneId ZONE_GMT = ZoneId.of("Z");
    @Override
    public void getAccounts() {
        /**
         * https://api.testnet.huobi.pro/v1/account/accounts?AccessKeyId=ghxertfvbf-39250d99-7ed9298f-04e81&SignatureMethod=HmacSHA256&SignatureVersion=2
         */
        String accessKeyId = "ghxertfvbf-39250d99-7ed9298f-04e81";
        String signatureMethod = "HmacSHA256";
        int signatureVersion = 2;
        String timestamp = Instant.ofEpochSecond(Instant.now().getEpochSecond()).atZone(ZONE_GMT).format(DT_FORMAT);
        Map<String, Object> a = Map.of(
                "AccessKeyId", accessKeyId,
                "SignatureMethod", signatureMethod,
                "SignatureVersion", signatureVersion,
                "Timestamp", timestamp
        );
        ResponseEntity<Map> accounts = huoBiClient.getAccounts(a);
        System.out.println(accounts);
    }


}
