package com.android.hearwego;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SurroundingActivity extends AppCompatActivity {

    private View decorView; //full screen 객체 선언
    private int	uiOption; //full screen 객체 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surrounding);

        ActionBar actionBar = getSupportActionBar(); //액션바(패키지명) 숨김처리
        actionBar.hide();

        /*전체 화면 모드 -> 소프트 키 없앰*/
        decorView = getWindow().getDecorView();
        uiOption = getWindow().getDecorView().getSystemUiVisibility();
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH )
            uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN )
            uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT )
            uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility( uiOption );


        Button button_facility = findViewById(R.id.menu_facility); //공공시설 이미지 버튼 객체 참조
        Button button_subway = findViewById(R.id.menu_subway); //지하철 이미지 버튼 객체 참조
        Button button_restroom = findViewById(R.id.menu_restroom); //화장실 이미지 버튼 객체 참조
        Button button_hospital = findViewById(R.id.menu_hospital); //병원 이미지 버튼 객체 참조
        Button button_pharmacy = findViewById(R.id.menu_pharmacy); //약국 이미지 버튼 객체 참조

        Button button_previous = findViewById(R.id.previous); //이전 이미지 버튼 객체 참조
        Button button_home = findViewById(R.id.home); // 홈 이미지 버튼 객체 참조

        //공공시설 버튼 누를 시 화면 전환
        button_facility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SurroundingActivity.this, FacilityActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //지하철역 버튼 누를 시 화면 전환
        button_subway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SurroundingActivity.this, SubwayActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //화장실 버튼 누를 시 화면 전환
        button_restroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SurroundingActivity.this, RestroomActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //병원 버튼 누를 시 화면 전환
        button_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SurroundingActivity.this, HospitalActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //약국 버튼 누를 시 화면 전환
        button_pharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SurroundingActivity.this, PharmacyActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //이전 버튼 누를 시 화면 전환
        button_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SurroundingActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //홈 버튼 누를 시 화면 전환
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SurroundingActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
