<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true"%>
<%
    //包含最后的斜线
    String path = null;
    String host = request.getRemoteAddr();
    path = "http://" + host;
    int port = request.getServerPort();
    if(port != 80) {
        path += (":" + port);
    }
    String webName = request.getContextPath(); // 如/Test
    path += (webName + "/");
%>

