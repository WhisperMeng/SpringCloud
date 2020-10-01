package web.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import common.entity.RestfulResult;
import common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController // 重要，如果用Controller会404
@RequestMapping(value = "")
public class ServiceController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${server.port}")
    String port;

    // 调用：localhost:6007/consumerServiceRibbon?token=1
    @RequestMapping("/ribbon")
    @HystrixCommand(fallbackMethod="consumerServiceRibbonFallback")
    public String consumerServiceRibbon(@RequestParam(value = "name", required = false) String name){
        String result = this.restTemplate.postForObject("http://springbootService/hello", name, String.class);
        return result;
    }

    public String consumerServiceRibbonFallback(@RequestParam(value = "name", required = false) String name){
        return "consumerServiceRibbon异常，端口：" + port + "，Name=" + name;
    }

}