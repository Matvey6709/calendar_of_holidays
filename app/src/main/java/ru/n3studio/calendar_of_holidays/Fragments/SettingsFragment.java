package ru.n3studio.calendar_of_holidays.Fragments;

import static ru.n3studio.calendar_of_holidays.MainActivity.settingsFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import ru.n3studio.calendar_of_holidays.R;

public class SettingsFragment extends Fragment {

    View v;
    Switch dark_switch;
    SharedPreferences prefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_settings, container, false);
        prefs = getActivity().getSharedPreferences(
                "theme", Context.MODE_PRIVATE);
        dark_switch = v.findViewById(R.id.switch1);
        dark_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    dark_switch.setText("Тёмная тема");
                }else if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    dark_switch.setText("Светлая тема");
                }
                prefs.edit().putBoolean("theme", true).apply();
            }
        });
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            dark_switch.setChecked(false);
            dark_switch.setText("Светлая тема");
        }else {
            dark_switch.setChecked(true);
            dark_switch.setText("Тёмная тема");
        }
        return v;
    }

    public void fragmentReplace(Fragment fragment){
        FragmentManager fragmentManager =  getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}