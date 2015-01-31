package android.cse.buet.edu.buttondemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.view.View.OnClickListener;

public class MainActivity extends Activity {
  private static final String VIDEO_URL = "https://www.youtube.com/watch?v=yp0WqnuYNYY";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button button = (Button) findViewById(R.id.youtubeButton);
    Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Demo_ConeriaScript.ttf");
    button.setTypeface(typeFace, Typeface.BOLD_ITALIC);

    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Uri uri = Uri.parse(VIDEO_URL);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
      }
    });
  }
}
