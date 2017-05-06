package com.hackathon.thesingularityproject.helpmycity;

import android.app.ProgressDialog;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddNewProblem extends AppCompatActivity implements View.OnClickListener {

    Button button3;
    EditText name, lastname, title, prdescription;
    String name1, lastname1, title1, prdescription1;
    String date1;
    JSONParser jsonParser;
    String url3 = "http://lekadramas.com/hackathon/db_addNewProblem.php";

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
            String date1 = sdf.format(new Date());

            Intent LocationView;
            LocationView = new Intent(this, AddProblemLocation.class);
            startActivity(LocationView);
        }
    }
    class CreateNewProblem extends AsyncTask<String, String, String> {

        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AddNewProblem.this);
            pDialog.setMessage("Creating Product..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        protected String doInBackground(String... args) {


            // Building Parameters

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", name1));
            params.add(new BasicNameValuePair("lastname", lastname1));
            params.add(new BasicNameValuePair("prdescription", prdescription1));
            params.add(new BasicNameValuePair("title", title1));
            params.add(new BasicNameValuePair("report_date", date1));


            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url3, "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            try {
                int success = json.getInt("success");

                if (success == 1) {
                    // successfully created product
                    Intent i = new Intent(getApplicationContext(), AddNewProblem.class);
                    startActivity(i);

                    // closing this screen
                    finish();
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(AddNewProblem.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("There was a problem with the database . . .");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }

}
