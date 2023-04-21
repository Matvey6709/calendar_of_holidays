package ru.n3studio.calendar_of_holidays.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;
import androidx.preference.SwitchPreferenceCompat;

import android.preference.PreferenceActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;

import ru.n3studio.calendar_of_holidays.BuildConfig;
import ru.n3studio.calendar_of_holidays.R;


public class SettingsFragment extends PreferenceFragmentCompat {
    SwitchPreferenceCompat  dark_switch;
    SharedPreferences prefs;
    Preference sh_button;
    boolean night_mode = false;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        prefs = getActivity().getSharedPreferences(
                "theme", Context.MODE_PRIVATE);
        dark_switch = findPreference("theme123");
        System.out.println(dark_switch.getKey());
        prefs = getActivity().getSharedPreferences("theme", Context.MODE_PRIVATE);
        night_mode = prefs.getBoolean("theme", false);
        if(night_mode){
            dark_switch.setChecked(true);
            dark_switch.setTitle("Светлая тема");
        }else {
            dark_switch.setChecked(false);
            dark_switch.setTitle(R.string.attachment_title);
        }


        dark_switch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if(night_mode){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    prefs.edit().putBoolean("theme", false).apply();
                    dark_switch.setTitle("Светлая тема");
                    dark_switch.setChecked(true);
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    prefs.edit().putBoolean("theme", true).apply();
                    dark_switch.setTitle("Тёмная тема");
                    dark_switch.setChecked(true);
                }
                prefs.edit().putBoolean("theme_ch", true).apply();
                return false;
            }
        });

        sh_button = findPreference("share");
        sh_button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
            @Override
            public boolean onPreferenceClick(Preference preference) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage= "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {}
                return false;
            }
        });


    }
}

