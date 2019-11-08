package davidhdezn.app.freniacare.Fragments;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

import davidhdezn.app.freniacare.R;

public class MenuPrincipal extends AppCompatActivity {
Window window;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        this.window = getWindow();
        String primaryDark = "#5c007a";
        String primary = "#8e24aa";
        window.setStatusBarColor(Color.parseColor(primaryDark));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(primary)));
        window.setNavigationBarColor(Color.parseColor(primary));

    getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
    getSupportActionBar().setCustomView(R.layout.txt_layout);




    }
}
