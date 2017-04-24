package com.pepple.webapp.tag;

import com.pepple.webapp.pojo.User;
import com.pepple.webapp.service.UserService;
import com.pepple.webapp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.jsp.JspException;

/**
 * @description:
 * @author: pepple
 * @date: 2017/4/19 14:24
 */
@Component
public class UserInfoTag extends BaseTag {
    private Long userid;
    private UserService userService;
    @Override
    public int doStartTag() throws JspException {
        log.debug("input userid: " + userid);
        setDefaultVarable("userinfo");
        User user = new User();
        user.setId(100);
        user.setName("haha");
        user.setPassword("****");
        applyToScope(user);
        return super.doStartTag();
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
