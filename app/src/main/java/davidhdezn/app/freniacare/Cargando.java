package davidhdezn.app.freniacare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.bumptech.glide.Glide;

public class Cargando extends AppCompatActivity {
    public progressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargando);

        init();
        //progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //progressBar.setVisibility(View.GONE);

            }
        },  6000);

    }

    private void init(){
        //this.progressBar = findViewById(R.id.progressBar);
    }
}
