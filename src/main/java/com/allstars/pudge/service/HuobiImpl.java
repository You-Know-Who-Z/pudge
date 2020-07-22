package com.allstars.pudge.service;

import com.allstars.pudge.client.HuoBiClient;
import com.allstars.pudge.utils.JsonUtil;
import com.allstars.pudge.utils.OkHttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zz
 * @date 2020/7/16 3:07 下午
 */
@Service
@Slf4j
public class HuobiImpl implements Huobi {
    @Override
    public void getAccounts() {
        /**
         * https://api.testnet.huobi.pro/v1/account/accounts?AccessKeyId=ghxertfvbf-39250d99-7ed9298f-04e81&SignatureMethod=HmacSHA256&SignatureVersion=2
         */
        String accessKeyId = "ghxertfvbf-39250d99-7ed9298f-04e81";
        String appSecretKey = "094cbd2f-aad15488-84d7ac78-f8c23";
        Map<String, String> params = new HashMap<>(4);
//        HuobiSignature huobiSignature = new HuobiSignature();
//        huobiSignature.createSignature(accessKeyId, appSecretKey, "get", "api.testnet.huobi.pro","/v1/account/accounts", params);
        HuoBiClient huoBiClient = new HuoBiClient(accessKeyId, appSecretKey);
        String a = huoBiClient.commonSymbols();
        System.out.println(a);
    }
}
