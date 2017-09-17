package com.hackathon.thesingularityproject.helpmycity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewAllProblems extends ListActivity implements View.OnClickListener{
    private ProgressDialog pDialog1;
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> problemsList = new ArrayList<>();
    // URL to get all problems list
    private static String URL = "http://lekadramas.com/hackathon/db_getallproblems.php";
    // problems JSONArray
    JSONArray problems = null;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_problems);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        // Loading problems in Background Thread
        new LoadAllProblems().execute();

        // Get listview
        ListView lv = getListView();

        // seleting single problem
        // launching Edit Problem Screen
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // getting values from selected ListItem
                String prid = ((TextView)view.findViewById(R.id.prid)).getText().toString();
                // Starting new intent
                Intent in = new Intent(getApplicationContext(), ProblemLocation.class);
                // sending pid to next activity
                in.putExtra("prid", prid);

                // starting new activity and expecting some response back
                startActivityForResult(in, 100);
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        new LoadAllProblems().execute();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );

    }
        @Override
        public void onClick(View v) {
            Intent AddProblem;
            AddProblem = new Intent(this, AddNewProblem.class);
            startActivity(AddProblem);
        }


    class LoadAllProblems extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog1 = new ProgressDialog(ViewAllProblems.this);
            pDialog1.setMessage("Loading Database . . .");
            pDialog1.setIndeterminate(false);
            pDialog1.setCancelable(false);
            pDialog1.show();
        }
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(URL, "GET", params);
            try {
                    problems = json.getJSONArray("problems");

                    // looping through All Problems
                    for (int i = 0; i < problems.length(); i++) {
                        JSONObject c = problems.getJSONObject(i);

                        // Storing each json item in variable
                        String title = " " + c.getString("title");
                        String prid = c.getString("prid");
                        // creating new Hash
                        HashMap<String, String> map = new HashMap<String, String>();
                        // adding each child node to HashMap key => value
                        map.put("title", title);
                        map.put("prid", prid);
                        // adding HashList to ArrayList
                        problemsList.add(map);
                    }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all problems
            pDialog1.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    ListAdapter adapter = new SimpleAdapter(ViewAllProblems.this, problemsList, R.layout.list_item, new String[] { "title", "prid"}, new int[] { R.id.title, R.id.prid});
                    // updating listview
                    setListAdapter(adapter);
                }
            });

        }

    }
}
