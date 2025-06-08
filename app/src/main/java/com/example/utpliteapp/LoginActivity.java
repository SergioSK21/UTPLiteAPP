package com.example.utpliteapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etCodigo, etPassword;
    private Button btnLogin;
    private TextView tvResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etCodigo = findViewById(R.id.etCodigo);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvResetPassword = findViewById(R.id.tvResetPassword);

        btnLogin.setOnClickListener(view -> {
            String codigo = etCodigo.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (codigo.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                if (codigo.equals("1") && password.equals("1")) {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvResetPassword.setOnClickListener(view ->
                Toast.makeText(this, "Función de restablecimiento en construcción", Toast.LENGTH_SHORT).show()
        );
    }
}
