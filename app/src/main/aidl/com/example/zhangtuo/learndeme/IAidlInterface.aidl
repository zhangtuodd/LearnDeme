// IAidlInterface.aidl
package com.example.zhangtuo.learndeme;

// Declare any non-default types here with import statements

interface IAidlInterface {

    void login(String name,String pwd);

//成功后返回token
    String loginCallBack();
}