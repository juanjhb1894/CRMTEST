package com.example.crmtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.crmtest.utils.StoredData;
import com.example.crmtest.webservice.Auth;

import org.json.JSONException;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    Button btnLoggin;
    EditText etEmail, etPassword;
    Auth.UserAccount account;
    StoredData storedData;
    RadioButton rbRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Remove action bar
        getSupportActionBar().hide();
        storedData = new StoredData(getApplicationContext());

        btnLoggin = (Button) findViewById(R.id.btnLogin);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etpassword);
        rbRemember = (RadioButton) findViewById(R.id.rbRemember);

        if(storedData.getBool("remember"))
        {
            etEmail.setText(storedData.getStr("mail"));
            etPassword.setText(storedData.getStr("pass"));
        }

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        btnLoggin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(rbRemember.isChecked())
                {
                    storedData.setBool("remember", true);
                    storedData.setStr("mail", etEmail.getText().toString());
                    storedData.setStr("pass", etPassword.getText().toString());
                }

                try {
                    account = new Auth().Login(etEmail.getText().toString(), etPassword.getText().toString());
                    if(account.name !=  null)
                    {
                        Toast.makeText(getApplicationContext(), account.name + ", Welcome back!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Access denied", Toast.LENGTH_LONG).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}