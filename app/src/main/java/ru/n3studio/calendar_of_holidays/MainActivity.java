package ru.n3studio.calendar_of_holidays;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yandex.mobile.ads.banner.AdSize;
import com.yandex.mobile.ads.banner.BannerAdEventListener;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.common.AdRequest;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.ImpressionData;
import com.yandex.mobile.ads.common.InitializationListener;
import com.yandex.mobile.ads.common.MobileAds;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fragmentReplace(new HomeFragment());
        binding.bottomnavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.Home:
                    fragmentReplace(new HomeFragment());
                    break;
                case R.id.Calendars:
                    fragmentReplace(new CalendarFragment());
                    break;
                case R.id.Widgets:
                    fragmentReplace(new WidgetFragment());
                    break;
                case R.id.Settings:
                    fragmentReplace(new SettingsFragment());
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

    public void fragmentReplace(Fragment fragment){
        FragmentManager fragmentManager =  getSupportFragmentManager();
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