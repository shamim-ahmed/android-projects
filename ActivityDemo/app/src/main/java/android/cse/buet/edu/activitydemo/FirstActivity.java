package android.cse.buet.edu.activitydemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first);

    TextView textView = (TextView) findViewById(R.id.firstActivityTextView);
    textView.setText(R.string.first_activity_text);

    Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Demo_ConeriaScript.ttf");
    textView.setTypeface(typeFace, Typeface.BOLD_ITALIC);

    Button button = (Button) findViewById(R.id.firstActivityButton);
    button.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
            startActivity(intent);
          }
        }
    );
  }
}
