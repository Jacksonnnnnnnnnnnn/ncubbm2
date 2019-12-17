package com.example.ncubbm;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DBphp {
    private static HttpClient hC;
    private static HttpPost hP;

    public static String DBstring(String i,String Wcook,String url){
    String result ="錯誤";



        try {
            hC=new DefaultHttpClient();
            hP=new HttpPost(url + "/index.php");
            hP.addHeader("Cookie", Wcook+";expires=Thu,31-Dec-37 23:55:55 GMT; path=/");
            //hP.addHeader("Cookie", "__test:2a82dd447404a842c71147240b1b4ae7;expires=Fri,01-Jan-38 07:55:55 GMT; path=/");

            //NameValuePair為一個接口
            //searching若null的話就顯示全部result
            if(i!=null && !i.toString().equals("")) {
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

                params.add(new BasicNameValuePair("S2", i));
                //urlEncodedFormEntity為指定編碼參數，構造一個新的來發送HTTP POST請求(如UTF8)
                hP.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            }
            //HttpResponse為接受回傳值
            HttpResponse hR=hC.execute(hP);
            HttpEntity hE=hR.getEntity();
            InputStream inputStream = hE.getContent();
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, HTTP.UTF_8), 8);
            //為動態元件，可指定他能容納的字元數上限值
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = bufReader.readLine()) != null) {
                builder.append(line + "\n");
            }
            inputStream.close();
            result=builder.toString();

        } catch (Exception e) {
            result = e.toString();
        } finally {
            //連結客戶端關閉的方法
            hC.getConnectionManager().shutdown();

        }

        return result;
    }
}
