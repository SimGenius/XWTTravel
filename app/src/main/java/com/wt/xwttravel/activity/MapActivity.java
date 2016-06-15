package com.wt.xwttravel.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.wt.xwttravel.R;

public class MapActivity extends Activity {



    BaiduMap map;
    MapView mapView;
    LatLng locationPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
        mapView = (MapView) findViewById(R.id.mapView);
        map = mapView.getMap();

        setLocationPoint();
        mapView.showZoomControls(false);
        map.addOverlay(
                new MarkerOptions()
                        .position(locationPoint)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_marka))
        );

        map.setMapStatus(MapStatusUpdateFactory.newLatLngZoom(locationPoint, 16f));

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void setLocationPoint(){
        double latitude = getIntent().getDoubleExtra("latitude",-1);
        double longitude = getIntent().getDoubleExtra("longitude",-1);

        locationPoint = new LatLng(latitude,longitude);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }

}
