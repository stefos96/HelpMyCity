package com.hackathon.thesingularityproject.helpmycity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;

import static android.R.id.button3;

public class AddNewProblem extends AppCompatActivity implements View.OnClickListener {

    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_problem);
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == button3) {
            Intent LocationView;
            LocationView = new Intent(this, AddProblemLocation.class);
            startActivity(LocationView);
        }
    }
}
