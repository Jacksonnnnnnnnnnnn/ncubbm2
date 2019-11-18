package com.example.ncubbm;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.util.ArrayList;

public class Delphp {
    private static HttpClient hC;
    private static HttpPost hP;

    public static void deleting(String[] i,String Wcook,String url){
        try {
            Log.d("checkDelete","delphp1");
            hC=new DefaultHttpClient();
            hP=new HttpPost(url+"/delete.php");
            System.out.println("是否取得cookie="+Wcook);
            hP.addHeader("Cookie",Wcook+";expires=Thu,31-Dec-37 23:55:55 GMT; path=/");
            Log.d("checkDelete","delphp2");

            ArrayList<NameValuePair> params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("S1",i[0]));
            hP.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse hR=hC.execute(hP);
            hR.getEntity();
            Log.d("checkDelete","delphp3");
        }catch (Exception e){
            System.out.print(e.toString());

        }
    }
}
