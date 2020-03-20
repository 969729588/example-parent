package com.milepost.exampleService.conditionBean.depends;

import com.alibaba.fastjson.JSONObject;
import com.milepost.exampleService.conditionBean.depends.bean.LoadIfBeanExist;
import com.milepost.exampleService.conditionBean.depends.bean.LoadIfBeanNotExists;
import com.milepost.exampleService.conditionBean.depends.clz.LoadIfClzExists;
import com.milepost.exampleService.conditionBean.depends.clz.LoadIfClzNotExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by @author yihui in 09:32 18/10/18.
 */
@RestController
@RequestMapping("depends")
public class DependRest {

    @Autowired
    private LoadIfBeanExist loadIfBeanExist;
    @Autowired
    private LoadIfBeanNotExists loadIfBeanNotExists;
    @Autowired
    private LoadIfClzExists loadIfClzExists;
    @Autowired(required = false)
    private LoadIfClzNotExists loadIfClzNotExists;

    @GetMapping(path = "show")
    public String show() {
        Map<String, String> result = new HashMap<>(4);
        // 存在
        result.put("loadIfBeanExist", loadIfBeanExist == null ? "null ==> false!" : loadIfBeanExist.getName());
        // 存在
        result.put("loadIfBeanNotExists",
                loadIfBeanNotExists == null ? "null ==> false!" : loadIfBeanNotExists.getName());
        // 存在
        result.put("loadIfClzExists", loadIfClzExists == null ? "null ==> false!" : loadIfClzExists.getName());
        // 不存在
        result.put("loadIfClzNotExists", loadIfClzNotExists == null ? "null ==> true!" : loadIfClzNotExists.getName());

        return JSONObject.toJSONString(result);
    }
}
