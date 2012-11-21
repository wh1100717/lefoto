package com.lefoto.common.base;

import com.lefoto.common.utils.FormatUtil;
import com.lefoto.model.user.LeUser;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Eric
 */
@Controller
public abstract class BaseController {

    private FormatUtil formatUtil;
    private LeUser user;

    public void execute(HttpServletRequest request) {
        user = this.getRequestUser(request);
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
