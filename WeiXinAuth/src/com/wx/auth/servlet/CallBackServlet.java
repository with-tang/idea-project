package com.wx.auth.servlet;

import com.wx.auth.util.AuthUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by tang on 2017/4/18.
 */
//@WebServlet("/callBack")
public class CallBackServlet extends HttpServlet {

    private String dbUrl;
    private String driverName;
    private String userName;
    private String passWord;
    private Connection conn=null;
    private PreparedStatement ps=null;
    private ResultSet rs=null;

    @Override
    public void init(ServletConfig config)throws ServletException{

        try{
            System.out.println("initialize the mysql");
            this.dbUrl=config.getInitParameter("dbUrl");
            System.out.println(dbUrl);
            this.driverName=config.getInitParameter("driverName");
            this.userName=config.getInitParameter("userName");
            this.passWord=config.getInitParameter("passWord");
            Class.forName(driverName);
            System.out.println("userName:"+userName+"  passWord:"+passWord);
            System.out.println("initialize the mysql success");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String code=request.getParameter("code");
        String url= "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + AuthUtil.AppID+
                "&secret=" +AuthUtil.AppSecret+
                "&code=" +code+
                "&grant_type=authorization_code";
        System.out.println("before the json");
        JSONObject jsonObject=AuthUtil.doGetJson(url);
        System.out.println("after the json");
        String openid=jsonObject.getString("openid");
        String token=jsonObject.getString("access_token");
        String infoUrl= "https://api.weixin.qq.com/sns/userinfo?access_token=" +token+
                "&openid=" +openid+
                "&lang=zh_CN";
        JSONObject userInfo=AuthUtil.doGetJson(infoUrl);
        System.out.println(userInfo);

        //1、使用微信用户信息登陆，无需完善账号
       /* request.setAttribute("info",userInfo);
        request.getRequestDispatcher("/index1.jsp").forward(request,response);*/
        //2、将微信与当前账号进行绑定
        try {
            String nickName = getNickName(openid);
            if(!"".equals(nickName))
            {
                request.setAttribute("nickName",nickName);
                request.getRequestDispatcher("/index2.jsp").forward(request,response);
            }
            else
            {
                request.setAttribute("openid",openid);
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }catch(SQLException e)
        {
         e.printStackTrace();
        }

    }
    public String getNickName(String openId)throws SQLException
    {
        String nickName="";
        System.out.println("1:before get conn");
        conn=DriverManager.getConnection("jdbc:mysql://192.168.20.128:3306/auth","moana","moana");
        System.out.println("2:after get conn"+conn);
        String sql="select nickname from user where openid=?";
        ps=conn.prepareStatement(sql);
        ps.setString(1,openId);
        rs=ps.executeQuery();
        while(rs.next())
        {
            nickName=rs.getString("nickname");
        }
        rs.close();
        ps.close();
        conn.close();
        return nickName;
    }
    public int updateUser(String openId,String account,String password)throws SQLException
    {
        //conn=DriverManager.getConnection(dbUrl,username,password);
        conn=DriverManager.getConnection("jdbc:mysql://192.168.20.128:3306/auth","moana","moana");
        String sql="update  user set openid=?  where account=? and password=?";
        ps=conn.prepareStatement(sql);
        ps.setString(1,openId);
        ps.setString(2,account);
        ps.setString(3,password);
        int temp =ps.executeUpdate();
        rs.close();
        ps.close();
        conn.close();
        return temp;

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        String openid=request.getParameter("openid");
        try
        {
            int temp=updateUser(openid,account,password);
            if(temp>0){
                System.out.println("bind success");
            }
            else {
                System.out.println("bind failure");
            }
        }catch(SQLException e)
        {
         e.printStackTrace();
        }

    }

}
