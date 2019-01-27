package com.example.ali.raseed;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

public class MtnActivity extends Activity
        implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtn);

        Button button = findViewById(R.id.mtnRecharge);
        Button button1 = findViewById(R.id.mtnCheck);
        Button button2 = findViewById(R.id.mtnSend);


        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mtnCheck:
                String code = "*" + 888 + Uri.encode("#");
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) return;
                Intent rechargeIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + code));
                startActivity(rechargeIntent);
                break;


            case R.id.mtnRecharge:
                String code1 = "*" + 888 + Uri.encode("#");
                Intent checkIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + code1));
                startActivity(checkIntent);
                break;


            case R.id.mtnSend:
                Intent sendIntent = new Intent(MtnActivity.this, BalanceSender.class);
                startActivity(sendIntent);
                break;


        }
    }


}


