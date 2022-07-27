package com.NWUG.faithclass;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class BrainGameActivity extends AppCompatActivity {
    private int firstRdInt = 1;
    private int secondRdInt = 1;
    private Timer timerCall;
    private int nCnt;
    private boolean isRunning = true;
    String playerInput = "";
    private int stageCount = 0;
    Random rd = new Random();

    //처음 호출되는 함수
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brain_game);
        getSupportActionBar().setTitle("두뇌력 향상 게임");
        firstRdInt = (rd.nextInt(98) + 1);
        secondRdInt = (rd.nextInt(98) + 1);
        //텍스트뷰 찾기
        TextView inputView = (TextView) findViewById(R.id.playerInput);
        TextView scoreView = (TextView) findViewById(R.id.score);
        TextView randomQuestion = (TextView) findViewById(R.id.randomQuestion);
        //버튼 찾기
        Button btn_0 = (Button) findViewById(R.id.btn_number0);
        Button btn_1 = (Button) findViewById(R.id.btn_number1);
        Button btn_2 = (Button) findViewById(R.id.btn_number2);
        Button btn_3 = (Button) findViewById(R.id.btn_number3);
        Button btn_4 = (Button) findViewById(R.id.btn_number4);
        Button btn_5 = (Button) findViewById(R.id.btn_number5);
        Button btn_6 = (Button) findViewById(R.id.btn_number6);
        Button btn_7 = (Button) findViewById(R.id.btn_number7);
        Button btn_8 = (Button) findViewById(R.id.btn_number8);
        Button btn_9 = (Button) findViewById(R.id.btn_number9);
        Button btn_C = (Button) findViewById(R.id.btn_clear);
        Button btn_enter = (Button) findViewById((R.id.btn_enter));

        randomQuestion.setText(firstRdInt+ " + " + secondRdInt + " =");
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //각 버튼을 눌렀을때 playerInput에 문자열 추가
                if(view.getId() == R.id.btn_number0)
                {
                    playerInput = (String) playerInput + "0";
                    inputView.setText(playerInput);
                }
                else if(view.getId() == R.id.btn_number1)
                {
                    playerInput = (String) playerInput + "1";
                    inputView.setText(playerInput);
                }
                else if(view.getId() == R.id.btn_number2)
                {
                    playerInput = (String) playerInput + "2";
                    inputView.setText(playerInput);
                }
                else if(view.getId() == R.id.btn_number3)
                {
                    playerInput = (String) playerInput + "3";
                    inputView.setText(playerInput);
                }
                else if(view.getId() == R.id.btn_number4)
                {
                    playerInput = (String) playerInput + "4";
                    inputView.setText(playerInput);
                }
                else if(view.getId() == R.id.btn_number5)
                {
                    playerInput = (String) playerInput + "5";
                    inputView.setText(playerInput);
                }
                else if(view.getId() == R.id.btn_number6)
                {
                    playerInput = (String) playerInput + "6";
                    inputView.setText(playerInput);
                }
                else if(view.getId() == R.id.btn_number7)
                {
                    playerInput = (String) playerInput + "7";
                    inputView.setText(playerInput);
                }
                else if(view.getId() == R.id.btn_number8)
                {
                    playerInput = (String) playerInput + "8";
                    inputView.setText(playerInput);
                }
                else if(view.getId() == R.id.btn_number9)
                {
                    playerInput = (String) playerInput + "9";
                    inputView.setText(playerInput);
                }
                else if(view.getId() == R.id.btn_clear)
                {
                    playerInput = "";
                    inputView.setText(playerInput);
                }
                //입력버튼을 눌렀을 때
                else if(view.getId() == R.id.btn_enter)
                {
                    //만약 playerInput이 정답과 일치한다면 stageCount에 1을 더함
                    if(Objects.equals(playerInput, "" + (firstRdInt + secondRdInt)))
                    {
                        playerInput = "";
                        stageCount++;
                        //5번 정답을 맞출경우, 팝업을 뜨게 하고 팝업에 게임 종료시 문구를 전달
                        if(stageCount == 5)
                        {
                            isRunning = false;
                            Intent intent = new Intent(getApplicationContext(), CustomDialog.class);
                            intent.putExtra("data", "대단해요!\n5문제를 " + nCnt + "초만에\n풀어내셨어요!");
                            startActivityForResult(intent, 1);
                        }
                        else
                        {
                            firstRdInt = (rd.nextInt(98) + 1);
                            secondRdInt = (rd.nextInt(98) + 1);
                            randomQuestion.setText(firstRdInt+ " + " + secondRdInt + " =");
                        }
                    }
                    else
                    {
                        playerInput = "";
                    }
                    inputView.setText(playerInput);
                }
            }
        };
        nCnt = -1;

        //1초마다 work 함수 호출
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                work();
                scoreView.setText("소요된 시간: " + nCnt);
            }
        };

        //리스너로 버튼이 눌리는지 감지함.
        btn_0.setOnClickListener(listener);
        btn_1.setOnClickListener(listener);
        btn_2.setOnClickListener(listener);
        btn_3.setOnClickListener(listener);
        btn_4.setOnClickListener(listener);
        btn_5.setOnClickListener(listener);
        btn_6.setOnClickListener(listener);
        btn_7.setOnClickListener(listener);
        btn_8.setOnClickListener(listener);
        btn_9.setOnClickListener(listener);
        btn_C.setOnClickListener(listener);
        btn_enter.setOnClickListener(listener);

        //1초마다 timerTask를 수행하도록 설정
        timerCall = new Timer();
        timerCall.schedule(timerTask, 0, 1000);
    }

    private void work(){
        Log.d("test==>", nCnt + "work!!");
        //isRunning이 거짓일 경우 문제를 맞췄다는 뜻 -> 초세기 취소
        if(isRunning == false){
            timerCall.cancel();
            nCnt--;
        }
        nCnt++;
    }

    //CustomDialog와 데이터를 주고 받기 위한 함수
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            //CustomDialog의 나가기 버튼이 눌리면 RESULT_OK신호를 받음 -> MainActivity로 돌아감
            if (resultCode == RESULT_OK) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }
    }
}