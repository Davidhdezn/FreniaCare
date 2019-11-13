package davidhdezn.app.freniacare.InicialApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

import davidhdezn.app.freniacare.NavigationDrawer;
import davidhdezn.app.freniacare.R;

public class Cargando extends AppCompatActivity {
     ProgressBar progressBar;
     Window window;
Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargando);

        this.window = getWindow();
        String primaryDark = "#5c007a";
        String primary = "#8e24aa";
        window.setStatusBarColor(Color.parseColor(primaryDark));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(primary)));
        window.setNavigationBarColor(Color.parseColor(primary));

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
                Intent intent = new Intent(Cargando.this, NavigationDrawer.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}
