package com.android.hearwego;

import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.Locale;

public class DestinationSearchActivity extends AppCompatActivity implements TMapGpsManager.onLocationChangedCallback {

    TMapView tMapView = null;
    TMapGpsManager tMapGpsManager = null;
    TMapData tMapData = null;

    private static String API_KEY = "l7xx59d0bb77ddfc45efb709f48d1b31715c";

    TextToSpeech textToSpeech;

    private View decorView; //full screen 객체 선언
    private int	uiOption; //full screen 객체 선언


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.destination_search);

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

        //TextToSpeech 기본 설정
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.KOREAN);
                }
            }
        });

        //현재 위치 받아오기
        tMapData = new TMapData();

        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey(API_KEY);

        //TmapGps 설정
        tMapGpsManager = new TMapGpsManager(this);
        tMapGpsManager.setMinTime(1000);
        tMapGpsManager.setMinDistance(5);
        tMapGpsManager.setProvider(tMapGpsManager.NETWORK_PROVIDER);
        tMapGpsManager.OpenGps();

        //버튼 선언
        ImageButton nowgps_btn = findViewById(R.id.button1_nowgps);
        ImageButton previous = findViewById(R.id.previous);
        ImageButton home = findViewById(R.id.home);

        //현재위치확인 버튼 누르면 현재위치를 음성으로 안내할 수 있게 구현
        nowgps_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TMapPoint nowpoint = tMapView.getLocationPoint();

                tMapData.convertGpsToAddress(nowpoint.getLatitude(), nowpoint.getLongitude(), new TMapData.ConvertGPSToAddressListenerCallback() {
                    @Override
                    public void onConvertToGPSToAddress(String s) {
                        textToSpeech.setPitch(1.5f);
                        textToSpeech.setSpeechRate(1.0f);
                        textToSpeech.speak("현재 위치는 "+s+"입니다", TextToSpeech.QUEUE_FLUSH, null);
                    }
                });
            }
        });

        //이전 버튼 선언
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DestinationSearchActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //홈 버튼 선언
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DestinationSearchActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });




    }

    @Override
    public void onLocationChange(Location location) {
        tMapView.setLocationPoint(location.getLongitude(), location.getLatitude());
        Log.d("현재위치", tMapView.getLocationPoint().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(textToSpeech!=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
            textToSpeech = null;
        }
    }
}
