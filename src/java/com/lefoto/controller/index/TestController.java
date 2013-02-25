/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.index;

import com.lefoto.common.cache.UserCache;
import com.lefoto.model.user.LeUser;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Eric
 */
@Controller
public class TestController {

    @RequestMapping(value = "/test")
    public @ResponseBody
    List<String> test(HttpServletRequest request) {
        LeUser user = UserCache.getRandomRobootUser();
        List<String> result = new ArrayList();
        result.add(user.getName());
        return result;
    }

}
