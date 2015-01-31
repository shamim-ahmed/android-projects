package android.cse.buet.edu.passworddemo;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    TextView passwordLabel = (TextView) findViewById(R.id.passwordLabel);
    final EditText passwordField = (EditText) findViewById(R.id.passwordField);
    Button passwordButton = (Button) findViewById(R.id.passwordButton);

    Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Demo_ConeriaScript.ttf");
    passwordLabel.setTypeface(typeFace, Typeface.BOLD);
    passwordButton.setTypeface(typeFace, Typeface.BOLD);

    passwordButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(MainActivity.this,
            String.format("Your password is : %s", passwordField.getText()),
            Toast.LENGTH_LONG).show();
      }
    });
  }
}
