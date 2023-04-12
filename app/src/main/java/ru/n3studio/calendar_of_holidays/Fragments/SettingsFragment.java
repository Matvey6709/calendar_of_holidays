package ru.n3studio.calendar_of_holidays.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import ru.n3studio.calendar_of_holidays.BuildConfig;
import ru.n3studio.calendar_of_holidays.R;

public class SettingsFragment extends Fragment {

    View v;
    Switch dark_switch;
    SharedPreferences prefs;
    Button sh_button;
    boolean night_mode = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_settings, container, false);
        prefs = getActivity().getSharedPreferences(
                "theme", Context.MODE_PRIVATE);
        dark_switch = v.findViewById(R.id.switch1);
        prefs = getActivity().getSharedPreferences("theme", Context.MODE_PRIVATE);
        night_mode = prefs.getBoolean("theme", false);
        if(night_mode){
            dark_switch.setChecked(true);
            dark_switch.setText("Светлая тема");
        }else {
            dark_switch.setText("Тёмная тема");
        }

        dark_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(night_mode){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    prefs.edit().putBoolean("theme", false).apply();
                    dark_switch.setText("Светлая тема");

                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    prefs.edit().putBoolean("theme", true).apply();
                    dark_switch.setText("Тёмная тема");
                }
                prefs.edit().putBoolean("theme_ch", true).apply();
            }
        });

        sh_button = v.findViewById(R.id.sh_button);
        sh_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage= "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {}
            }
        });
        return v;
    }


}