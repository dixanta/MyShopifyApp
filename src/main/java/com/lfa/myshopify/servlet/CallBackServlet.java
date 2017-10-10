/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lfa.myshopify.servlet;

import com.lfa.myshopify.constant.AppConstant;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

/**
 *
 * @author USER
 */
@WebServlet(name = "callback",urlPatterns = {"/CallBack/*"})
public class CallBackServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String shop=request.getParameter("shop");
        request.getSession().setAttribute("SHOPIFY_SHOP", shop);
        String code=request.getParameter("code");
        String authUrl="https://"+shop+"/admin/oauth/access_token";
        try{
        OAuthClient client=new OAuthClient(new URLConnectionClient());
        OAuthClientRequest req=OAuthClientRequest.tokenLocation(authUrl)
                .setClientId(AppConstant.ClientId).setClientSecret(AppConstant.ClientSecret)
                .setCode(code)
                .buildQueryMessage();
        String token=client.accessToken(req,OAuthJSONAccessTokenResponse.class).getAccessToken();
          request.getSession().setAttribute("SHOPIFY_ACCESS_TOKEN", token);
          response.sendRedirect(request.getContextPath() + "/dashboard");
        }catch(OAuthSystemException | OAuthProblemException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    
    
}
