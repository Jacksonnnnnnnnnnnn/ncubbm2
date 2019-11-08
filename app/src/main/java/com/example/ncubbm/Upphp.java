package com.example.ncubbm;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.util.ArrayList;

public class Upphp {
    private static HttpClient hC;
    private static HttpPost hP;

    public static void updating(String[] i,String Wcook,String url){
        try {
            hC=new DefaultHttpClient();
            hP=new HttpPost(url+"/update.php");
            System.out.println("是否取得cookie="+Wcook);
            hP.addHeader("Cookie",Wcook+";expires=Thu,31-Dec-37 23:55:55 GMT; path=/");

            ArrayList<NameValuePair>params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("S0",i[0]));
            params.add(new BasicNameValuePair("S1",i[1]));
            params.add(new BasicNameValuePair("S2",i[2]));
            params.add(new BasicNameValuePair("S3",i[3]));
            params.add(new BasicNameValuePair("S4",i[4]));
            hP.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse hR=hC.execute(hP);
            hR.getEntity();

        }catch (Exception e){
            System.out.print(e.toString());

        }
    }
}
