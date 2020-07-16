package com.example.demo;

import com.allstars.pudge.Application;
import com.allstars.pudge.service.Huobi;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zz
 * @date 2020/7/16 3:26 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Teeeee {
    @Autowired
    private Huobi huobi;

    @Test
    public void testExample() {
        //groupManager访问路径
        //param传入参数
        huobi.getAccounts();
    }
}
