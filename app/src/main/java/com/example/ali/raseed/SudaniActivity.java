package com.example.ali.raseed;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SudaniActivity extends AppCompatActivity
        implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudani);


        Button btn = findViewById(R.id.sudaniCheck);
        Button btn1 = findViewById(R.id.sudaniRecharge);
        Button btn2 = findViewById(R.id.sudaniSend);


        btn.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sudaniCheck:
                String code = "*" + 222 + Uri.encode("#");
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) return;
                Intent rechargeIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + code));
                startActivity(rechargeIntent);
                break;


            case R.id.sudaniRecharge:
                String code1 = "*" + 222 + Uri.encode("#");
                Intent checkIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + code1));
                startActivity(checkIntent);
                break;


            case R.id.sudaniSend:
                Intent sendIntent = new Intent(SudaniActivity.this, BalanceSender.class);
                startActivity(sendIntent);
                break;


        }
    }


}











