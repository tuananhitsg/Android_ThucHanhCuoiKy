package com.example.a01_19478061_huynhtuananh;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a01_19478061_huynhtuananh.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    Button btnToLoginPage,btnRegister;
    EditText  edtRegisterEmail, edtPassword, edtRePassword, edtLoginEmail;
    private Context context;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initUi();

        btnToLoginPage.setOnClickListener((v) -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = onClickRegister();
            }
        });


    }
    private void initUi() {
        context = this;

        mAuth = FirebaseAuth.getInstance();

        edtRegisterEmail = findViewById(R.id.regis_edtEmailInput);
        edtLoginEmail = findViewById(R.id.login_edtEmailInput);
        edtPassword = findViewById(R.id.regis_edtPassInput);
        edtRePassword = findViewById(R.id.regis_edtConfirmPassInput);

        btnToLoginPage = findViewById(R.id.btn_to_login_page);
        btnRegister = findViewById(R.id.btn_register);
    }
    private boolean onClickRegister() {

        String email = edtRegisterEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String rePassword = edtRePassword.getText().toString().trim();

        if (email.isEmpty() || email.length() <= 0) {
            Toast.makeText(context, "Email không được để trống", Toast.LENGTH_SHORT).show();
            edtRegisterEmail.requestFocus();
            return false;
        }
        if (password.isEmpty() || password.length() <= 0) {
            Toast.makeText(context, "Mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
            edtPassword.requestFocus();
            return false;
        }
        if (password.length() <= 6) {
            Toast.makeText(context, "Mật khẩu phải lớn hơn 6 kí tự", Toast.LENGTH_SHORT).show();
            edtPassword.requestFocus();
            return false;
        }
        if (rePassword.isEmpty() || rePassword.length() <= 0) {
            Toast.makeText(context, "Mật khẩu nhập lại không được để trống", Toast.LENGTH_SHORT).show();
            edtRePassword.requestFocus();
            return false;
        }
        if (!password.equals(rePassword)) {
            Toast.makeText(context, "Nhập lại mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            edtRePassword.requestFocus();
            return false;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            String uid = mAuth.getCurrentUser().getUid();
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("user");
                            User user = new User(uid, email);
                            myRef.child(uid).setValue(user);

                            Toast.makeText(context, "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);


                            edtLoginEmail.setText(email);
                        } else {
                            Toast.makeText(context, "Đăng ký tài khoản thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return true;
    }
}