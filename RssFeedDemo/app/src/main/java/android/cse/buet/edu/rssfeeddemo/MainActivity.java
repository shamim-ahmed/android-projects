package android.cse.buet.edu.rssfeeddemo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends Activity {
  private static final String RSS_URL = "http://www.theguardian.com/world/bangladesh/rss";
  private static final int CONNECTION_TIMEOUT_PERIOD = 10000;
  private static final int READ_TIMEOUT_PERIOD = 15000;

  private TextView textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    textView = (TextView) findViewById(R.id.rssText);
    textView.setMovementMethod(new ScrollingMovementMethod());
    Button fetchButton = (Button) findViewById(R.id.fetchButton);

    fetchButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new FetchRssTask().execute(RSS_URL);
      }
    });
  }

  private class FetchRssTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
      String result = "";

      try {
        result = fetchRss(params[0]);
      } catch (Exception ex) {
        result = ex.toString();
      }

      return result;
    }

    @Override
    protected void onPostExecute(String s) {
      super.onPostExecute(s);
      textView.setText(s);
    }

    private String fetchRss(String str) throws IOException {
      URL rssUrl = new URL(str);
      HttpURLConnection connection = (HttpURLConnection) rssUrl.openConnection();
      connection.setConnectTimeout(CONNECTION_TIMEOUT_PERIOD);
      connection.setReadTimeout(READ_TIMEOUT_PERIOD);
      connection.setRequestMethod("GET");
      connection.setDoInput(true);

      connection.connect();
      InputStream inputStream = connection.getInputStream();
      Scanner scanner = new Scanner(inputStream);
      StringBuilder resultBuilder = new StringBuilder();

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        resultBuilder.append(line).append(System.getProperty("line.separator"));
      }

      scanner.close();
      return resultBuilder.toString();
    }
  }
}
