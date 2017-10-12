package com.example.nikhil.studentregistrationsystem.loginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nikhil.studentregistrationsystem.R;
import com.example.nikhil.studentregistrationsystem.mainActivity_MVP.MainActivity;
import com.pixplicity.easyprefs.library.Prefs;

/**
 * Created by Nikhil on 10/10/17.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText id = (EditText) findViewById(R.id.userId);
        final EditText pwd = (EditText) findViewById(R.id.password);
        final TextView error = (TextView) findViewById(R.id.errorText);
        Button btn = (Button) findViewById(R.id.loginBtn);

//        Fixed UserID and password given to system admin
        final String idFix = "admin123";
        final String pwdFix = "admin123";
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (id.getText().toString().trim().equals(idFix)
                        && pwd.getText().toString().trim().equals(pwdFix)) {
                    Prefs.putBoolean("login", true);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    error.setText("Wrong Credentials");
                }
            }
        });
    }
}
