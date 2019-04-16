package com.lifeinfinity.company.multiplythread.threadpool08;
import org.springframework.stereotype.Service;

/**
 * @Author By: zhangyuhang@gridsum.com
 * @Description:
 * @Date: Created on 2019/4/3
 */
@Service
public class TestServiceImpl implements  TestService {
    @Override
    public void SayHi() {
        System.out.println("HelloWorld");
    }
}
