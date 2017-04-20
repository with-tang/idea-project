package com.wx.auth.servlet;

import com.wx.auth.util.AuthUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by tang on 2017/4/18.
 */


@WebServlet("/wxLogin")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException,ServletException
    {
        String backUrl="http://nicety.imwork.net:36671/wxCallBack";
        String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + AuthUtil.AppID+
                "&redirect_uri=" + URLEncoder.encode(backUrl)+
                "&response_type=code" +
                "&scope=snsapi_userinfo"+
                "&state=STATE#wechat_redirect";
        response.sendRedirect(url);

    }
}
