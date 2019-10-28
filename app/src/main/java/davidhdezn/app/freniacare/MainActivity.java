package davidhdezn.app.freniacare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
Button btn;
TextView btnTxt;

    SignInButton signin;
    int RC_SIGN_IN = 0;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signin = findViewById(R.id.sign_in_button);

        btn = (Button) findViewById(R.id.button2);
        btnTxt = (TextView) findViewById(R.id.txtRegistrarse);






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
                startActivity(new Intent( MainActivity.this, RegistroUsuario.class));
                finish();
                }

            }
        );

    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build();

    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
}
/**
 *
 * Redirigir a segunda Activity
 *
    @Override
    protected void onStart() {
        super.onStart();

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

    }**/

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
            Intent intent = new Intent(MainActivity.this, CargandoApp.class);
            startActivity(intent);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());//**MIn10:07***
            //*updateUI(null);
        }
    }
}
