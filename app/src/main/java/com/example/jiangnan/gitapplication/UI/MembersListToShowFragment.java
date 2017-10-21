package com.example.jiangnan.gitapplication.UI;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jiangnan.gitapplication.Adapter.RecyclerViewAdapter;
import com.example.jiangnan.gitapplication.Data.AccountData;
import com.example.jiangnan.gitapplication.R;
import com.example.jiangnan.gitapplication.Util.ThreadPoolUtil;

/**
 * Created by jiangnan on 2017/10/20.
 */

public class MembersListToShowFragment extends Fragment{
    private RecyclerView memberListView;
    private RecyclerViewAdapter mrecyclerViewAdapter;
    private Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_members_list_to_show , container , false);
        initRes(view);
        Log.d("MembersListToShowFragment" , "设置接口");
        return view;
    }

    private void initRes(View view){
        memberListView = (RecyclerView) view.findViewById(R.id.members_view);
        memberListView.setLayoutManager(new GridLayoutManager(getActivity() , 3));
        upDate();
    }
    private void upDate(){
//        ThreadPoolUtil.getInstance().execute(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });

        mrecyclerViewAdapter = new RecyclerViewAdapter(getActivity() , AccountData.getInstance().allMembers);
        memberListView.setAdapter(mrecyclerViewAdapter);
    }
}
