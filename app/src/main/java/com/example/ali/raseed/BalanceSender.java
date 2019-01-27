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
import android.widget.EditText;


public class BalanceSender extends AppCompatActivity
        implements View.OnClickListener {

    EditText mEdit;
    EditText mEdit1;
    EditText mEdit2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.balance_sender);

        Button button = findViewById(R.id.send);


        button.setOnClickListener(this);
        mEdit = findViewById(R.id.password);
        mEdit1 = findViewById(R.id.balanceReceiver);
        mEdit2 = findViewById(R.id.balance);


    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.send:
                String content = mEdit.getText().toString();
                String content1 = mEdit1.getText().toString();
                String content2 = mEdit2.getText().toString();


                String code = "*" + 200 + "*" + content + "*" + content2 + "*" + content1 + "*" + content1 + Uri.encode("#");


                Intent sendIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + code));

                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED)
                    return;
                startActivity(sendIntent);

        }
    }

}























