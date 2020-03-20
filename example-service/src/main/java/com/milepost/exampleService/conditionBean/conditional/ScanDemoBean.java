package com.milepost.exampleService.conditionBean.conditional;

import com.milepost.exampleService.conditionBean.conditional.condition.ScanDemoCondition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * Created by @author yihui in 14:34 18/10/18.
 */
@Component
@Conditional(ScanDemoCondition.class)
public class ScanDemoBean {
    public ScanDemoBean() {
        System.out.println("init ScanDemoBean...");
    }

    @Value("${conditional.demo.load}")
    private boolean load;

    public boolean getLoad() {
        return load;
    }
}
