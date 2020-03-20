package com.milepost.exampleService.getBeans;

import org.springframework.stereotype.Component;

/**
 * Created by Ruifu Hua on 2020/2/26.
 */
@SomeAnnotation(pro = "SomeBeanImpl3")
@Component
public class SomeBeanImpl3 implements SomeBean{

    @Override
    public void doSomething(String input) {
        System.out.println("SomeBeanImpl3 " + input);
    }

    @Override
    public String getSomething(String input) {
        return "SomeBeanImpl3 " + input;
    }
}
