package ru.n3studio.calendar_of_holidays.Fragments;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

import ru.n3studio.calendar_of_holidays.Data_Widget;
import ru.n3studio.calendar_of_holidays.R;

public class WidgetFragment extends Fragment {
    View v;
    Button btn;
    AppWidgetManager mAppWidgetManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_widget, null, true);
        btn = v.findViewById(R.id.button_add_widget);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mAppWidgetManager = getActivity().getSystemService(AppWidgetManager.class);
                }
                ComponentName myProvider = new ComponentName(getActivity(), Data_Widget.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (mAppWidgetManager.isRequestPinAppWidgetSupported()) {
                        Intent pinnedWidgetCallback = new Intent(getActivity(), Data_Widget.class);
                        PendingIntent successCallback = PendingIntent.getBroadcast(getActivity(), 0, pinnedWidgetCallback, PendingIntent.FLAG_IMMUTABLE);
                        mAppWidgetManager.requestPinAppWidget(myProvider, null, successCallback);
                    }
                }
            }
        });
        return v;
    }



}