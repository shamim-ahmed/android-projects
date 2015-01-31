package android.cse.buet.edu.radiobuttondemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.sexRadioGroup);
    Button doneButton = (Button) findViewById(R.id.doneButton);

    doneButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(radioButtonId);
        Toast.makeText(MainActivity.this, String.format("You selected : %s", radioButton.getText()), Toast.LENGTH_LONG).show();
      }
    });
  }
}
