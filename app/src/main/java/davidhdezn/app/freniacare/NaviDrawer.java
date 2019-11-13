package davidhdezn.app.freniacare;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.telephony.SmsManager;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import davidhdezn.app.freniacare.MenuPrincipal.EstadoDeAnimo;

public class NaviDrawer extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
Window window;
Button  EstadoDeAnimo = (Button) findViewById(R.id.btnEstadodeAnimo);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_draw);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.window = getWindow();
        String primaryDark = "#5c007a";
        String primary = "#8e24aa";
        window.setStatusBarColor(Color.parseColor(primaryDark));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(primary)));
        window.setNavigationBarColor(Color.parseColor(primary));



        /**
        Button Enviar;
        Enviar = (Button) findViewById(R.id.enviar);


        if(ActivityCompat.checkSelfPermission(
                NaviDrawer.this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(
                NaviDrawer.this,Manifest
                        .permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(NaviDrawer.this,new String[]
                    { Manifest.permission.SEND_SMS,},1000);
        }else{
        };
        Enviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enviarMensaje("2461847491","Hola!! Prueba Frenia-Care");
            }
        });
    }
    private void enviarMensaje (String numero, String mensaje){
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(numero,null,mensaje,null,null);
            Toast.makeText(getApplicationContext(), "Mensaje Enviado.", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Mensaje no enviado, datos incorrectos.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        **/


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navi_drawer, menu);
        return true;
    }
//,l,l
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}
