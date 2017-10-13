package com.example.jiangnan.gitapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private boolean DEBUG = true;
    private String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sortForTime();
        ListSort();
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
        nums.add(3);
        nums.add(5);
        nums.add(1);
        nums.add(0);
        nums.add(9);
        nums.add(4);
        nums.add(8);
        nums.add(6);
        nums.add(7);

        if(DEBUG) Log.d(TAG, "集合排序前＝" + nums);
        Collections.sort(nums);
        if(DEBUG) Log.d(TAG, "\n集合排序后＝" + nums);
        Collections.sort(nums,Collections.reverseOrder());
        if(DEBUG) Log.d(TAG, "\n集合反转排序后＝" + nums);
    }
}
