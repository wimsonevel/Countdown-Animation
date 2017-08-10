package id.co.blogspot.wimsonevel.countdown_animation;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private CountDownTimer countDownTimer;
    private DonutProgress countDownProgress;
    private Button btnStart;

    private int status = 0;
    private final long startTime = 10 * 1000;
    private final long interval = 1 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countDownProgress = (DonutProgress) findViewById(R.id.countdown_progress);
        btnStart = (Button) findViewById(R.id.btn_start);

        countDownProgress.setProgress(10);

        btnStart.setOnClickListener(this);

        countDownTimer = new CountDownTimer(startTime, interval) {
            @Override
            public void onTick(long millisUntilFinished) {
                countDownProgress.setProgress((int) millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                countDownProgress.setProgress(0);
                status = 2;
                btnStart.setText(R.string.reset);
            }
        };
    }

    @Override
    public void onClick(View view) {
        if (status == 0) {
            countDownTimer.start();
            status = 1;
            btnStart.setText(R.string.stop);
        } else if(status == 1){
            countDownTimer.cancel();
            countDownProgress.setProgress(10);
            status = 0;
            btnStart.setText(R.string.start);
        } else {
            countDownProgress.setProgress(10);
            status = 0;
            btnStart.setText(R.string.start);
        }
    }
}
