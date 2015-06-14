package com.teamveryniceah.ping;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dd.processbutton.iml.ActionProcessButton;


public class LoginActivity extends ActionBarActivity {
    private ActionProcessButton btnLogin;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //getWindow().setStatusBarColor(getResources().getColor(R.color.login_blue));
        btnLogin=(ActionProcessButton)findViewById(R.id.btnLogin);
        btnLogin.setMode(ActionProcessButton.Mode.ENDLESS);
        TextView myTextView = (TextView) findViewById(R.id.appLabel);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "font/levenim.ttf");

        myTextView.setTypeface(myTypeface);
        btnLogin.setTypeface(myTypeface);

    }
    public void login(View view){
        btnLogin.setProgress(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                LoginActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btnLogin.setProgress(100);
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }).start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
