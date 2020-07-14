package com.allstars.pudge.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author zz
 * @date 2020/7/14 5:28 下午
 */
@Component
@FeignClient(name = "backend", url = "${service-url.backend}")
public interface HuoBiClient {
    /**
     * 获取责任主体列表
     * @param projectId
     * @param param
     * @return
     */
    @GetMapping("/projects/{projectId}/organizations")
    ResponseEntity<Map> getOrganizations(@PathVariable Long projectId, @RequestParam Map<String, Object> param);
}
