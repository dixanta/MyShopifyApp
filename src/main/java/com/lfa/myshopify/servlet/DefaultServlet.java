/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lfa.myshopify.servlet;

import com.lfa.myshopify.constant.AppConstant;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
@WebServlet(name = "default", urlPatterns = {"/"})
public class DefaultServlet extends HttpServlet {

    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String link = "https://{shop}.myshopify.com/admin/oauth/authorize?client_id={api_key}&scope={scopes}&redirect_uri={redirect_uri}&state={nonce}";
        link = link.replace("{shop}", request.getParameter("shop"));
        link = link.replace("{api_key}", AppConstant.ClientId);
        link = link.replace("{scopes}", AppConstant.SHOPIFY_SCOPES);
        link = link.replace("{redirect_uri}", URLEncoder.encode("http://localhost:8080/MyShopifyApp/CallBack/","UTF-8"));
        link = link.replace("{nonce}", randomString(20));
        response.sendRedirect(link);
        //response.getWriter().println(link);
    }

    String randomString(int len) {
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

}
