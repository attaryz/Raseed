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

public class SudaniBalance extends AppCompatActivity
        implements View.OnClickListener {

    EditText mEdit;
    EditText mEdit1;
    EditText mEdit2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sudani_balance);


        Button button = findViewById(R.id.sudaniSend);


        button.setOnClickListener(this);
        mEdit = findViewById(R.id.field_password);
        mEdit1 = findViewById(R.id.field_Receiver);
        mEdit2 = findViewById(R.id.field_balance);


    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.sudani_send:
                String content = mEdit.getText().toString();
                String content1 = mEdit1.getText().toString();
                String content2 = mEdit2.getText().toString();
                String code = "*" + 303 + "*" + content2 + "*" + content1 + "*" + content + Uri.encode("#");
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED)
                    return;


                Intent sendIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + code));
                startActivity(sendIntent);

        }
    }

}




