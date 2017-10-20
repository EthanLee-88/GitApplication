package com.example.jiangnan.gitapplication.Data;

import java.util.ArrayList;

/**
 * Created by jiangnan on 2017/10/18.
 */

public class AccountData {

    private static AccountData familyData = new AccountData();

    private static boolean isCreate = false;

    public String Tel = "";

    public String pwd = "";

    public String LoginTaken = "";

    public ArrayList<Members> allMembers;

    public AccountData(){}

    public static AccountData getInstance() {
        if(!isCreate){
            isCreate = true;
            Create();
        }
        return familyData;
    }

    private static void Create(){
         familyData.allMembers = new ArrayList<Members>();
    }
}
