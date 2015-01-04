package android.cse.buet.edu.maptrial2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MapInputActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map_input);
    final TextView location = (TextView) findViewById(R.id.locationText);
    final Button button = (Button) findViewById(R.id.goButton);

    button.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View v) {
        try {
          String locationValue = location.getText().toString();
          Intent geoIntent = new Intent(MapInputActivity.this, MapDisplayActivity.class);
          geoIntent.putExtra("location", locationValue);
          startActivity(geoIntent);
        } catch (Exception ex) {
          ex.printStackTrace(System.err);
        }
      }
    });
  }
}
