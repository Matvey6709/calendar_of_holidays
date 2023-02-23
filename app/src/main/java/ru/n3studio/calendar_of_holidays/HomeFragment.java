package ru.n3studio.calendar_of_holidays;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yandex.mobile.ads.banner.AdSize;
import com.yandex.mobile.ads.banner.BannerAdEventListener;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.common.AdRequest;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.ImpressionData;
import com.yandex.mobile.ads.common.InitializationListener;
import com.yandex.mobile.ads.common.MobileAds;

public class HomeFragment extends Fragment {

    View v;
    ConstraintLayout sky;
    LinearLayout.LayoutParams lp;
    LinearLayout the_main_scroll;
    ConstraintLayout.LayoutParams lp2;

    ListView mainList;
    String[] title = {"День батарейки", "День пиццы", "День пиццы", "День пиццы", "День пиццы", "День пиццы", "День пиццы", "День пиццы", ""};
    String[] subtitle = {"Батарейку придумал итальянский учёный-физик Аллесандро Вольто.", "Это простое, на первый взгляд, \n" +
            "итальянское национальное блюдо", "День пиццы", "День пиццы", "День пиццы", "День пиццы", "День пиццы", "День пиццы", ""};
    int q = 1;

    BannerAdView mBannerAdView;
    AdRequest adRequest;

    float x;
    float y;
    float x0;
    float y0;
    String sDown;
    String sMove;
    String sUp;
    TextView textView;
    ConstraintLayout constraintLayout;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        v = inflater.inflate(R.layout.fragment_home, null, true);
        sky = v.findViewById(R.id.constraintLayout);
        lp = (LinearLayout.LayoutParams) sky.getLayoutParams();
        the_main_scroll = v.findViewById(R.id.the_main_scroll);
//        orientation();
        creat_load_add();
        mainList = v.findViewById(R.id.Main_ListView);
        ListAdapter_MainScreen adapter = new ListAdapter_MainScreen(getActivity(), title, subtitle, q);
        mainList.setAdapter(adapter);
        textView = v.findViewById(R.id.textView2);
        constraintLayout = v.findViewById(R.id.constraintLayout);
        constraintLayout.setOnTouchListener(new OnSwipeTouchListener(getActivity()){
            public void onSwipeTop() {
                Toast.makeText(getActivity(), "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Toast.makeText(getActivity(), "right", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                Toast.makeText(getActivity(), "left", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                Toast.makeText(getActivity(), "bottom", Toast.LENGTH_SHORT).show();
            }
        });


        return v;
    }



//    public boolean onTouch(View v, MotionEvent event) {
//        x = event.getX();
//        y = event.getY();
//        listener.onFling(event.getX(), event.getY(), x, y);
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN: // нажатие
//                sDown = "Down: " + x + "," + y;
//                sMove = ""; sUp = "";
//                x0 = x;
//                y0 = y;
//                break;
//            case MotionEvent.ACTION_MOVE: // движение
//                sMove = listener.getDirection(x0, y0, x, y);
//                break;
//            case MotionEvent.ACTION_UP: // отпускание
//            case MotionEvent.ACTION_CANCEL:
//                sMove = "";
//                sUp = "Up: " + x + "," + y;
//                break;
//        }
//        textView.setText(sDown + "\n" + sMove + "\n" + sUp);
//        return true;
//    }



    public int getScreenOrientation()
    {
        Display getOrient = getActivity().getWindowManager().getDefaultDisplay();
        int orientation = Configuration.ORIENTATION_UNDEFINED;
        if(getOrient.getWidth()==getOrient.getHeight()){
            orientation = Configuration.ORIENTATION_SQUARE;
        } else{
            if(getOrient.getWidth() < getOrient.getHeight()){
                orientation = Configuration.ORIENTATION_PORTRAIT;
            }else {
                orientation = Configuration.ORIENTATION_LANDSCAPE;
            }
        }
        return orientation;
    }

    public void creat_load_add(){
        MobileAds.initialize(getActivity(), new InitializationListener() {
            @Override
            public void onInitializationCompleted() {
                Log.d("YANDEX_MOBILE_ADS_TAG", "SDK initialized");
            }
        });
        mBannerAdView = v.findViewById(R.id.banner_ad_view);
        mBannerAdView.setAdUnitId("demo-banner-yandex");
        mBannerAdView.setAdSize(AdSize.stickySize(800));
        adRequest = new AdRequest.Builder().build();
        mBannerAdView.setBannerAdEventListener(new BannerAdEventListener() {
            @Override
            public void onAdLoaded() {
//                Toast.makeText(, "загрузилось", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(AdRequestError adRequestError) {
//                Toast.makeText(this.getApplicationContext(), "ошибка", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClicked() {

            }

            @Override
            public void onLeftApplication() {

            }

            @Override
            public void onReturnedToApplication() {

            }

            @Override
            public void onImpression(@Nullable ImpressionData impressionData) {

            }
        });
//        the_main_scroll.addView(mBannerAdView);
        mBannerAdView.loadAd(adRequest);
    }

    public void orientation(){
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
    }
}