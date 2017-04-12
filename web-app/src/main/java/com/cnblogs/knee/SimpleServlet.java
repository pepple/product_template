package com.cnblogs.knee;

import com.cnblogs.knpp.SimpleService;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description:
 * @author: pepple
 * @date: 2017/4/7 13:18
 */
public class SimpleServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter writer = response.getWriter();
        String serviceDesc = SimpleService.getServiceDescription();
        writer.println(serviceDesc);
        writer.flush();
        writer.close();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
