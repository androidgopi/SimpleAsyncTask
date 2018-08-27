package com.sreeyainfotech.sampleasynchronous;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivitty extends AppCompatActivity {

    private EditText email,password;
    LoginAsync LoginAsync_async;
    private String Login_Url;
    private Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activitty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_cancel);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
Login_Url="http://iphone.us2guntur.com/AndroidAppLoginService";

        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAction();
            }
        });

    }
    public void loginAction() {

        final JSONObject json = new JSONObject();
        try {
            json.put("loginid", email.getText().toString());
            json.put("password", password.getText().toString());
            json.put("appname", "us2gandroidapp");
            json.put("app_devicetoken", "");

            LoginAsync_async = new LoginAsync(LoginActivitty.this, Login_Url, json);
            LoginAsync_async.execute();
            LoginAsync_async.delegate = new LoginAsyncResponse() {
                @Override
                public void loginAsyncResponse(JSONObject result) {
                    try {
                        if(result!=null) {
                            if (result.has("friendsdetails") && result.has("loginstatus") && result.getString("loginstatus").equals("success")) {
                                Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
                                }
                        }else {
                            Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



}
