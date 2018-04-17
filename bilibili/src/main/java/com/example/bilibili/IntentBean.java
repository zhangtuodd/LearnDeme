package com.example.bilibili;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/4/16
 */

public class IntentBean {

    public String latitude;//纬度
    public String longitude;//经度
    public String address;//活动地点
    public String addressTitle;//活动标题

    @Override
    public String toString() {
        return "IntentBean{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", address='" + address + '\'' +
                ", addressTitle='" + addressTitle + '\'' +
                '}';
    }
}
