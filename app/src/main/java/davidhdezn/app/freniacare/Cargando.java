package davidhdezn.app.freniacare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

public class Cargando extends AppCompatActivity {
     ProgressBar progressBar;
Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargando);

        init();
        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);

            }
        },  6000);

    }

    private void init(){
        this.progressBar = findViewById(R.id.progressBar);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Cargando.this, MenuPrincipal.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }
}
