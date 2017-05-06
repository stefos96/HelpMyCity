package com.hackathon.thesingularityproject.helpmycity;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewAllProblems extends ListActivity implements View.OnClickListener{

    private ProgressDialog pDialog;
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> problemsList;
    // url to get all problems list
    private static String url = "http://trojanzaro.hopto.org/phpfiles/getallproblems.php";
    // problems JSONArray
    JSONArray problems = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_problems);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        // Hashmap for ListView
        problemsList = new ArrayList<HashMap<String, String>>();

        // Loading problems in Background Thread
        new LoadAllProducts().execute();

        // Get listview
        ListView lv = getListView();

        // on seleting single problem
        // launching Edit Problem Screen
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String pid = ((TextView) view.findViewById(R.id.prid)).getText()
                        .toString();

                // Starting new intent
                //Intent in = new Intent(getApplicationContext(), EditProductActivity.class);
                // sending pid to next activity
                //in.putExtra("prid", pid);

                // starting new activity and expecting some response back
                //startActivityForResult(in, 100);
            }
        });

    }

    // Response from Edit Product Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if result code 100
        if (resultCode == 100) {
            // if result code 100 is received
            // means user edited/deleted problem
            // reload this screen again
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

    }

        @Override
        public void onClick(View v) {
            Intent AddProblem;
            AddProblem = new Intent(this, AddNewProblem.class);
            startActivity(AddProblem);
        }


    class LoadAllProducts extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ViewAllProblems.this);
            pDialog.setMessage("Loading Database . . .");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());

            try {
                    problems = json.getJSONArray("problems");

                    // looping through All Problems
                    for (int i = 0; i < problems.length(); i++) {
                        JSONObject c = problems.getJSONObject(i);

                        // Storing each json item in variable
                        String id = c.getString("prid");
                        String name = c.getString("name");
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
                        // adding each child node to HashMap key => value
                        map.put("prid", id);
                        map.put("name", name);

                        // adding HashList to ArrayList
                        problemsList.add(map);
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
            // dismiss the dialog after getting all problems
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(ViewAllProblems.this, problemsList, R.layout.list_item, new String[] { "prid", "name"}, new int[] { R.id.prid, R.id.prdescription});
                    // updating listview
                    setListAdapter(adapter);
                }
            });

        }

    }
}
