package com.hackathon.thesingularityproject.helpmycity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        Intent MainView;
        MainView = new Intent(this, ViewAllProblems.class);
        startActivity(MainView);
    }
}
