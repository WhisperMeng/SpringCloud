package web.client;

import common.entity.RestfulResult;
import org.springframework.stereotype.Component;

@Component
public class ServiceFallback implements ServiceFeignClient{

    @Override
    public RestfulResult hello(String name) {
        RestfulResult result = new RestfulResult();
        result.setData("服务调用失败");
        return result;
    }
}
