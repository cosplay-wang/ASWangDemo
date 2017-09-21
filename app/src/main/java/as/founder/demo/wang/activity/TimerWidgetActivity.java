package as.founder.demo.wang.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import as.founder.demo.wang.R;

public class TimerWidgetActivity extends AppCompatActivity {
    DatePicker myDatePicker;
    TimePicker myTimePicker;
    NumberPicker myNumberPicker;
    TextView myDatePickerShow,myTimePickerShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取日历对象
        Calendar calendar = Calendar.getInstance();
        // 获取当前对应的年、月、日的信息
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH) + 1;
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);
        setContentView(R.layout.activity_timer_widget);
        myDatePickerShow = (TextView) findViewById(R.id.myDatePicker_show);
        myTimePickerShow = (TextView) findViewById(R.id.myTimePicker_show);
        myDatePickerShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(TimerWidgetActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        setTitle(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day).show();
            }
        });
        myTimePickerShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 初始化TimerPickerDialog
                new TimePickerDialog(TimerWidgetActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        setTitle(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true).show();
            }
        });
        myNumberPicker = (NumberPicker) findViewById(R.id.myNumberPicker);
        // 设置NumberPicker属性
        myNumberPicker.setMinValue(0);
        myNumberPicker.setMaxValue(20);
        myNumberPicker.setValue(10);
        myNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                setTitle(oldVal + ":" + newVal);
            }
        });


    }
}
