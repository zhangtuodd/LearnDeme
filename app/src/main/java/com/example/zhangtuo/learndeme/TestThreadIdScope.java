package com.example.zhangtuo.learndeme;

import com.example.base.util.LogUtils;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2023/3/12
 */

class TestThreadIdScope extends Thread{
   public TestThreadIdScope(){
      LogUtils.e("TestThreadIdScope--construct-", Thread.currentThread().getName() + "----1");

   }

   @Override
   public void run() {
      LogUtils.e("TestThreadIdScope--run-", Thread.currentThread().getName() + "----1");

   }
}
