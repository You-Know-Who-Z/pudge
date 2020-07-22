package com.allstars.pudge.client;


import com.allstars.pudge.service.HuobiSignature;
import com.allstars.pudge.utils.JsonUtil;
import com.allstars.pudge.utils.OkHttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zz
 * @date 2020/7/14 5:28 下午
 */
@Slf4j
public class HuoBiClient {
    //    private String accessKeyId = "ghxertfvbf-39250d99-7ed9298f-04e81";
//    private String accessKeySecret = "094cbd2f-aad15488-84d7ac78-f8c23";
    public static final String HUOBI_PRO_API_HOST = "api.testnet.huobi.pro";
    public static final String HUOBI_HADAX_API_HOST = "api.hadax.com";
    String API_HOST;
    String API_URL;
    final String accessKeyId;
    final String accessKeySecret;

    /**
     * 创建一个ApiClient实例
     *
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     */
    public HuoBiClient(String accessKeyId, String accessKeySecret) {
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.API_URL = "https://" + HUOBI_PRO_API_HOST;
        this.API_HOST = HUOBI_PRO_API_HOST;
    }

    /**
     * 查询所有账户信息
     *
     * @return .
     */
    public String getAccounts() {
        return get("/v1/account/accounts", null);
    }

    /**
     * send a GET request
     *
     * @param uri
     * @param params
     * @return
     */
    private String get(String uri, Map<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        return call("GET", uri, null, params);
    }

    /**
     * send a POST request
     *
     * @param uri
     * @param object
     * @return
     */
    private String post(String uri, Object object) {
        return call("POST", uri, object, new HashMap<String, String>());
    }

    /**
     * call api by endpoint
     *
     * @param method
     * @param uri
     * @param object
     * @param params
     * @return
     */
    private String call(String method, String uri, Object object, Map<String, String> params) {
        log.trace("准备开始调用 Huobi API 调用方法 {} url {}", method, uri);
        HuobiSignature signature = new HuobiSignature();
        signature.createSignature(this.accessKeyId, this.accessKeySecret, method, API_HOST, uri, params);
        try {
            Request.Builder builder;
            String url = API_URL + uri + "?" + toQueryString(params);
            log.info("url==== {}", url);
            if (OkHttpClientUtil.REQUEST_POST.equals(method)) {
                RequestBody body = RequestBody.create(OkHttpClientUtil.JSON, JsonUtil.writeValue(object));
                builder = new Request.Builder().url(url).post(body);
            } else {
                builder = new Request.Builder().url(url).get();
            }
            Request request = builder.build();
            Response response = OkHttpClientUtil.client.newCall(request).execute();
            String s = response.body().string();
            return s;
        } catch (IOException e) {
            log.error("调用 Huobi API 调用方法 IO 错误 {} url {} ERROR {} , {}", method, uri, e, e.getMessage());
            throw new RuntimeException("调用 Huobi API 调用方法 IO 错误," + e.getMessage());
        } catch (Exception e) {
            log.error("调用 Huobi API 调用方法 错误 {} url {} ERROR {} , {}", method, uri, e, e.getMessage());
            throw new RuntimeException("调用 Huobi API 调用方法 IO 错误," + e.getMessage());
        }
    }

    /**
     * Encode as "a=1&b=%20&c=&d=AAA"
     *
     * @param params
     * @return
     */
    private String toQueryString(Map<String, String> params) {
        return params.entrySet().stream().filter(v -> v.getValue() != null).map((entry) ->
                entry.getKey() + "=" + HuobiSignature.urlEncode(entry.getValue())
        ).collect(Collectors.joining("&"));
    }


}
