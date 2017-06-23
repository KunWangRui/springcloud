package app.command;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author gehao
 * @since Created by Administrator on 2016/12/7.
 */
@Component
@Slf4j
public class Command {

    @HystrixCommand(fallbackMethod = "defaultProcess")
    public Object getSomething(Map<String, Object> params) {
//		throw new RuntimeException("假设执行遇到异常");
        return "你好";
    }

    public Object defaultProcess(Map<String, Object> params) {
        return "默认的处理函数——调用失败";
    }


}
