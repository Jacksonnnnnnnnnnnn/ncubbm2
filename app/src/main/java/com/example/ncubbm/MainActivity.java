package com.example.ncubbm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Context context=this;
    EditText et1,et2,et3,et4,et5,et6,et7,et8;
    WebView webview;
    String url="http://ncubbm.byethost12.com";
    CookieManager cookieManager;
    String cookieStr,id_text;
//    ListView LV1;
    RecyclerView Re1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());

        Wcookie(context);
        Handler myHandler = new Handler();
        myHandler.postDelayed(runTimerStop, 15000);//removeCallbacks方法是删除指定的Runnable對象，使線程對象停止運行。
        if(cookieStr!=null){
            myHandler.removeCallbacks(runTimerStop);
        }

//        LV1=(ListView)findViewById(R.id.LV1);
        Re1=(RecyclerView)findViewById(R.id.Re1);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        et3=(EditText)findViewById(R.id.et3);
        et4=(EditText)findViewById(R.id.et4);
        et5=(EditText)findViewById(R.id.et5);
        et6=(EditText)findViewById(R.id.et6);
        et7=(EditText)findViewById(R.id.et7);
        et8=(EditText)findViewById(R.id.et8);

        //新增資料
        Button b1=(Button)findViewById(R.id.button12);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("checkInsert","hiiiiiiiiiiiiiiiiiiii");
                String[] et0=new String[]{et1.getText().toString(),et2.getText().toString(),
                                          et3.getText().toString(),et4.getText().toString(),
                                          et5.getText().toString(),et6.getText().toString(),
                                          et7.getText().toString(),et8.getText().toString()};
                Inphp.Interting(et0,cookieStr,url);
                Log.d("checkInsert","inphp");
                select(null);
            }
        });

        /*
        //選擇資料才能del or update
        LV1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView te0=(TextView)view.findViewById(R.id.text0);
                id_text=te0.getText().toString();
                TextView te1=(TextView)view.findViewById(R.id.text1);
                et1.setText(te1.getText());
                TextView te2=(TextView)view.findViewById(R.id.text2);
                et2.setText(te2.getText());
                TextView te3=(TextView)view.findViewById(R.id.text3);
                et3.setText(te3.getText());
                TextView te4=(TextView)view.findViewById(R.id.text4);
                et4.setText(te4.getText());

            }
        });
         */

        //update , 不確定id_text是否還有用
        Button b2=(Button)findViewById(R.id.button13);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String[] et0=new String[]{id_text,et1.getText().toString(),et2.getText().toString(),
                String[] et0=new String[]{id_text,et1.getText().toString(),et2.getText().toString(),
                                                  et3.getText().toString(),et4.getText().toString(),
                                                  et5.getText().toString(),et6.getText().toString(),
                                                  et7.getText().toString(),et8.getText().toString()};
                Upphp.updating(et0,cookieStr,url);
                select(null);
            }
        });

        //delete
        Button b3=(Button)findViewById(R.id.button14);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("checkDelete","in");
                String[] et0=new String[]{id_text};
                Delphp.deleting(et0,cookieStr,url);
                select(null);
            }
        });

        //search pid column
        Button b4=(Button)findViewById(R.id.button15);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select(et2.getText().toString());
            }
        });

    }

    private void Wcookie(Context context) {
        CookieSyncManager.createInstance(context);
        cookieManager = CookieManager.getInstance();
        webview = new WebView(context);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient(){
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                cookieManager.setAcceptCookie(true);
                cookieStr=cookieManager.getCookie(url);
            }
        });
        webview.loadUrl(url);
        webview.clearHistory();
        webview.clearCache(true);

        cookieManager.removeAllCookie();
        cookieManager.removeSessionCookie();
    }


    private Runnable runTimerStop = new Runnable() {
        @Override
        public void run() {
            select(null);
        }
    };


    //顯示資料
    public void select(String id){
        try {
            String r = DBphp.DBstring(id, cookieStr, url);

            JSONArray jsonArray = new JSONArray(r);
//            List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
            ArrayList<Re1_list>items=new ArrayList<Re1_list>();

            for(int i =0;i<jsonArray.length();i++){
                JSONObject jsonData=jsonArray.getJSONObject(i);
                items.add(new Re1_list(jsonData.getString("date"),jsonData.getString("pid"),
                                       jsonData.getString("bedNo"),jsonData.getString("s1"),
                                       jsonData.getString("s2"),jsonData.getString("s3"),
                                       jsonData.getString("s4"),jsonData.getString("remark")));
//LV1
//                Map<String,Object> item=new HashMap<String, Object>();
//                item.put("id",jsonData.getString("id"));
//                item.put("name",jsonData.getString("name"));
//                item.put("m1",jsonData.getString("m1"));
//                item.put("m2",jsonData.getString("m2"));
//                item.put("m3",jsonData.getString("m3"));
//                items.add(item);//新增到items
                }
//            SimpleAdapter SA=new SimpleAdapter(context,items,R.layout.list_text,new String[]{"id","name","m1","m2","m3"},new int[]{R.id.text0,R.id.text1,R.id.text2,R.id.text3,R.id.text4});
//            LV1.setAdapter(SA);
                //自定Adapter
            Re1Adapter SA= new Re1Adapter(context,items);
            Re1.setLayoutManager(new GridLayoutManager(this,3));
            Re1.setAdapter(SA);

            SA.setOnItemClickListener(new Re1Adapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int i) {
                    TextView te0=(TextView)view.findViewById(R.id.t0);
                    id_text=te0.getText().toString();
                    TextView te1=(TextView)view.findViewById(R.id.t2);
                    et1.setText(te1.getText());
                    TextView te2=(TextView)view.findViewById(R.id.t3);
                    et2.setText(te2.getText());
                    TextView te3=(TextView)view.findViewById(R.id.t4);
                    et3.setText(te3.getText());
                    TextView te4=(TextView)view.findViewById(R.id.t5);
                    et4.setText(te4.getText());
                    TextView te5=(TextView)view.findViewById(R.id.t6);
                    et5.setText(te5.getText());
                    TextView te6=(TextView)view.findViewById(R.id.t7);
                    et6.setText(te6.getText());
                    TextView te7=(TextView)view.findViewById(R.id.t8);
                    et7.setText(te7.getText());
                    TextView te8=(TextView)view.findViewById(R.id.t9);
                    et8.setText(te8.getText());
                }
            });

        }catch (Exception e){
            Log.e("Log_tag=",e.toString());
        }
    }

    public void toHomePage(View v) {
        Intent i = new Intent (this, Home.class);
        startActivity(i);
    }

}
