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

public class Upphp {
    private static HttpClient hC;
    private static HttpPost hP;

    public static void updating(String[] i,String Wcook,String url){
        try {
            Log.d("checkUpdate2","UpPHP");
            hC=new DefaultHttpClient();
            hP=new HttpPost(url+"/update.php");
            System.out.println("是否取得cookie="+Wcook);
            hP.addHeader("Cookie",Wcook+";expires=Thu,31-Dec-37 23:55:55 GMT; path=/");

            ArrayList<NameValuePair>params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("S1",i[0]));
            params.add(new BasicNameValuePair("S2",i[1]));
            params.add(new BasicNameValuePair("S3",i[2]));
            params.add(new BasicNameValuePair("S4",i[3]));
            params.add(new BasicNameValuePair("S5",i[4]));
            params.add(new BasicNameValuePair("S6",i[5]));
            params.add(new BasicNameValuePair("S7",i[6]));
            params.add(new BasicNameValuePair("S8",i[7]));
            params.add(new BasicNameValuePair("S9",i[8]));
//            params.add(new BasicNameValuePair("S1",i[0])) to params.add(new BasicNameValuePair("S8",i[7]));
            hP.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse hR=hC.execute(hP);
            hR.getEntity();
            Log.d("checkUpdate3","UpPHP");
        }catch (Exception e){
            System.out.print(e.toString());

        }
    }
}
