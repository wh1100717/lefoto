package com.lefoto.common.base;

import com.lefoto.common.utils.FormatUtil;
import com.lefoto.model.user.LeUser;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author Eric
 */
@Controller
public abstract class BaseController {

    private FormatUtil formatUtil;
    private LeUser user;
    private HttpServletRequest request;
    private HttpServletResponse response;
    
    public void execute(HttpServletRequest request) {
        user = this.getRequestUser(request);
    }

    public String getParaStringFromRequest(String paraName) throws Exception {
        request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
        Map<String, String[]> paramMap = request.getParameterMap();
        if (paramMap == null) {
            return null;
        }
        if (paramMap.get(paraName) == null) {
            return null;
        }
        String result = paramMap.get(paraName)[0];
        if (result == null || result.equals("")) {
            return null;
        }
        return result;
    }

    public int getParaIntFromRequest(String paraName) {
        request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
        Map<String, String[]> paramMap = request.getParameterMap();
        if (paramMap == null) {
            return 0;
        }
        if (paramMap.get(paraName) == null) {
            return 0;
        }
        String result = paramMap.get(paraName)[0];
        if (result == null || result.equals("")) {
            return 0;
        }
        return Integer.parseInt(result);
    }

    public BaseController() {
        this.formatUtil = new FormatUtil();
    }

    public FormatUtil getFormatUtil() {
        return formatUtil;
    }

    public void setFormatUtil(FormatUtil formatUtil) {
        this.formatUtil = formatUtil;
    }

    public LeUser getRequestUser(HttpServletRequest request) {
        return (LeUser) request.getAttribute("user");
    }

    public LeUser getUser() {
        return user;
    }

    public void setUser(LeUser user) {
        this.user = user;
    }
}
