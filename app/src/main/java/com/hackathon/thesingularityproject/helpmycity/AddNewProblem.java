package com.hackathon.thesingularityproject.helpmycity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewProblem extends AppCompatActivity implements View.OnClickListener {

    Button button3;
    EditText name, lastname, title, prdescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_problem);

        button3 = (Button) findViewById(R.id.button3);
        name = (EditText)findViewById(R.id.editText);
        lastname = (EditText)findViewById(R.id.editText5);
        title = (EditText)findViewById(R.id.editText7);
        prdescription = (EditText)findViewById(R.id.editText6);

        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            Intent LocationView;
            LocationView = new Intent(this, AddProblemLocation.class);
            startActivity(LocationView);
    }

}
