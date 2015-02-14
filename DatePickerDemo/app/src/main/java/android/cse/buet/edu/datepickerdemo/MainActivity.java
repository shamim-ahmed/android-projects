package android.cse.buet.edu.datepickerdemo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends Activity {
  private static final int DATE_DIALOG_ID = 999;
  private int year;
  private int month;
  private int dayOfMonth;
  private Button changeDateButton;
  private TextView dateTextView;
  private DatePicker datePicker;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    changeDateButton = (Button) findViewById(R.id.changeDateButton);
    dateTextView = (TextView) findViewById(R.id.dateTextView);
    datePicker = (DatePicker) findViewById(R.id.datePicker);

    Calendar calendar = Calendar.getInstance();
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH);
    dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

    dateTextView.setText(String.format("%4d-%2d-%2d", year, month + 1, dayOfMonth));
    datePicker.init(year, month, dayOfMonth, null);
    datePicker.setEnabled(false);

    changeDateButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showDialog(DATE_DIALOG_ID);
      }
    });
  }

  @Override
  public Dialog onCreateDialog(int id) {
    if (id == DATE_DIALOG_ID) {
      return new DatePickerDialog(this, new CustomDateSetListener(), year, month, dayOfMonth);
    }

    return null;
  }

  private class CustomDateSetListener implements DatePickerDialog.OnDateSetListener {

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
      MainActivity.this.year = year;
      MainActivity.this.month = month;
      MainActivity.this.dayOfMonth = dayOfMonth;

      dateTextView.setText(String.format("%2d-%2d-%4d", dayOfMonth, month + 1, year));
      datePicker.init(year, month, dayOfMonth, null);
    }
  }
}
