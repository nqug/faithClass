package com.NWUG.faithclass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class QuizActivity extends AppCompatActivity
{
    private int currentStage = 0;
    private int currentScore = 0;
    //문제, 선지, 정답 리스트로 정의
    ArrayList<String> questions = new ArrayList<>(Arrays.asList("문제1. 장애인의 날은 언제일까요?", "문제2. 장애인의 반댓말은 무엇일까요?", "문제3. 당신은 시각장애인의 길을 안내하는 안내견을 발견했습니다. 개가 귀여워서 간식을 주고 싶은데, 그래도 될까요?", "문제4. 장애인들이 휠체어를 탄 채로 다른 사람의 도움 없이 안전하고 편리하게 오를 수 있도록 차체 바닥이 낮고 출입구에 계단 대신 경사판이 설치된 버스를 뭐라고 부를까요?", "문제5. 장애는 모두 선천적으로 타고나는것인가요?"));
    ArrayList<String> answers = new ArrayList<>(Arrays.asList("7월 26일", "4월 20일", "비장애인", "정상인", "O", "X", "저상버스", "경사버스", "O", "X"));
    ArrayList<Integer> correctAnswers = new ArrayList<>(Arrays.asList(2, 1, 2, 1, 2));

    //처음 호출되는 함수
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
        //텍스트뷰 찾기
        TextView question_txt = (TextView) findViewById(R.id.txt_question);
        //버튼 찾기
        Button btn1 = (Button) findViewById(R.id.btn_choice1);
        Button btn2 = (Button) findViewById(R.id.btn_choice2);
        //타이틀 장애 퀴즈로 설정
        getSupportActionBar().setTitle("장애 퀴즈");
        //currentStage에 따라 리스트 다르게 호출
        question_txt.setText(questions.get(currentStage));
        btn1.setText(answers.get(currentStage * 2));
        btn2.setText(answers.get(currentStage * 2 + 1));
        //버튼 인식 위한 Listener
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //어떤 버튼 눌렸는지 식별
                if(view.getId() == R.id.btn_choice1)
                {
                    //currentStage에 해당하는 정답일때 currentStage와 currentScore에 1 더하고, 아니면 currentStage만 더함
                    if(correctAnswers.get(currentStage) == 1)
                    {
                        currentScore++;
                    }
                    if(currentStage == 4)
                    {
                        gameOver(currentScore);
                    }
                    else
                    {
                        currentStage++;
                        question_txt.setText(questions.get(currentStage));
                        btn1.setText(answers.get(currentStage * 2));
                        btn2.setText(answers.get(currentStage * 2 + 1));
                    }
                }
                else if(view.getId() == R.id.btn_choice2)
                {
                    if(correctAnswers.get(currentStage) == 2)
                    {
                        currentScore++;
                    }
                    if(currentStage == 4)
                    {
                        gameOver(currentScore);
                    }
                    else
                    {
                        currentStage++;
                        question_txt.setText(questions.get(currentStage));
                        btn1.setText(answers.get(currentStage * 2));
                        btn2.setText(answers.get(currentStage * 2 + 1));
                    }
                }
            }
        };

        //버튼 클릭 인식
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
    }

    //CustomDialog 호출하고 데이터 전송
    private void gameOver(int score)
    {
        Intent intent = new Intent(getApplicationContext(), CustomDialog.class);
        intent.putExtra("data", "대단해요!\n" + score * 20 + "점이에요!");
        startActivityForResult(intent, 1);
    }

    //CustomDialog에 데이터 전송하기 위해 필요한 함수
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
