package com.example.a01_19478061_huynhtuananh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    EditText edtPassword, edtEmail;
    Button btnLogin, btnToRegisterPage;
    private Context context;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUi();

        btnToRegisterPage.setOnClickListener((v) -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                boolean result = checkData(email, password);
                if (result) {
                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(context, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LoginActivity.this, ListSachMainActivity.class);
                                        intent.putExtra("uid", auth.getCurrentUser().getUid());
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(context, "Tài khoản/ mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

    }

    private void initUi() {
        context=this;
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        btnToRegisterPage = findViewById(R.id.btn_to_register_page);
        edtEmail = findViewById(R.id.login_edtEmailInput);
        edtPassword = findViewById(R.id.login_edtPassInput);
        btnLogin = findViewById(R.id.btnLogin);
    }

    public boolean checkData(String email, String password) {
        if (email.isEmpty() || email.length() <= 0) {
            Toast.makeText(context, "Email không được để trống", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.isEmpty() || password.length() <= 0) {
            Toast.makeText(context, "Mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}