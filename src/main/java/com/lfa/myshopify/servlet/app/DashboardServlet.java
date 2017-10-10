/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lfa.myshopify.servlet.app;


import com.lfa.myshopify.util.HttpClient;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
@WebServlet(name = "dashboard", urlPatterns = {"/dashboard/*"})
public class DashboardServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String url="https://"+session.getAttribute("SHOPIFY_SHOP")
                + "/admin/customers.json?access_token="
                +session.getAttribute("SHOPIFY_ACCESS_TOKEN");
        String data=HttpClient.get(url);
        response.getWriter().println(data);
    }

    

}
