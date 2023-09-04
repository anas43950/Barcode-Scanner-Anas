package com.barcodescanner.fragments.qrcodes;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import androidx.annotation.NonNull;

import com.barcodescanner.R;
import com.barcodescanner.databinding.FragmentEventBinding;
import com.barcodescanner.fragments.BaseFragment;
import com.barcodescanner.models.qrcodes.Event;
import com.barcodescanner.utils.Utility;

import java.util.Calendar;
import java.util.Locale;


public class EventFragment extends BaseFragment {
    FragmentEventBinding binding;
    final Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE);
    String datePattern = "dd-MM-yyyy";
    String timePattern = "HH:mm";

    public EventFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEventBinding.inflate(inflater);
        setCurrentDateAndTime();
        setListeners();
        return binding.getRoot();
    }

    private void setListeners() {
        binding.startDateTv.setOnClickListener(view -> {
            OnDateSetListener onDateSetListener = (datePicker, i, i1, i2) -> {
                binding.startDateTv.setText(String.format(Locale.getDefault(), "%02d-%02d-%d", i2, (i1 + 1), i));
            };
            DatePickerDialog endDatePicker = new DatePickerDialog(createBarcodeActivity, onDateSetListener, year, month, day);
            endDatePicker.show();
        });
        binding.endDateTv.setOnClickListener(view -> {
            OnDateSetListener onDateSetListener = (datePicker, i, i1, i2) -> {
                binding.endDateTv.setText(String.format(Locale.getDefault(), "%02d-%02d-%d", i2, (i1 + 1), i));
            };
            DatePickerDialog endDatePicker = new DatePickerDialog(createBarcodeActivity, onDateSetListener, year, month, day);
            endDatePicker.show();
        });
        binding.startTimeTv.setOnClickListener(view -> {
            OnTimeSetListener onTimeSetListener = new OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
                    String time = String.format(Locale.getDefault(), "%02d:%d", hours, minutes);
                    binding.startTimeTv.setText(time);
                }
            };
            TimePickerDialog timePickerDialog = new TimePickerDialog(createBarcodeActivity, onTimeSetListener, hour, minute, false);
            timePickerDialog.show();
        });
        binding.endTimeTv.setOnClickListener(view -> {
            OnTimeSetListener onTimeSetListener = new OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
                    String time = String.format(Locale.getDefault(), "%02d:%d", hours, minutes);
                    binding.endTimeTv.setText(time);
                }
            };
            TimePickerDialog timePickerDialog = new TimePickerDialog(createBarcodeActivity, onTimeSetListener, hour, minute, false);
            timePickerDialog.show();
        });
    }

    private void setCurrentDateAndTime() {
        binding.startDateTv.setText(Utility.getFormattedDate(System.currentTimeMillis(), datePattern));
        binding.endDateTv.setText(Utility.getFormattedDate(System.currentTimeMillis(), datePattern));
        binding.startTimeTv.setText(Utility.getFormattedDate(System.currentTimeMillis(), timePattern));
        binding.endTimeTv.setText(Utility.getFormattedDate(System.currentTimeMillis(), timePattern));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done_menu) {
            String title = binding.titleEt.getText().toString().trim();
            String organizer = binding.organizerEt.getText().toString().trim();
            String summary = binding.summaryEt.getText().toString().trim();
            String startDateAndTime = binding.startDateTv.getText().toString() + "T" + binding.startTimeTv.getText().toString();
            String endDateAndTime = binding.endDateTv.getText().toString() + "T" + binding.endTimeTv.getText().toString();
            createBarcodeActivity.createBarcode(new Event(title, organizer, summary, startDateAndTime, endDateAndTime).toSchema());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}