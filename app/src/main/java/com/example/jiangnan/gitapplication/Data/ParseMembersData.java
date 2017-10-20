package com.example.jiangnan.gitapplication.Data;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.jiangnan.gitapplication.UI.MembersListToShowActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by jiangnan on 2017/10/20.
 */

public class ParseMembersData {
    private Context mcontext;

    private ArrayList<Members> memberses;

    public ParseMembersData(Context context){
        mcontext = context;
    }

    public void ParseMember (String members ){
        memberses = new ArrayList<Members>();
        try {
            JSONObject memberObject = new JSONObject(members);

            String code = memberObject.getString("code");

            if(code.equals("0")){
            JSONArray datac = memberObject.getJSONArray("data");

                for(int i = 0 ; i < datac.length() ;i ++){

                    JSONObject index = datac.getJSONObject(i);

                    Gson gson = new Gson();

                    Members members1 = gson.fromJson(index.toString() , Members.class);

                    memberses.add(members1);
                }
            }
        }catch (Exception e){
            Log.d("ParseMembersData" ,parseException(e));
        }
        if(memberses.size() != 0){
            AccountData.getInstance().allMembers.clear();
            AccountData.getInstance().allMembers.addAll(memberses);
            Log.d("" , "姓名=" + AccountData.getInstance().allMembers.get(0).nickname + "\n长度=" + AccountData.getInstance().allMembers.size());

            Intent intent = new Intent(mcontext , MembersListToShowActivity.class);
            mcontext.startActivity(intent);
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
