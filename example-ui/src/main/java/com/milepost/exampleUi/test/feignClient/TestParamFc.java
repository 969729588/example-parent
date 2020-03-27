package com.milepost.exampleUi.test.feignClient;

import com.milepost.api.vo.response.Response;
import com.milepost.exampleApi.entity.person.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Ruifu Hua on 2020/3/15.
 */
@FeignClient(contextId = "testParamFc", name = "${info.app.service.name}")
public interface TestParamFc {

    @PostMapping("${info.app.service.prefix}/testParam/servlet")
    Response<String> servlet();

    @PostMapping("${info.app.service.prefix}/testParam/requestHeader")
    Response<String> requestHeader();

    @PostMapping("${info.app.service.prefix}/testParam/cookieValue")
    Response<String> cookieValue(@RequestParam("JSESSIONID") String sessionId);

    @GetMapping("${info.app.service.prefix}/testParam/pathVariable/{id}")
    Response<String> pathVariable(@PathVariable("id") Integer id);

    @PostMapping("${info.app.service.prefix}/testParam/basicTypes")
    Response<String> basicTypes(@RequestParam("name") String name, @RequestParam("count") int count, @RequestParam("age") Integer age);

    @GetMapping("${info.app.service.prefix}/testParam/springQueryMapPojo")
    Response<Person> springQueryMapPojo(@SpringQueryMap Person person);

    /**
     * Feign传递pojo时，只能使用@RequestBody注解，不能像SpringMVC那样不使用任何注解传递pojo。
     * @param person
     * @return
     */
    @PostMapping("${info.app.service.prefix}/testParam/pojo")
    Response<Person> pojo(@RequestBody Person person);

    @PostMapping("${info.app.service.prefix}/testParam/map")
    Response<Map<String,Object>> map(@RequestBody Map<String, Object> map);

    @PostMapping("${info.app.service.prefix}/testParam/complexPojo")
    Response<Person> complexPojo(@RequestBody Person person);

    @PostMapping("${info.app.service.prefix}/testParam/listPojo")
    Response<List<Person>> listPojo(@RequestBody List<Person> personList);

    @PostMapping("${info.app.service.prefix}/testParam/listMap")
    Response<List<Map<String,Object>>> listMap(@RequestBody List<Map<String, Object>> mapList);

}
