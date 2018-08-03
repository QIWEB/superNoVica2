package com.qiweb.vica.a.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 *@author 大贲.qiweb
 *@calss description 发送报文方法
 *@param
 *@date
*/
public class HttpClientsUtils {

    /**
     *@author 大贲.qiweb
     *@method description 调用vica,使用httpclients/调用电子发票
     *@param
     *@date
    */
    public static String doPost(String sendData, String sendUrl) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(sendUrl);
        StringEntity sedata = new StringEntity(sendData, "utf-8");
        httppost.addHeader("Content-type", "application/json");
        httppost.addHeader("Token", "+hoT22yGlVqrFkhi+WV26VBrvXGkiZNb48vnrYHJj3K8V7xPcu09bA==");
        httppost.setEntity(sedata);
        String content = null;
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == 200) {
                content = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                response.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                httpClient.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return content;
    }






}
