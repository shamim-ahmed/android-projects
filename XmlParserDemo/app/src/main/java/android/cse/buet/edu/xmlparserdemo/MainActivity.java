package android.cse.buet.edu.xmlparserdemo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
  private static final String LINE_SEPARATOR = System.getProperty("line.separator");
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
        XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = parserFactory.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(in, "UTF-8");
        parser.nextTag();  // move to <rss>
        parser.nextTag();  // move to <channel>
        List<Item> itemList = getItems(parser);

        for (Item item : itemList) {
          result.append(item.getTitle()).append(LINE_SEPARATOR);
          result.append(item.getDescription()).append(LINE_SEPARATOR);
          result.append(item.getLink()).append(LINE_SEPARATOR);
          result.append(LINE_SEPARATOR);
        }

      } catch (Exception ex) {
        // very innovative way to figure out what went wrong
        Toast.makeText(MainActivity.this, String.format("An exception occurred : %s", ex.toString()), Toast.LENGTH_LONG).show();
      }

      return result.toString();
    }

    @Override
    protected void onPostExecute(String str) {
      xmlContent.setText(str);
    }

    private List<Item> getItems(XmlPullParser parser) {
      List<Item> itemList = new ArrayList<>();

      try {
        parser.require(XmlPullParser.START_TAG, null, "channel");

        while (parser.next() != XmlPullParser.END_TAG) {
          if (parser.getEventType() != XmlPullParser.START_TAG) {
            continue;
          }

          if (parser.getName().equals("item")) {
            Item item = parseItem(parser);
            itemList.add(item);
          } else {
            skipCurrentElement(parser);
          }
        }
      } catch (Exception ex) {
        Toast.makeText(MainActivity.this, String.format("An exception occurred : %s", ex.toString()), Toast.LENGTH_LONG).show();
      }

      return itemList;
    }

    private Item parseItem(XmlPullParser parser) throws XmlPullParserException, IOException {
      if (parser.getEventType() != XmlPullParser.START_TAG) {
        throw new IllegalStateException();
      }

      Item item = new Item();

      while (parser.next() != XmlPullParser.END_TAG) {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
          continue;
        }

        String name = parser.getName();

        switch (name) {
          case "title":
            item.setTitle(parser.nextText());
            break;
          case "link":
            item.setLink(parser.nextText());
            break;
          case "description":
            item.setDescription(parser.nextText());
            break;
          default:
            skipCurrentElement(parser);
            break;
        }
      }

      return item;
    }

    private void skipCurrentElement(XmlPullParser parser) throws XmlPullParserException, IOException {
      if (parser.getEventType() != XmlPullParser.START_TAG) {
        throw new IllegalStateException();
      }

      int depth = 1;

      while (depth != 0) {
        int n = parser.next();

        switch (n) {
          case XmlPullParser.END_TAG:
            depth--;
            break;
          case XmlPullParser.START_TAG:
            depth++;
            break;
        }
      }
    }
  }
}
