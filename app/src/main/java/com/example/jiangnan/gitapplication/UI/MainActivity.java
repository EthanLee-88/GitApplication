package com.example.jiangnan.gitapplication.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jiangnan.gitapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button getData;
    private boolean DEBUG = true;
    private String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData = (Button) findViewById(R.id.into_net);
        get();

        sortForTime();
        ListSort();
    }

    private void get(){
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(MainActivity.this , NetWorkActivity.class);
                startActivity(mIntent);
            }
        });
    }

    /***********************************************时间从小到大的排序算法*******************************************************/
    private void sortForTime(){

        int [] b = new int[] {4 , 7 ,9 ,1 ,4 ,6 ,5 ,8 ,2};
        for(int i = 0 ; i < b.length ; i ++){
            if(DEBUG) Log.d(TAG, "数组元素＝" + b[i]);
        }

        Arrays.sort(b);
        for(int i = 0 ; i < b.length ; i ++){
            if(DEBUG) Log.d(TAG, "数组排序＝" + b[i]);
        }

//        int listLength = alarmDataList.size();
//
//        for (int i = 0; i < listLength; i++) {
//            String[] timeString = alarmDataList.get(0).remindTime.split(":");
//
//            int minTime = Integer.parseInt(timeString[0]) * 60 + Integer.parseInt(timeString[1]);
//            int minTimeIndex = 0;
//
//            for (int j = 0; j < alarmDataList.size(); j++) {
//                if(DEBUG) Log.d(TAG, "数组列表＝" + alarmDataList.get(j).remindTime);
//                timeString = alarmDataList.get(j).remindTime.split(":");
//                if(minTime > Integer.parseInt(timeString[0]) * 60 + Integer.parseInt(timeString[1])){
//                    minTime = Integer.parseInt(timeString[0]) * 60 + Integer.parseInt(timeString[1]);
//                    minTimeIndex = j;
//                }
//            }
//            alarmDataListForSort.add(alarmDataList.get(minTimeIndex));
//            alarmDataList.remove(minTimeIndex);
//        }
//
//        if(DEBUG) Log.d(TAG, "数组长度＝" + alarmDataListForSort.size());
//        if(DEBUG) Log.d(TAG, "排序后数组长度＝" + alarmDataList.size());
//
//        for (int j = 0; j < alarmDataListForSort.size(); j++) {
//            if(DEBUG) Log.d(TAG, "排序后的时间数组＝" + alarmDataListForSort.get(j).remindTime);
//            alarmDataList.add(alarmDataListForSort.get(j));
//        }
    }

    public void ListSort() {
        List<Integer> nums = new ArrayList<Integer>();
        List<Integer> arr = new ArrayList<Integer>();

        nums.add(3);
        nums.add(5);
        nums.add(1);
        nums.add(0);
        nums.add(9);
        nums.add(4);
        nums.add(8);
        nums.add(6);
        nums.add(7);

        arr = nums;

        if(DEBUG) Log.d(TAG, "集合排序前＝" + nums);
        Collections.sort(nums);
        if(DEBUG) Log.d(TAG, "\n集合排序后＝" + nums);
        Collections.sort(nums,Collections.reverseOrder());
        if(DEBUG) Log.d(TAG, "\n集合反转排序后＝" + nums);

        //冒泡算法
        if(DEBUG) Log.d(TAG, "\n集合冒泡排序前＝" + arr);
        for(int i=arr.size()-1;i>0;i--){//让比较的范围不停的减掉最后一个单元
            for(int j=1;j<=i;j++){
                if(arr.get(j-1)>arr.get(j)){//让2个数之间大的数排后面
                    int tmp = arr.get(j - 1);
                    arr.set(j - 1 , arr.get(j));
//                    arr.get(j - 1) = arr.get(j);
                    arr.set(j , tmp);
//                    arr.get(j) = tmp;
                }
            }
        }
        if(DEBUG) Log.d(TAG, "\n集合冒泡排序后＝" + arr);
    }

}
