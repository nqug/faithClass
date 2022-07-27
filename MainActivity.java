package com.NWUG.faithclass;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //버튼 찾기
        Button button1 = (Button) findViewById(R.id.btn1);
        Button button2 = (Button) findViewById(R.id.btn2);
        Button button3 = (Button) findViewById(R.id.btn3);
        //버튼 클릭을 인식하기 위한 listener 정의
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            //버튼 클릭을 인식하였을때
            public void onClick(View view) {
                //버튼 아이디로 어떤 버튼이 눌렸는지 식별
                if(view.getId() == R.id.btn1)
                {
                    Intent intent = new Intent(getApplicationContext(), BrainGameActivity.class);
                    startActivity(intent);
                }
                else if(view.getId() == R.id.btn2)
                {
                    Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                    startActivity(intent);
                }
                else if(view.getId() == R.id.btn3)
                {
                    Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                    startActivity(intent);
                }
            }
        };

        //버튼에 listener 추가하여 클릭 감지
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
    }

}