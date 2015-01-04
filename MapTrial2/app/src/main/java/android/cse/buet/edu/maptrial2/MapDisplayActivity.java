package android.cse.buet.edu.maptrial2;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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
      aMap.addMarker(new MarkerOptions().position(new LatLng(23, 90)).title("Dhaka, My Love !"));
    }
  }
}
