package com.milepost.exampleService.conditionBean.depends.clz;

/**
 * Created by @author yihui in 09:26 18/10/18.
 */
public class LoadIfClzNotExists {
    private String name;

    public LoadIfClzNotExists(String name) {
        this.name = name;
    }

    public String getName() {
        return "load if not exists clz: " + name;
    }
}
