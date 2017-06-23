package app.rest;

/**
 * Created by kun on 17-6-23.
 */

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import app.command.Command;
import app.remote.Remote;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author gehao
 * @since Created by gehao on 2016/12/6.
 */
@RestController
@RequestMapping(value = "/api/demo")
@Slf4j
public class Api {

    @Value("${foo}")
    private String foo;

    /**
     * 测试 Hystrix 熔断
     */
    @Resource
    private Command demoCommand;

    /**
     * 采用Ribbon请求数据
     */
    @Resource
    private RestTemplate client;

    /**
     * 采用Feign请求数据
     */
    @Resource
    private Remote remoteService;

    /**
     * 普通数据请求，使用了Hystrix熔断机制，具体看 {@link Command}
     *
     * @return 对象
     */
    @RequestMapping(method = RequestMethod.GET, value = "/foo")
    public Object getFoo() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("query", foo);
        return demoCommand.getSomething(params);
    }

    /**
     * 使用Feign远程调用，具体看 {@link Remote}
     *
     * @return 字符串
     */
    @RequestMapping(method = RequestMethod.GET, value = "/feign")
    public String getFeign() {
        return remoteService.getFoo();
    }

    /**
     * 使用Ribbon请求远程服务，可以自动序列化，这里使用的只是String
     *
     * @return 字符串
     */
    @RequestMapping(method = RequestMethod.GET, value = "/ribbon")
    public String getRemote() {
        org.slf4j.Logger log= LoggerFactory.getLogger(Api.class);
        log.info(client + ">>");
        return client.getForObject("http://example-app-server/api/demo/foo", String.class);
    }
}

