package ru.n3studio.calendar_of_holidays.Fragments;

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

import ru.n3studio.calendar_of_holidays.GetHolidays;
import ru.n3studio.calendar_of_holidays.ListAdapter_MainScreen;
import ru.n3studio.calendar_of_holidays.MainActivity;
import ru.n3studio.calendar_of_holidays.R;

public class HomeFragment extends Fragment {

    View v;
    ConstraintLayout sky;
    LinearLayout.LayoutParams lp;
    LinearLayout the_main_scroll;
    ConstraintLayout.LayoutParams lp2;

    ListView mainList;


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
    GetHolidays holidays;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, null, true);
        mainList = v.findViewById(R.id.Main_ListView);
        textView = v.findViewById(R.id.textView2);
        creat_load_add();
        if (GetHolidays.adapter == null) {
            holidays = new GetHolidays("http://n3studio.ru/holidays.json", getActivity(), mainList);
        } else {
            mainList.setAdapter(GetHolidays.adapter);
        }



//        constraintLayout = v.findViewById(R.id.constraintLayout);
//        constraintLayout.setOnTouchListener(new OnSwipeTouchListener(getActivity()){
//            public void onSwipeTop() {
//                Toast.makeText(getActivity(), "top", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeRight() {
//                Toast.makeText(getActivity(), "right", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeLeft() {
//                Toast.makeText(getActivity(), "left", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeBottom() {
//                Toast.makeText(getActivity(), "bottom", Toast.LENGTH_SHORT).show();
//            }
//        });

//        holidays = new GetHolidays("http://n3studio.ru/holidays.json", getActivity());

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


    public void creat_load_add() {
        mBannerAdView = v.findViewById(R.id.banner_ad_view);
        mBannerAdView.setAdUnitId("demo-banner-yandex");
        mBannerAdView.setAdSize(AdSize.stickySize(800));
        adRequest = new AdRequest.Builder().build();
        mBannerAdView.loadAd(adRequest);
    }

}