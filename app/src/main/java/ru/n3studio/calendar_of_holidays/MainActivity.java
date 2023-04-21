package ru.n3studio.calendar_of_holidays;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.yandex.mobile.ads.common.InitializationListener;
import com.yandex.mobile.ads.common.MobileAds;

import ru.n3studio.calendar_of_holidays.Convectror.Holiday;
import ru.n3studio.calendar_of_holidays.Fragments.CalendarFragment;
import ru.n3studio.calendar_of_holidays.Fragments.HomeFragment;
import ru.n3studio.calendar_of_holidays.Fragments.SettingsFragment;
import ru.n3studio.calendar_of_holidays.Fragments.WidgetFragment;
import ru.n3studio.calendar_of_holidays.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

//    ConstraintLayout sky;
//    ConstraintLayout.LayoutParams lp;
//    LinearLayout the_main_scroll;
//    ConstraintLayout.LayoutParams lp2;
//
//    ListView mainList;
//    String[] title = {"День батарейки", "День пиццы", "День пиццы", "День пиццы", "День пиццы", "День пиццы",  "День пиццы",  "День пиццы", ""};
//    String[] subtitle = {"Батарейку придумал итальянский учёный-физик Аллесандро Вольто.", "Это простое, на первый взгляд, \n" +
//            "итальянское национальное блюдо", "День пиццы", "День пиццы", "День пиццы", "День пиццы", "День пиццы", "День пиццы", ""};
//    int q = 1;
//
//    BannerAdView mBannerAdView;
//    AdRequest adRequest;

    ActivityMainBinding binding;
    HomeFragment homeFragment;
    public static CalendarFragment calendarFragment;
    WidgetFragment widgetFragment;
    public static SettingsFragment settingsFragment;
    SharedPreferences prefs;
    boolean night_mode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        homeFragment = new HomeFragment();
        prefs = this.getSharedPreferences(
                "theme", Context.MODE_PRIVATE);
        calendarFragment = new CalendarFragment();
        widgetFragment = new WidgetFragment();
        settingsFragment = new SettingsFragment();
        prefs = this.getSharedPreferences("theme", Context.MODE_PRIVATE);
        if (prefs.getBoolean("theme", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        if (prefs.getBoolean("theme_ch", false)) {
            fragmentReplace(settingsFragment);
            prefs.edit().putBoolean("theme_ch", false).apply();
        } else {
            fragmentReplace(new HomeFragment());
        }
//        if (savedInstanceState == null) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.settings, new fragment_settings1.SettingsFragment())
//                    .commit();
//        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MobileAds.initialize(MainActivity.this, new InitializationListener() {
                    @Override
                    public void onInitializationCompleted() {
                        Log.d("YANDEX_MOBILE_ADS_TAG", "SDK initialized");
                    }
                });
            }
        }, 0);

        binding.bottomnavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.Home:
                    fragmentReplace(new HomeFragment());
                    break;
                case R.id.Calendars:
                    fragmentReplace(calendarFragment);
                    break;
                case R.id.Widgets:
                    fragmentReplace(widgetFragment);
                    break;
                case R.id.Settings:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout, new SettingsFragment())
                            .commit();

                    ActionBar acdtionBar = getSupportActionBar();
                    if (acdtionBar != null) {
                        acdtionBar.setDisplayHomeAsUpEnabled(true);
                    }

                    break;
            }
            return true;
        });


//        Toast.makeText(getApplicationContext(),
//                getScreenOrientation()+"", Toast.LENGTH_SHORT).show();
//        sky = findViewById(R.id.constraintLayout);
//        lp = (ConstraintLayout.LayoutParams) sky.getLayoutParams();
//        the_main_scroll = findViewById(R.id.the_main_scroll);
//        orientation();
//        creat_load_add();
//        mainList = findViewById(R.id.Main_ListView);
//        ListAdapter_MainScreen adapter = new ListAdapter_MainScreen(MainActivity.this, title, subtitle, q);
//        mainList.setAdapter(adapter);


    }



    public void fragmentReplace(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

//    public int getScreenOrientation()
//    {
//        Display getOrient = getWindowManager().getDefaultDisplay();
//        int orientation = Configuration.ORIENTATION_UNDEFINED;
//        if(getOrient.getWidth()==getOrient.getHeight()){
//            orientation = Configuration.ORIENTATION_SQUARE;
//        } else{
//            if(getOrient.getWidth() < getOrient.getHeight()){
//                orientation = Configuration.ORIENTATION_PORTRAIT;
//            }else {
//                orientation = Configuration.ORIENTATION_LANDSCAPE;
//            }
//        }
//        return orientation;
//    }
//
//    public void creat_load_add(){
//        MobileAds.initialize(this, new InitializationListener() {
//            @Override
//            public void onInitializationCompleted() {
//                Log.d("YANDEX_MOBILE_ADS_TAG", "SDK initialized");
//            }
//        });
//        mBannerAdView = findViewById(R.id.banner_ad_view);
//        mBannerAdView.setAdUnitId("demo-banner-yandex");
//        mBannerAdView.setAdSize(AdSize.stickySize(800));
//        adRequest = new AdRequest.Builder().build();
//        mBannerAdView.setBannerAdEventListener(new BannerAdEventListener() {
//            @Override
//            public void onAdLoaded() {
////                Toast.makeText(, "загрузилось", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onAdFailedToLoad(AdRequestError adRequestError) {
////                Toast.makeText(this.getApplicationContext(), "ошибка", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onAdClicked() {
//
//            }
//
//            @Override
//            public void onLeftApplication() {
//
//            }
//
//            @Override
//            public void onReturnedToApplication() {
//
//            }
//
//            @Override
//            public void onImpression(@Nullable ImpressionData impressionData) {
//
//            }
//        });
////        the_main_scroll.addView(mBannerAdView);
//        mBannerAdView.loadAd(adRequest);
//    }
//
//    public void orientation(){
//        lp2 = (ConstraintLayout.LayoutParams) the_main_scroll.getLayoutParams();
//        if(getScreenOrientation() == 1){
//            lp.matchConstraintPercentHeight = (float) 0.35;
//            lp2.matchConstraintPercentHeight = (float) 0.55;
//        }else {
//            lp.matchConstraintPercentHeight = (float) 0.75;
//            lp2.matchConstraintPercentHeight = (float) 0.15;
//        }
//        sky.setLayoutParams(lp);
//        the_main_scroll.setLayoutParams(lp2);
//    }

}