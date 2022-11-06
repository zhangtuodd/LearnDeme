package com.example.bilibili;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;

import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/4/14
 */

public class BaiduMapActivity extends Activity implements View.OnClickListener {
    public RelativeLayout headView;
    TextView tvHeadSelect;
    TextView tvHeadCancel;

    LinearLayout llSearchOne;
    public TextView tvSearchOne;
    public FrameLayout llSearchTwo;


    EditText etSearchTwoInput;
    TextView tvSearchTwoCancel;

    MapView mMapView;

    ListView mTopList;
    ListView mBelowList;
    MyBelowAdapter myBelowAdapter;
    MyTopAdapter myTopAdapter;

    public LocationClient mLocationClient = null;
    private MyLocationListener myListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SDKInitializer.initialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_biz_activity_map_layout);
        initView();
    }

    public String actTitle;
    public double latitude;
    public double longitude;

    private void initView() {
        actTitle = "北京云漾科技";
        latitude = 40.08588;
        longitude = 116.353743;
        headView = findViewById(R.id.module_biz_activity_map_search_all_head_view);
        llSearchOne = findViewById(R.id.module_biz_activity_map_search_one_ll);
        tvSearchOne = findViewById(R.id.module_biz_activity_map_search_one_text);
        llSearchTwo = findViewById(R.id.module_biz_activity_map_search_two_ll);

        tvHeadCancel = findViewById(R.id.module_biz_activity_map_search_head_view_cancel);
        tvHeadSelect = findViewById(R.id.module_biz_activity_map_search_head_select);

        etSearchTwoInput = findViewById(R.id.module_biz_activity_map_search_two_et);
        tvSearchTwoCancel = findViewById(R.id.module_biz_activity_map_search_two_cancel);

        llSearchOne.setOnClickListener(this);
        llSearchTwo.setOnClickListener(this);
        headView.setOnClickListener(this);
        tvSearchTwoCancel.setOnClickListener(this);
        tvHeadCancel.setOnClickListener(this);
        tvHeadSelect.setOnClickListener(this);
        mMapView = findViewById(R.id.module_biz_activity_map_search_map_view);
        mTopList = findViewById(R.id.module_biz_activity_map_search_top_list);
        mBelowList = findViewById(R.id.module_biz_activity_map_search_below_list);
        myBelowAdapter = new MyBelowAdapter(this);
        mBelowList.setAdapter(myBelowAdapter);

        myTopAdapter = new MyTopAdapter(this);
        mTopList.setAdapter(myTopAdapter);


        myListener = new MyLocationListener(mMapView, myBelowAdapter, myTopAdapter, this);
        BaiduMap mBaiduMap = mMapView.getMap();
        mBaiduMap.clear();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(17).build()));   // 设置级别
        // 定位初始化
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        initLocation();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }


    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mLocationClient.unRegisterLocationListener(myListener);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.module_biz_activity_map_search_one_ll) {
            headView.setVisibility(View.GONE);
            llSearchTwo.setVisibility(View.VISIBLE);
        } else if (id == R.id.module_biz_activity_map_search_two_cancel) {
            headView.setVisibility(View.VISIBLE);
            llSearchTwo.setVisibility(View.GONE);
        } else if (id == R.id.module_biz_activity_map_search_head_view_cancel) {
            this.finish();
        } else if (id == R.id.module_biz_activity_map_search_head_select) {
            this.finish();
        }
    }

    private IntentBean bean;

    public void setIntentData(IntentBean intentBean) {
        this.bean = intentBean;
        Log.i("tag", "bean -- toString -- >> " + bean.toString());
    }
}
