package com.android.hearwego;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SurroundingChoiceActivity extends AppCompatActivity {

    private View decorView; //full screen 객체 선언
    private int	uiOption; //full screen 객체 선언

    /*텍스트뷰 선언*/
    TextView nameText;
    TextView addressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.surrounding_choice);

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

        Button button_add_bookmark = findViewById(R.id.add_bookmark);
        Button button_set_destination = findViewById(R.id.set_destination);

        ImageButton button_previous = findViewById(R.id.previous); //이전 이미지 버튼 객체 참조
        ImageButton button_home = findViewById(R.id.home); // 홈 이미지 버튼 객체 참조

        /*이전 버튼 누를 시 화면 전환*/
        button_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        /*홈 버튼 누를 시 화면 전환*/
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SurroundingChoiceActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        /*인텐트 받아들임*/
        Intent intent = getIntent();
        String nameData = intent.getStringExtra("name");
        String addressData = intent.getStringExtra("address");
        Double latitude = Double.parseDouble(intent.getStringExtra("latitude"));
        Double longitude = Double.parseDouble(intent.getStringExtra("longitude"));

        Log.d("현재-위치!", nameData + addressData);

        /*목적지 이름, 주소 텍스트 설정 (주소는 동까지만 뜸. )*/
        nameText = findViewById(R.id.destination);
        nameText.setText(nameData);
        addressText = findViewById(R.id.destination_address);
        addressText.setText(addressData);

        /*'목적지로 설정' 버튼 눌렀을 때*/
        button_set_destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*인텐트 보냄*/
                Intent dest_intent = new Intent(SurroundingChoiceActivity.this, RouteGuideActivity.class);
                dest_intent.putExtra("name", nameData);
                dest_intent.putExtra("latitude", latitude);
                dest_intent.putExtra("longitude", longitude);
                startActivity(dest_intent);
            }
        });


    }
}