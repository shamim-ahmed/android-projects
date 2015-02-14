package android.cse.buet.edu.spinnertrial;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends Activity {

  private static final String[] MONTH_NAMES = {"January", "February", "March", "April", "May", "June",
      "July", "August", "September", "October", "November", "December" };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
    final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
        Arrays.asList(MONTH_NAMES));
    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner2.setAdapter(arrayAdapter);

    Button okButton = (Button) findViewById(R.id.okButton);

    okButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(MainActivity.this,
            String.format("country = %s and month = %s", spinner1.getSelectedItem(), spinner2.getSelectedItem()),
            Toast.LENGTH_LONG).show();
      }
    });
  }
}
