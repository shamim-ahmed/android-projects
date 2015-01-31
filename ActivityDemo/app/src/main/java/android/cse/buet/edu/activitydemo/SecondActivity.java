package android.cse.buet.edu.activitydemo;

import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;


public class SecondActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);

    TextView textView = (TextView) findViewById(R.id.secondActivityTextView);
    textView.setText(R.string.second_activity_text);

    Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Demo_ConeriaScript.ttf");
    textView.setTypeface(typeFace, Typeface.BOLD_ITALIC);
  }
}
