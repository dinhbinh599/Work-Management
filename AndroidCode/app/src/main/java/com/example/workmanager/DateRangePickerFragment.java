package com.example.workmanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class DateRangePickerFragment extends Fragment {
    EditText edtStartTime, edtEndTime;
    DatePickerFragment startTimeFragment, endTimeFragment;
    Calendar startTime, endTime;

    public DateRangePickerFragment() {
        // Required empty public constructor
    }

    public EditText getEdtStartTime() {
        return edtStartTime;
    }

    public EditText getEdtEndTime() {
        return edtEndTime;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        if (bundle != null) {
            startTime = (Calendar) bundle.getSerializable("startTime");
            endTime = (Calendar) bundle.getSerializable("endTime");
        } else {
            startTime = Calendar.getInstance();
            endTime = Calendar.getInstance();
            endTime.add(Calendar.DAY_OF_MONTH, 1);
        }
        View view = inflater.inflate(R.layout.fragment_date_range_picker, container, false);
        edtStartTime = view.findViewById(R.id.edtStartTime);
        edtEndTime = view.findViewById(R.id.edtEndTime);
        startTimeFragment = new DatePickerFragment();
        startTimeFragment.setCalendar(startTime);
        startTimeFragment.setEditText(edtStartTime);
        endTimeFragment = new DatePickerFragment();
        endTimeFragment.setCalendar(endTime);
        endTimeFragment.setEditText(edtEndTime);
        if (bundle != null) {
            startTimeFragment.setTextCalendar();
            endTimeFragment.setTextCalendar();
        }
        edtStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimeFragment.show(getActivity().getSupportFragmentManager(), "DatePicker");
            }
        });

        edtEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endTimeFragment.show(getActivity().getSupportFragmentManager(), "DatePicker2");
            }
        });
        return view;
    }
}
