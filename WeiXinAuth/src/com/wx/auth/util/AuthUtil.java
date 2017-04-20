package com.wx.auth.util;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by tang on 2017/4/18.
 */
public class AuthUtil {

   /* 测试公众号 public static final String AppID="wxa0b6166df1dc80a6";
    public static final String AppSecret="a8c65c8e7e2baa0a25cd16972bb80c59";*/

    public static final String AppID="wxa0b6166df1dc80a6";
    public static final String AppSecret="a8c65c8e7e2baa0a25cd16972bb80c59";
    /**
     *
     * @param url
     * @return json
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static JSONObject doGetJson(String url)throws IOException,ClientProtocolException{
        JSONObject jsonObject=null;
        DefaultHttpClient client=new DefaultHttpClient();
        //get content via the url by the Get Method
        HttpGet httpGet=new HttpGet(url);
        //get the response
        HttpResponse httpResponse=client.execute(httpGet);
        //get response entity
        HttpEntity entity=httpResponse.getEntity();
        //transform the entity into json format
        if(entity!=null)
        {
            String result= EntityUtils.toString(entity,"UTF-8");
            jsonObject=JSONObject.fromObject(result);
        }
        //release the connection
        httpGet.releaseConnection();
        return jsonObject;
    }
}
