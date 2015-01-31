package android.cse.buet.edu.edittextdemo;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final EditText editText = (EditText) findViewById(R.id.editText);
    Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Demo_ConeriaScript.ttf");
    editText.setTypeface(typeFace, Typeface.BOLD);

    editText.setOnKeyListener(new View.OnKeyListener() {
      @Override
      public boolean onKey(View v, int keyCode, KeyEvent event) {
        int action = event.getAction();

        if (action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
          Toast.makeText(MainActivity.this, editText.getText(), Toast.LENGTH_LONG).show();
          return true;
        } else if (action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_9) {
          Toast.makeText(MainActivity.this, "You pressed 9 ! YOu naughty boy !!", Toast.LENGTH_LONG).show();
          return true;
        }
        return false;
      }
    });
  }
}
