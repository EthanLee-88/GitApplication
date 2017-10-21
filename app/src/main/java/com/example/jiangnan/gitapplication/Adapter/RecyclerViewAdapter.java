package com.example.jiangnan.gitapplication.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jiangnan.gitapplication.Data.Members;
import com.example.jiangnan.gitapplication.R;
import com.example.jiangnan.gitapplication.View.MyCircleImageView;

import java.util.ArrayList;

/**
 * Created by jiangnan on 2017/10/21.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{  //泛型 ,指向自定义的内部类ViewHolder

    private ArrayList<Members> memberData;
    private Context mcontext;
    public OnItemClickListener MonItemClickListener;

    public RecyclerViewAdapter(Context context , ArrayList<Members> memberList){
        memberData = memberList;
        mcontext = context;
        Log.d("RecyclerViewAdapter" , "昵称=" + memberData.get(1).nickname);
    }

    public interface OnItemClickListener {

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        MonItemClickListener = onItemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nickName;
        public MyCircleImageView memberIcon;
        public LinearLayout ll;

        public ViewHolder(View view){
        super(view);
        memberIcon = (MyCircleImageView) view.findViewById(R.id.member_icon);
        nickName = (TextView) view.findViewById(R.id.nick_name);
        memberIcon.isThis = true;
        memberIcon.setBorderWidth(3);
        memberIcon.setBorderColor(Color.BLUE);
        ll = (LinearLayout) view;
        ll.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {    //加载ViewHolder布局文件
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_member_list , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nickName.setText(memberData.get(position).nickname);
        holder.memberIcon.setImageResource(R.drawable.icon_launcher_news_default);
//        holder.memberIcon.setImageDrawable(memberData.get(position).i);
    }

    @Override
    public int getItemCount() {
        return memberData.size();
    }
}
