package app.remote;

/**
 * Created by kun on 17-6-23.
 */

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author gehao
 * @since Created by Administrator on 2016/12/8.
 */
@FeignClient(name = "example-app-server")
public interface Remote {

    @RequestMapping(value = "/api/demo/foo", method = RequestMethod.GET)
    String getFoo();
}
