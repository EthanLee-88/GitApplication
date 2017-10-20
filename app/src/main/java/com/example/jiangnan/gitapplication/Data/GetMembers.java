package com.example.jiangnan.gitapplication.Data;

import android.content.Context;
import android.util.Log;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by jiangnan on 2017/10/18.
 */

public class GetMembers {
    private Context mContext;
    private String PATH = "http://112.74.136.2:8070/TF02/V2/info/allfamilymembers/list";

    public GetMembers (Context context){
        mContext = context;
    }

    public void getMembers(String tel , String token){
        String params = null;
        params = "{\"tel\":\"" + tel + "\"}";
        URL url = null;
        try{
            url = new URL(PATH);
        }catch (Exception e){

        }
        final URL finalUrl = url;
        URLconnection(finalUrl , params);
    }
    private void URLconnection(final URL url , String param){

        HttpURLConnection httpURLConnection = null;
        String result = null;
        try{
            httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(20000);
            httpURLConnection.setReadTimeout(120000);
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("Content-Type","application/json");
            httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
            httpURLConnection.setRequestProperty("token" , AccountData.getInstance().LoginTaken);

            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(param.getBytes("utf-8"));
            outputStream.close();

            int responseCode = httpURLConnection.getResponseCode();
            if(responseCode == 200){
                InputStream inputStream = httpURLConnection.getInputStream();    /********打开输入流接受数据********/
                byte[] bytes;
                ArrayList<Byte> results = new ArrayList<Byte>();       /*********字节数组*******/
                int cache = 0;
                try{
                    while ((cache = inputStream.read()) != -1){
                        results.add((byte)cache);
                    }
                }catch (Exception e){

                }
                if(results.size() == 0){

                }else {
                    bytes = new byte[results.size()];
                    for(int i = 0 ; i < bytes.length ; i ++){
                        bytes[i] = results.get(i);
                    }
                    result = new String(bytes , "utf-8");
                    Log.d("GetMembers" , "家庭成员数据=" + result);
                }
                inputStream.close();
            }

        }catch (Exception e){

        }
        ParseMembersData parseMembersData = new ParseMembersData(mContext);
        parseMembersData.ParseMember(result);

    }


}
