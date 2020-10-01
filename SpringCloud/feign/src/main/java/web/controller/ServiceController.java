package web.controller;

import common.entity.RestfulResult;
import common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web.client.ServiceFeignClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController // 重要，如果用Controller会404
@RequestMapping(value = "")
public class ServiceController {

    @Autowired
    ServiceFeignClient serviceFeignClient;

    @RequestMapping(value = "hello")
    public void login(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "name", required = false) String name) {

        RestfulResult restfulResult = serviceFeignClient.hello(name);

        CommonUtils.printDataJason(response, restfulResult);
    }

}