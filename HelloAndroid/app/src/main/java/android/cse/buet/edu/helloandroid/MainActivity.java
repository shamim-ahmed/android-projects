package android.cse.buet.edu.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * A very basic Android app
 *
 * @author shamimahmed
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("This is a basic android app");
        setContentView(textView);
    }
}
