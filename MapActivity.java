package com.NWUG.faithclass;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private GoogleMap mMap;

    //처음 호출되는 함수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        //타이틀 지도로 설정
        getSupportActionBar().setTitle("지도");
        //Fragment찾기(지도 표시할 Object)
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        mMap = googleMap;

        //하남시장애인복지관 좌표
        LatLng place = new LatLng(37.559511, 127.188407);
        MarkerOptions markerOptions = new MarkerOptions();
        //마커 좌표를 place로 설정
        markerOptions.position(place);
        //마커 타이틀 설정
        markerOptions.title("하남시장애인복지관");
        //마커 설명 설정
        markerOptions.snippet("하남의 장애인 복지 센터");
        //지도상에 마커 표시
        mMap.addMarker(markerOptions);
        //카메라 마커 좌표로 옮기기, 확대 정도:15
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 15));

    }

}