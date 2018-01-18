package com.pts3.sport;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pts3.sport.activity.StepperActivity;

public class info extends AppCompatActivity {

    TextView userInfo;
    AppCompatButton disconnect;
    CardView choisirClasse;
    Context context=this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);



        userInfo = (TextView) findViewById(R.id.userInfo);
        disconnect = (AppCompatButton) findViewById(R.id.disconnect);
        choisirClasse = (CardView) findViewById(R.id.choisirClasse);

        userInfo.setText(getIntent().getStringExtra("username"));


        choisirClasse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent displayActivity;
                displayActivity = new Intent(context,StepperActivity.class);
                context.startActivity(displayActivity);
            }
        });

        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent displayActivity = new Intent(context,MainActivity.class);


                context.startActivity(displayActivity);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }
}
