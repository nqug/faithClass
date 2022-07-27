package com.NWUG.faithclass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

//팝업
public class CustomDialog extends Activity {

    //처음 호출되는 함수
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.game_over_dialog);

        //텍스트뷰 찾기
        TextView txtText = (TextView)findViewById(R.id.txtResult);

        //데이터 가져오기
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        txtText.setText(data);
    }

    //나가기 버튼을 눌렀을때
    public void mOnClose(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);

    //팝업 닫기
        finish();
    }

    //팝업이 뜬 상태에서 외부화면을 클릭했을때
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //팝업 종료 막기
        return event.getAction() != MotionEvent.ACTION_OUTSIDE;
    }

    //뒤로가기 버튼 막기
    @Override
    public void onBackPressed()
    {
        return;
    }
}