package davidhdezn.app.freniacare.InicialApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import davidhdezn.app.freniacare.R;

public class RegistroUsuario extends AppCompatActivity {

    private EditText mEditTextName;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButtonRegister;

    private  String name = "";
    private  String email = "";
    private  String password = "";

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    //private DatabaseReference mDatabase;// ...
    //mDatabase = FirebaseDatabase.getInstance().getReference();
    Window window;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        this.window = getWindow();
        String primaryDark = "#5c007a";
        String primary = "#8e24aa";
        window.setStatusBarColor(Color.parseColor(primaryDark));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(primary)));
        window.setNavigationBarColor(Color.parseColor(primary));


        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mEditTextName = (EditText) findViewById(R.id.editTextName);
        mEditTextEmail = (EditText) findViewById(R.id.editTextCorreo);
        mEditTextPassword = (EditText) findViewById(R.id.editTextPassword);
        mButtonRegister = (Button) findViewById(R.id.btnRegister);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = mEditTextName.getText().toString();
                email = mEditTextEmail.getText().toString();
                password = mEditTextPassword.getText().toString();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                    if (password.length() >= 6 ){


                    }else{
                        Toast.makeText(RegistroUsuario.this, "El password debe tener al menos 6 caracteres", Toast.LENGTH_LONG).show();
                    }

                    registerUser();

                } else{
                    Toast.makeText(RegistroUsuario.this, "Debe completar todos los campos", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    private void registerUser(){
mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
if (task.isSuccessful()){

    Map<String, Object> map = new HashMap<>();

    map.put("name",name);
    map.put("email", email);
    map.put("password", password);

    String id = mAuth.getCurrentUser().getUid();
    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task2) {
if (task2.isSuccessful()){
    startActivity(new Intent(RegistroUsuario.this, Login.class));
    finish();
}
else {
    Toast.makeText(RegistroUsuario.this, "No se pudieron crear los campos", Toast.LENGTH_LONG).show();

}

        }
    });




}else {
    Toast.makeText(RegistroUsuario.this, "No se pudo registrar este usuario", Toast.LENGTH_LONG).show();

}
    }
});
    }
}
