package com.pepple.webapp.tag;

import com.pepple.webapp.commonutil.StringUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @description:
 * @author: pepple
 * @date: 2017/4/19 9:25
 */
public class BaseTag extends TagSupport{
    private String variable;
    private String scope;
    private HttpServletRequest request;
    protected Logger log = Logger.getLogger(getClass());

    @Override
    public int doStartTag() throws JspException {
        return super.doStartTag();
    }

    public void applyToScope(Object value) {
        if(StringUtil.isEmpty(variable)) {
            return ;
        }
        //默认使用page作为scope
        if(StringUtil.isEmpty(scope)) {
            scope = "page";
        }

        if("page".equals(scope)) {
            pageContext.setAttribute(variable,value);
        }
        else if("request".equals(scope)) {
            pageContext.getRequest().setAttribute(variable,value);
        }
        else if("session".equals(scope)) {
            pageContext.getSession().setAttribute(variable,value);
        }
        else if("application".equals(scope)) {
            pageContext.getServletContext().setAttribute(variable,value);
        }
        else {
            log.warn("value of scope is invalid, scope: " + scope);
        }
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public void setDefaultVarable(String defaultVarable) {
        this.variable = defaultVarable;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest)pageContext.getRequest();
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
