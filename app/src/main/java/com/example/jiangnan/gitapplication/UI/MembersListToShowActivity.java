package com.example.jiangnan.gitapplication.UI;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jiangnan.gitapplication.R;

/**
 * Created by jiangnan on 2017/10/20.
 */

public class MembersListToShowActivity extends AppCompatActivity {
    @Override
    public void onCreate( Bundle savedInstanceState,  PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_members_list_to_show);
    }
}
