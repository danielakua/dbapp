package com.example.danielakua.dbapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onResume(){
        super.onResume();
        if (LoginPage.sharedPref.contains("username")){
            GoToLogin();
        }
    }

    // click to register
    public void RegisterClick(View view) {
        final TextView errorRegister = findViewById(R.id.errorRegister);
        EditText usernameRegister = findViewById(R.id.usernameRegister);
        EditText passwordRegister = findViewById(R.id.passwordRegister);
        EditText confirmRegister = findViewById(R.id.confirmRegister);

        errorRegister.setText("");
        String username = usernameRegister.getText().toString();
        String password = passwordRegister.getText().toString();
        String confirm = confirmRegister.getText().toString();

        if (username.isEmpty()) {
            errorRegister.setText("Enter username");
            return;
        }
        else if (username.equals(LoginPage.sharedPref.getString("adminuser",""))) {
            errorRegister.setText("Illegal username");
            return;
        }
        else if (!username.matches("^[a-z]*$")) {
            errorRegister.setText("username must contain only lowercase letters");
            return;
        }
        else if (password.isEmpty()) {
            errorRegister.setText("Enter password");
            return;
        }
        else if (!username.matches("^[a-z1-9]*$")) {
            errorRegister.setText("password must contain only lowercase letters and numbers");
            return;
        }
        else if (confirm.isEmpty()) {
            errorRegister.setText("Confirm password");
            return;
        }
        else if (!password.equals(confirm)) {
            errorRegister.setText("Passwords doesn't match");
            return;
        }

        PerformQuery query = new PerformQuery(this, "apply", new PerformQuery.AsyncResponse(){
            @Override
            public void processFinish(String response)
            {
                response = response.trim();
                errorRegister.setText(response);
            }
        });
        query.execute(username, password);
    }

    public void ChangeDBClick(View view) {
        LoginPage.sharedPref.edit().clear().apply();
        Intent intent = new Intent(this, DBMainActivity.class);
        startActivity(intent);
        finish();
    }

    public void LoginClick(View view) {
        GoToLogin();
    }

    private void GoToLogin() {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
        finish();
    }
}
