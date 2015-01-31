package android.cse.buet.edu.checkboxdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final CheckBox iosCheckBox = (CheckBox) findViewById(R.id.iosCheckBox);
    final CheckBox androidCheckBox = (CheckBox) findViewById(R.id.androidCheckBox);
    final CheckBox windowsCheckBox = (CheckBox) findViewById(R.id.windowsCheckBox);
    final Button doneButton = (Button) findViewById(R.id.doneButton);

    iosCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
          Toast.makeText(MainActivity.this, "Try Android, Bro !", Toast.LENGTH_LONG).show();
        }
      }
    });

    doneButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String result = String.format("iOS selected ? %b%n", iosCheckBox.isChecked())
            .concat(String.format("Android selected ? %b%n", androidCheckBox.isChecked()))
            .concat(String.format("Windows selected ? %b%n", windowsCheckBox.isChecked()));
        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
      }
    });
  }
}
