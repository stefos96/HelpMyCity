package com.hackathon.thesingularityproject.helpmycity;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProblemLocation extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private String address;
    private double latitude;
    private double longitude;
    private LatLng SERRES = new LatLng(41.0855781, 23.5402434);
    private String name;
    private String prdescription;
    private String date;
    private LatLng reportedLocation;
    private SupportMapFragment mapFragment;
    private String pridClick;

    private String title;

    JSONParser jParser = new JSONParser();
    JSONArray problem = new JSONArray();
    String urlGetItem = "http://www.lekadramas.com/Hackathon/db_getproblem.php";

    private TextView titleTextView;
    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_location);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        Bundle extras = getIntent().getExtras();
        pridClick = extras.getString("prid");
        new LoadItem().execute();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.mapstyle_night));

            if (!success) {
                Log.e("MapsActivityRaw", "Style parsing failed.");
            }
        }
        catch (Resources.NotFoundException e) {
            Log.e("MapsActivityRaw", "Can't find style.", e);
        }

        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                // Convert location to real address
                convertLocationToAddress();
                return null;
            }
        };

//        convertLocationToAddress();

        // Add a marker to the location of the problem
        LatLng reportLocation = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(reportLocation).title(address));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(reportLocation, 15));
    }

    // Simple class for reverse geolocation
    private void convertLocationToAddress(){
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> locationList;

        try {
            locationList = geocoder.getFromLocation(latitude, longitude, 1);
            address = locationList.get(0).getAddressLine(0);
        }
        catch (Exception e) {
            address = "Address not found";
        }
    }

    class LoadItem extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... params) {
            // Building Parameters
            List<NameValuePair> args = new ArrayList<>();
            args.add(new BasicNameValuePair("prid", pridClick));
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(urlGetItem, "GET", args);
            try {
                problem = json.getJSONArray("problems");
                JSONObject c = problem.getJSONObject(0);
                name = c.getString("name") + " " + c.getString("lastname");
                prdescription = c.getString("prdescription");
                date = c.getString("report_date");
                title = c.getString("title");
                latitude = Double.parseDouble(c.getString("latitude"));
                longitude = Double.parseDouble((c.getString("longitude")));
            }
            catch (JSONException e) {}

            return null;
        }

        @Override
        protected void onPostExecute(String a){

            titleTextView = (TextView) findViewById(R.id.titleTextView);
            nameTextView = (TextView) findViewById(R.id.nameTextView);
            descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
            dateTextView = (TextView) findViewById(R.id.dateTextView);
            titleTextView.setText(title);
            nameTextView.setText(name);
            descriptionTextView.setText(prdescription);
            dateTextView.setText(date);
            mapFragment.getMapAsync(ProblemLocation.this);
        }
    }

}

