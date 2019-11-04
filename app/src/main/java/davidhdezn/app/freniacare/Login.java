package davidhdezn.app.freniacare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mBtnLogin;

    private FirebaseAuth mAuth ;

    private String email = "";
    private String password = "";

    Window window;
    TextView btnTxt;
    SignInButton signin;
    int RC_SIGN_IN = 0;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signin = findViewById(R.id.sign_in_button);
        btnTxt = (TextView) findViewById(R.id.txtRegistrarse);

        mEditTextEmail = (EditText) findViewById(R.id.editTextCorreo);
        mEditTextPassword = (EditText) findViewById(R.id.editTextPassword);
        mBtnLogin= (Button) findViewById(R.id.btnLogin);
        mAuth = FirebaseAuth.getInstance();

        //***Barra de color
        this.window = getWindow();
        String primaryDark = "#5c007a";
        String primary = "#8e24aa";
        //String background = "#757575";
        window.setStatusBarColor(Color.parseColor(primaryDark));
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(primary)));
        //window.setBackgroundDrawable(new ColorDrawable(Color.parseColor(background)));
        window.setNavigationBarColor(Color.parseColor(primary));

        //*** Logeo de usuario
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = mEditTextEmail.getText().toString();
                password = mEditTextPassword.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()){
                    loginUser();
                }else {
                    Toast.makeText(Login.this, "Debe completar todos los campos", Toast.LENGTH_LONG).show();
                }
            }
        });




//***Google****
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;

                }

            }
        });

        btnTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( Login.this, RegistroUsuario.class));
                finish();
                }

            }
        );

    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build();

    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
}


private void signIn() {
    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
    startActivityForResult(signInIntent, RC_SIGN_IN);
}
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Intent intent = new Intent(Login.this, Cargando.class);
            startActivity(intent);


        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());//**MIn10:07***
            //*updateUI(null);
        }
    }

    private void loginUser(){
mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()){
            startActivity(new Intent(Login.this, SplashScreen.class));
            //finish();
        } else {
            Toast.makeText(Login.this, "No se ha podido iniciar sesiòn, Verifique sus datos", Toast.LENGTH_LONG).show();
        }

    }
});
    }




    //*******************VAlidaciòn de LA Primera EJecuciòn***********

}
