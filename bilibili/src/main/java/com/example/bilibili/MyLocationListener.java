package com.example.bilibili;

import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.utils.CoordinateConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/4/14
 */
public class MyLocationListener extends BDAbstractLocationListener implements OnGetPoiSearchResultListener {

    BaiduMap baiduMap;
    MapView mMapView;
    int locType;
    List<PoiInfoBean> dataBelowList = new ArrayList<>();
    List<PoiInfoBean> dataTopList = new ArrayList<>();
    private MyBelowAdapter myBelowAdapter;
    private MyTopAdapter myTopAdapter;
    PoiSearch mPoiSearch;
    BaiduMapActivity baiduMapActivity;
    EditText etInput;
    boolean successFlag = false;
    BDLocation location;
    //判断是否是底部list适配数据（只有第一次是底部list加载）
    private boolean firstFlag = false;

    public MyLocationListener(MapView mMapView, MyBelowAdapter adapter, MyTopAdapter myTopAdapter, BaiduMapActivity baiduMapActivity) {
        this.mMapView = mMapView;
        this.myBelowAdapter = adapter;
        this.myTopAdapter = myTopAdapter;
        this.baiduMapActivity = baiduMapActivity;
        this.myBelowAdapter.setListener(this);
        this.myTopAdapter.setListener(this);
        firstFlag = true;
        etInput = this.baiduMapActivity.etSearchTwoInput;
    }

    private String actTitle;
    private double latitude;
    private double longitude;

    float direction;// 获取手机方向，【0~360°】,手机上面正面朝北为0°
    String district;// 区县
    String province;// 省份
    String city;// 城市
    String addrStr;// 获取反地理编码(文字描述的地址)
    float radius;//获取定位精度，默认值为0.0f

    @Override
    public void onReceiveLocation(BDLocation location) {
        if (location == null || mMapView == null || successFlag) {
            return;
        }
        this.location = location;
        etInput.addTextChangedListener(watcher);
        actTitle = baiduMapActivity.actTitle;
        latitude = baiduMapActivity.latitude;
        longitude = baiduMapActivity.longitude;

        baiduMap = mMapView.getMap();

        locType = location.getLocType();
        Log.i("mybaidumap", "当前定位的返回值是：" + locType);


        if (location.hasRadius()) {// 判断是否有定位精度半径
            radius = location.getRadius();
        }

        if (locType == BDLocation.TypeNetWorkLocation) {
            addrStr = location.getAddrStr();
            Log.i("mybaidumap", "当前定位的地址是：" + addrStr);
        }

        direction = location.getDirection();
        province = location.getProvince();
        city = location.getCity();
        district = location.getDistrict();

//        LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
        LatLng ll = new LatLng(latitude, longitude);

        //将当前位置加入List里面
        PoiInfoBean infoBean = new PoiInfoBean();
        PoiInfo info = new PoiInfo();
        info.address = location.getAddrStr();
        info.city = location.getCity();
        info.location = ll;
        info.name = location.getAddrStr();
        infoBean.info = info;
        infoBean.isChecked = true;

        dataBelowList.clear();
        dataBelowList.add(infoBean);
        Log.i("mybaidumap", "province是：" + province + " city是" + city + " 区县是: " + district);
        setLocation(info);


        mMapView.showZoomControls(true);// 显示默认的缩放控件
        mMapView.showScaleControl(true);// 显示默认比例尺控件
        //poi 搜索周边
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                if (firstFlag) {
                    //第一次进入，必须通过三个参数的周边查询，避免查找不出来
                    searchByNear();
                } else {
                    searchByArea(actTitle);
                }
                Looper.loop();
            }
        }).start();


    }

    public void setLocation(PoiInfo info) {
        if (info == null) {
            return;
        }
        LatLng ll = info.location;
        double latitude = ll.latitude;
        double longitude = ll.longitude;
        String address = info.address;
        String title = info.name;
        IntentBean intentBean = new IntentBean();
        intentBean.address = address;
        intentBean.addressTitle = title;
        intentBean.latitude = Double.toString(latitude);
        intentBean.longitude = Double.toString(longitude);
        baiduMapActivity.setIntentData(intentBean);
        baiduMap.clear();
        // 构造定位数据
        MyLocationData locData = new MyLocationData.Builder()
//                .accuracy(0)//设置定位光圈
                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(latitude)
                .longitude(longitude).build();
        baiduMap.setMyLocationData(locData);

        //画标志
        CoordinateConverter converter = new CoordinateConverter();
        converter.coord(ll);
        converter.from(CoordinateConverter.CoordType.COMMON);
        LatLng convertLatLng = converter.convert();

        OverlayOptions ooA = new MarkerOptions().position(ll).icon(BitmapDescriptorFactory.fromResource(R.mipmap.module_biz_activity_ic_openmap_focuse_mark));
        Marker mCurrentMarker = (Marker) baiduMap.addOverlay(ooA);

        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(convertLatLng, 17.0f);
        baiduMap.animateMapStatus(u);

        //画当前定位标志
        MapStatusUpdate uc = MapStatusUpdateFactory.newLatLng(ll);
        baiduMap.animateMapStatus(uc);
    }

    /**
     * 通过区域搜索，需要1个参数
     * <p>
     * 关键字
     *
     * @param actTitle
     */
    public void searchByArea(String actTitle) {
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
        mPoiSearch.searchInCity((new PoiCitySearchOption())
                .city("北京")
                .keyword(actTitle)
                .pageNum(10));
    }


    /**
     * 通过周边搜索，需要三个参数
     * 经纬度/关键字
     */
    public void searchByNear() {
        // POI初始化搜索模块，注册搜索事件监听
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
        PoiNearbySearchOption poiNearbySearchOption = new PoiNearbySearchOption();
        poiNearbySearchOption.keyword(actTitle);
        poiNearbySearchOption.location(new LatLng(latitude, longitude));
        poiNearbySearchOption.radius(100);  // 检索半径，单位是米
        poiNearbySearchOption.pageCapacity(10);  // 默认每页10条
        mPoiSearch.searchNearby(poiNearbySearchOption);  // 发起附近检索请求
    }

    @Override
    public void onGetPoiResult(PoiResult result) {
        if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {// 没有找到检索结果
//            Toast.makeText(baiduMapActivity, "只找到一个", Toast.LENGTH_SHORT).show();
            if (firstFlag == true) {//底部list加载数据
                firstFlag = false;
            } else {
                dataTopList.clear();
                myTopAdapter.bindData(dataTopList);
            }
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {// 检索结果正常返回
            successFlag = true;
            if (result != null) {
                List<PoiInfo> allPoi = result.getAllPoi();
                if (allPoi != null && allPoi.size() > 0) {
                    if (firstFlag == true) {//底部list加载数据
                        firstFlag = false;
                        for (int i = 0; i < allPoi.size(); i++) {
                            PoiInfoBean infoBean = new PoiInfoBean();
                            infoBean.info = allPoi.get(i);
                            infoBean.isChecked = false;
                            dataBelowList.add(infoBean);
                        }
                        myBelowAdapter.bindData(dataBelowList);
                    } else {//顶部list加载数据
                        dataTopList.clear();
                        firstFlag = false;
                        for (int i = 0; i < allPoi.size(); i++) {
                            PoiInfoBean infoBean = new PoiInfoBean();
                            infoBean.info = allPoi.get(i);
                            infoBean.isChecked = false;
                            dataTopList.add(infoBean);
                        }
                        myTopAdapter.bindData(dataTopList);
                    }
                }
            }
        }
        if (mPoiSearch != null) {
            mPoiSearch.destroy();
        }
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s != null && s.toString() != null && s.length() > 0) {
                Log.i("tag", s.toString());
                searchByArea(s.toString());

            }
        }
    };


    /**
     * 通过top列表的item点击去获取below列表
     *
     * @param info
     */
    public void byTopItemRefreshBelow(PoiInfo info) {
        firstFlag = true;
        dataBelowList.clear();
        PoiInfoBean infoBean = new PoiInfoBean();
        infoBean.info = info;
        infoBean.isChecked = true;
        dataBelowList.add(infoBean);
        searchByArea(info.name);

    }
}

