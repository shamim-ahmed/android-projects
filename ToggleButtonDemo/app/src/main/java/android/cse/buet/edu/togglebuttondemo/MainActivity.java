package android.cse.buet.edu.togglebuttondemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final ToggleButton activeToggleButton = (ToggleButton) findViewById(R.id.activeToggleButton);
    final ToggleButton premiumToggleButton = (ToggleButton) findViewById(R.id.premiumToggleButton);
    Button doneButton = (Button) findViewById(R.id.doneButton);

    doneButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String result = String.format("Active : %s%n", activeToggleButton.getText())
            .concat(String.format("Premium : %s%n", premiumToggleButton.getText()));
        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
      }
    });
  }
}
