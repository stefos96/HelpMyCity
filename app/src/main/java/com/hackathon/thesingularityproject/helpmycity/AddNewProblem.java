package com.hackathon.thesingularityproject.helpmycity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNewProblem extends AppCompatActivity implements View.OnClickListener {

    Button button3;
    EditText name, lastname, title, prdescription;
    String name1, lastname1, title1, prdescription1;
    String date1;


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
        if (name.getText().toString().trim().length() == 0 ||
                lastname.getText().toString().trim().length() == 0 ||
                title.getText().toString().trim().length() == 0 ||
                prdescription.getText().toString().trim().length() == 0)
        {
            AlertDialog alertDialog = new AlertDialog.Builder(AddNewProblem.this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("All fields must be entered!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        else
        {
            name1 = name.getText().toString();
            lastname1 = lastname.getText().toString();
            prdescription1 = prdescription.getText().toString();
            title1 = title.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date1 = sdf.format(new Date());

            Intent LocationView;
            LocationView = new Intent(this, AddProblemLocation.class);
            LocationView.putExtra("name", name1);
            LocationView.putExtra("lastname", lastname1);
            LocationView.putExtra("prdescription", prdescription1);
            LocationView.putExtra("title", title1);
            LocationView.putExtra("date", date1);
            startActivity(LocationView);
        }
    }


}
