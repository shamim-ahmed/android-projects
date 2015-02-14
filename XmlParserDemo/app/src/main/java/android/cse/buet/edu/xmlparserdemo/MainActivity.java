package android.cse.buet.edu.xmlparserdemo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends Activity {

  private TextView xmlContent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    xmlContent = (TextView) findViewById(R.id.xmlContent);
    xmlContent.setMovementMethod(new ScrollingMovementMethod());
    Button parseButton = (Button) findViewById(R.id.parseButton);

    parseButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new ParseTask().execute(R.raw.rssfeed);
      }
    });
  }

  private class ParseTask extends AsyncTask<Integer, Void, String> {

    @Override
    protected String doInBackground(Integer... params) {
      if (params.length == 0) {
        throw new IllegalArgumentException("No resource id provided");
      }

      StringBuilder result = new StringBuilder();

      try {
        InputStream in = getResources().openRawResource(params[0]);
        Scanner scanner = new Scanner(in);

        while (scanner.hasNextLine()) {
          String line = scanner.nextLine();
          result.append(line).append(System.getProperty("line.separator"));
        }

        scanner.close();
      } catch (Exception ex) {
        result.append(ex.toString());
      }

      return result.toString();
    }

    @Override
    protected void onPostExecute(String str) {
      xmlContent.setText(str);
    }
  }
}
