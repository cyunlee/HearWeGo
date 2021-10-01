package com.android.hearwego;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class BookmarkActivity extends AppCompatActivity {
    private View decorView; //full screen 객체 선언
    private int	uiOption; //full screen 객체 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_bookmark);

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


        Button button_bookmark1 = findViewById(R.id.bookmark1);
        Button button_bookmark2 = findViewById(R.id.bookmark2);
        Button button_bookmark3 = findViewById(R.id.bookmark3);
        Button button_bookmark4 = findViewById(R.id.bookmark4);

        ImageButton button_previous = findViewById(R.id.previous); //이전 이미지 버튼 객체 참조
        ImageButton button_home = findViewById(R.id.home); // 홈 이미지 버튼 객체 참조

        //즐겨찾기 버튼
        button_bookmark1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookmarkActivity.this, BookmarkEdit.class);
                startActivity(intent);
                finish();
            }
        });




        //이전 버튼 누를 시 화면 전환
        button_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookmarkActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //홈 버튼 누를 시 화면 전환
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookmarkActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
