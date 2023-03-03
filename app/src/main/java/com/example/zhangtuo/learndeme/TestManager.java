package com.example.zhangtuo.learndeme;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2023/2/26
 */

class TestManager {

   public static List<Activity> mCallBack  = new ArrayList<>();

   public static  void add(Activity act){
      mCallBack.add(act);
   }

   public static  void remove(Activity act){
      mCallBack.remove(act);
   }


}
