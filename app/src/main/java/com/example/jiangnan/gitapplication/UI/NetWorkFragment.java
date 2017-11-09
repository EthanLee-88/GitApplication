package com.example.jiangnan.gitapplication.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jiangnan.gitapplication.Data.AccountData;
import com.example.jiangnan.gitapplication.Data.ParseLoginData;
import com.example.jiangnan.gitapplication.R;
import com.example.jiangnan.gitapplication.Util.ThreadPoolUtil;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by jiangnan on 2017/10/16.
 */

public class NetWorkFragment extends Fragment {
    private Button getData ;
    private EditText MeditText , AccountNum;
    protected static int DEFAULT_CONNECT_TIMEOUT = 20000;
    protected static int DEFAULT_READ_TIMEOUT = 120000;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_net_work , container , false);
        initRes(view);
        return view;
    }
    private void initRes (View view){
        getData = (Button) view.findViewById(R.id.get_data);
        MeditText = (EditText) view.findViewById(R.id.password_enter);
        AccountNum = (EditText) view.findViewById(R.id.account_num_enter);

        netWork();
    }
    private void netWork(){
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!MeditText.getText().toString().isEmpty()){
                    createParamAndUrl();
                }else {
                    Toast.makeText(getActivity(), "密码不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createParamAndUrl(){
        String PATH = "http://112.74.136.2:8070/TF02/V2/account/familyaccount/login";
        URL url = null;
        try {
             url = new URL(PATH);
        }catch (Exception e){

        }
        final String params = "{\"tel\":\"" + AccountNum.getText().toString() + "\",\"pwd\":\"" + MeditText.getText().toString() + "\"}";

        Log.d("NetWorfkFragment","账号参数=" + params);
        Log.d("NetWorfkFragment","登录接口=" + PATH);

        final URL finalURL = url;
        ThreadPoolUtil.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                Login(finalURL , params);
            }
        });

    }
    private void Login(URL url , String param){
        HttpURLConnection HUconn = null;
        String ReSult = null;

        try{
            HUconn = (HttpURLConnection) url.openConnection();  /********打开连接*******/

            HUconn.setUseCaches(false);                /********useCaches请求头字段的值**********/
            HUconn.setConnectTimeout(20000);            /**************连接超时************/
            HUconn.setReadTimeout(120000);
            HUconn.setRequestMethod("POST");            /********设置请求方式*******/

            HUconn.setRequestProperty("Connection" , "Keep-Alive");       //设置连接的状态        /********设置通用的请求属性*******/
            HUconn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            HUconn.setRequestProperty("Content-Type","application/json");      //设定 请求格式 json
            HUconn.setRequestProperty("Accept-Charset", "utf-8");            //设置编码语言

            HUconn.setDoOutput(true);                       /**********设置请求头字段的值*******/
            HUconn.connect();                              /***********建立实际连接********/
            OutputStream outputStream = HUconn.getOutputStream();  /**********获取输出流************/
            outputStream.write(param.getBytes("utf-8"));      /************向输出流写入账号密码参数************/
            outputStream.close();                        /*********关闭输出流***********/
            int resPonseCode = HUconn.getResponseCode();   /*************获取服务器的响应代码**************/
            Log.d("NetWorfkFragment" ,"服务器返回码=" + resPonseCode);

            if(resPonseCode ==  200){                     /***************请求成功******/
               InputStream inputStream = HUconn.getInputStream();    /********打开输入流接受数据********/
               byte[] bytes;
                ArrayList<Byte> result = new ArrayList<Byte>();       /*********字节数组*******/
                int cache = 0;
                try{
                    while ((cache = inputStream.read()) != -1){
                        result.add((byte)cache);
                    }
                }catch (Exception e){

                }
                if(result.size() == 0){

                }else {
                    bytes = new byte[result.size()];
                    for(int i = 0 ; i < bytes.length ; i ++){
                        bytes[i] = result.get(i);
                    }
//                    for(int i = 0 ; i < bytes.length ; i ++){
//                        Log.d("NetWorfkFragment" , "打印字节=" + bytes[i]);
//                    }
                    ReSult = new String(bytes , "utf-8");

                    ParseLoginData parseLoginData =new ParseLoginData(getActivity());
                    parseLoginData.ParseJson(ReSult);
                }
                inputStream.close();
            }else {

            }
            HUconn.disconnect();
        }catch (Exception e){
            Log.e("NetWorfkFragment", parseException(e));
        }
        Log.d("NetWorfkFragment","登录返回数据=" + ReSult);
    }

    private void readData(){
        Log.d("NetWorfkFragment","读取登录的缓存信息" + AccountData.getInstance().LoginTaken + "账号" + AccountData.getInstance().Tel);
    }

    public static String parseException(Throwable throwable) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);
        Throwable cause = throwable.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        return result;
    }
}

/***********
 *
 * connection.setRequestProperty("Host", "*******");  //设置请 求的服务器网址，域名，例如***.**.***.***
 connection.setRequestProperty("Content-Type", " application/json");//设定 请求格式 json，也可以设定xml格式的
 connection.setRequestProperty("Accept-Charset", "utf-8");  //设置编码语言
 connection.setRequestProperty("X-Auth-Token", "token");  //设置请求的token
 connection.setRequestProperty("Connection", "keep-alive");  //设置连接的状态
 connection.setRequestProperty("Transfer-Encoding", "chunked");//设置传输编码
 connection.setRequestProperty("Content-Length", obj.toString().getBytes().length + "");  //设置文件请求的长度
 *
 * ************/