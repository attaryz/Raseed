package com.example.ali.raseed;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions


public class MainActivity extends AppCompatActivity {


    private static final Object zainSDN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.permission);
        Button btnZain = findViewById(R.id.zain);
        Button btnSudani = findViewById(R.id.sudani);
        Button btnMtn = findViewById(R.id.mtn);
        {
            TelephonyManager tm = (TelephonyManager)
                    getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            String simId = tm.getSimOperatorName();
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED) return;
            TextView textView = findViewById(R.id.text);
            textView.setText(simId);


        }


        btnZain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent zainIntent = new Intent(MainActivity.this, ZainSim.class);
                startActivity(zainIntent);

            }
        });
        btnSudani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sudaniIntent = new Intent(MainActivity.this, SudaniActivity.class);
                startActivity(sudaniIntent);

            }
        });
        btnMtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mtnIntent = new Intent(MainActivity.this, MtnActivity.class);
                startActivity(mtnIntent);

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivityPermissionsDispatcher.callPhoneWithPermissionCheck(MainActivity.this);


            }
        });
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            btn.setVisibility(View.VISIBLE);
        } else {
            btn.setVisibility(View.GONE);


        }
    }


    @NeedsPermission({Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE, Manifest.permission.READ_PHONE_STATE})
    void callPhone() {
        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE, Manifest.permission.READ_PHONE_STATE})
    void callPhoneRational(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setTitle("Permissions needed")
                .setMessage("Thia App needs Permissions For Using phone And Accessing Contacts")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.proceed();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.cancel();
                    }
                })
                .show();
    }

    @OnPermissionDenied({Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE, Manifest.permission.READ_PHONE_STATE})
    void phoneDenied() {
        Toast.makeText(this, "Permissions Denied", Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain({Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE, Manifest.permission.READ_PHONE_STATE})
    void onNeverAsk() {
        Toast.makeText(this, "Never Ask Again", Toast.LENGTH_SHORT).show();
    }


}
