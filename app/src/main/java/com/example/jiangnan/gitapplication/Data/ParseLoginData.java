package com.example.jiangnan.gitapplication.Data;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by jiangnan on 2017/10/18.
 */

public class ParseLoginData {
    private Context mContext;

    public ParseLoginData (Context context){
        mContext = context;
    }

    public void ParseJson(String json){
        try{
            JSONObject LoginObject = new JSONObject(json);

            JSONObject data = LoginObject.getJSONObject("data");

            AccountData.getInstance().LoginTaken = data.getString("token");

            AccountData.getInstance().Tel = data.getString("tel");

            AccountData.getInstance().pwd = data.getString("pwd");

            Log.d("ParseLoginData","token值=" + AccountData.getInstance().LoginTaken + "\n电话号码=" + AccountData.getInstance().Tel);

            if(!AccountData.getInstance().LoginTaken.isEmpty()){
                GetMembers getMembers = new GetMembers(mContext);
                getMembers.getMembers(AccountData.getInstance().Tel , AccountData.getInstance().LoginTaken);
            }

        }catch (Exception e){
                Log.e("ParseLoginData" , parseException(e));

        }
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
