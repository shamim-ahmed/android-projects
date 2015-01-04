package android.cse.buet.edu.maptrial2;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapDisplayActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map_display);

    MapView mapView = (MapView) findViewById(R.id.geoMap);
    mapView.onCreate(savedInstanceState);
    mapView.onResume();
    GoogleMap aMap = mapView.getMap();

    if (aMap != null) {
      Intent intent = getIntent();
      String location = intent.getStringExtra("location");

      if (location != null && !location.trim().equals("")) {
        updateMap(aMap, location);
      }
    }
  }

  private void updateMap(GoogleMap map, String location) {
    Geocoder geoCoder = new Geocoder(this);

    try {
      List<Address> addressList = geoCoder.getFromLocationName(location, 1);

      if (addressList != null && addressList.size() > 0) {
        Address address = addressList.get(0);
        String description = String.format("%s, %s", location, address.getCountryName());
        map.addMarker(new MarkerOptions().position(new LatLng(address.getLatitude(), address.getLongitude())).title(description));
      }
    } catch (Exception ex) {
      ex.printStackTrace(System.err);
    }
  }
}
