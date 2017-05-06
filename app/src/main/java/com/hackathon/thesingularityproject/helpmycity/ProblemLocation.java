package com.hackathon.thesingularityproject.helpmycity;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProblemLocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String address;
    private double latitude;
    private double longitude;

    String pridClick;
    String name;
    String prdescription;
    String date;

    JSONParser jParser;
    JSONArray problem;
    String urlGetItem = "http://www.lekadramas.com/Hackathon/db_getproblem.php";

    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_location);


        Bundle extras = getIntent().getExtras();
        pridClick = extras.getString("prid");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        nameTextView = (TextView) findViewById(R.id.nameTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        dateTextView = (TextView) findViewById(R.id.dateTextView);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Building Parameters
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("prid", pridClick));
        // getting JSON string from URL
        JSONObject json = jParser.makeHttpRequest(urlGetItem, "GET", params);
        try {
            problem = json.getJSONArray("problems");
            JSONObject c = problem.getJSONObject(0);
            name = c.getString("name") + " " + c.getString("lastname");
            prdescription = c.getString("prdescription");
            date = c.getString("report_date");
            latitude = Double.parseDouble(c.getString("latitude"));
            longitude = Double.parseDouble((c.getString("longitude")));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        catch (Exception e){}
        mMap = googleMap;
        new LoadProblem().execute();
    }



    private void convertLocationToAddress(){
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> locationList;

        try {
            locationList = geocoder.getFromLocation(latitude, longitude, 1);
            address = locationList.get(0).getAddressLine(0);
        }
        catch (IOException e) {
            address = "Address not found";
        }
    }

    class LoadProblem extends AsyncTask<String, String, String> {
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {
            return null;
        }

        /*
         * After completing background task Dismiss the progress dialog
         */
        protected void onPostExecute(String file_url) {
                    convertLocationToAddress();
                    // Add a marker to the location of the problem
                    LatLng problemLocation = new LatLng(latitude, longitude);
                    mMap.addMarker(new MarkerOptions().position(problemLocation).title(address));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(problemLocation, 13));
                    nameTextView.setText(name);
                    descriptionTextView.setText(prdescription);
                    dateTextView.setText(date);
        }

    }

}
