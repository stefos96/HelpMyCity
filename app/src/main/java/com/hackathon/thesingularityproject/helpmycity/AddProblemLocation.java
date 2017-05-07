package com.hackathon.thesingularityproject.helpmycity;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class AddProblemLocation extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, View.OnClickListener {

    String name;
    String lastname;
    String title;
    String prdescription;
    String date;

    private GoogleMap mMap;
    Button button1;
    String url3 = "http://lekadramas.com/hackathon/new_problem.php";
    JSONParser jParser = new JSONParser();
    // Location
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_problem_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        mMap.setOnMapClickListener(this);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        // Add a marker in Serres and move the camera
        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        lastname = extras.getString("lastname");
        title = extras.getString("title");
        date = extras.getString("date");
        prdescription = extras.getString("prdescription");

        LatLng serres = new LatLng(41.0845196, 23.5443302);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(serres));

    }

    // Set location of your problem on the map
    @Override
    public void onMapClick(LatLng latLng) {
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(latLng));
        latitude = latLng.latitude;
        longitude = latLng.longitude;
    }

    // Submit problem
    @Override
    public void onClick(View v) {
        new AddProblem().execute();
    }

    private class AddProblem extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {
            JSONObject json = new JSONObject();
            List<NameValuePair> args = new ArrayList<>();
            args.add(new BasicNameValuePair("name", name));
            args.add(new BasicNameValuePair("lastname", lastname));
            args.add(new BasicNameValuePair("title", title));
            args.add(new BasicNameValuePair("prdescription", prdescription));
            args.add(new BasicNameValuePair("date", date));
            args.add(new BasicNameValuePair("latitude", Double.toString(latitude)));
            args.add(new BasicNameValuePair("longitude", Double.toString(longitude)));

            json = jParser.makeHttpRequest(url3, "GET", args);
            finish();
            return null;
        }
    }

}
