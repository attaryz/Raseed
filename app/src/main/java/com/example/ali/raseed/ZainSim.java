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

public class ZainSim extends AppCompatActivity
        implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zain_sim);

        Button button = findViewById(R.id.ic_recharge);
        Button button1 = findViewById(R.id.ic_check);
        Button button2 = findViewById(R.id.ic_send);


        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ic_recharge:
                String code = "*" + 888 + Uri.encode("#");
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) return;
                Intent rechargeIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + code));
                startActivity(rechargeIntent);
                break;


            case R.id.ic_check:
                String code1 = "*" + 888 + Uri.encode("#");
                Intent checkIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + code1));
                startActivity(checkIntent);
                break;


            case R.id.ic_send:
                Intent sendIntent = new Intent(ZainSim.this, BalanceSender.class);
                startActivity(sendIntent);
                break;


        }
    }


}


