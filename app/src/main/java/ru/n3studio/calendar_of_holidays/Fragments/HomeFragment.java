package ru.n3studio.calendar_of_holidays.Fragments;

import static android.view.DragEvent.ACTION_DRAG_ENDED;
import static android.view.DragEvent.ACTION_DRAG_ENTERED;
import static android.view.DragEvent.ACTION_DRAG_EXITED;
import static android.view.DragEvent.ACTION_DRAG_LOCATION;
import static android.view.DragEvent.ACTION_DRAG_STARTED;
import static android.view.DragEvent.ACTION_DROP;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.NestedScrollingChild;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yandex.mobile.ads.banner.AdSize;
import com.yandex.mobile.ads.banner.BannerAdEventListener;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.common.AdRequest;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.ImpressionData;

import org.w3c.dom.ls.LSOutput;

import ru.n3studio.calendar_of_holidays.GetHolidays;
import ru.n3studio.calendar_of_holidays.OnSwipeTouchListener;
import ru.n3studio.calendar_of_holidays.R;
import ru.n3studio.calendar_of_holidays.TopSheetBehavior;

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
    public static View vs;
    public static View vs2;
    Handler handler = new Handler();
    TextView title1;
    TextView title2;
    TextView description;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, null, true);
        mainList = v.findViewById(R.id.Main_ListView);
        creat_load_add();
        title1 = v.findViewById(R.id.textView2);
        title2 = v.findViewById(R.id.textView31);
        description = v.findViewById(R.id.description);
        if (GetHolidays.adapter == null) {
            holidays = new GetHolidays("http://n3studio.ru/holidays.json", getActivity(), mainList, title1, title2);
        } else {
            try {
                mainList.setAdapter(GetHolidays.adapter);
                description.setText(GetHolidays.description[0]);
            }catch (Exception e){}
        }
        vs = v.findViewById(R.id.top_sheet);
        vs.setVisibility(View.VISIBLE);

        vs2 = v.findViewById(R.id.iTopDetails);
        vs2.setVisibility(View.VISIBLE);
        TopSheetBehavior.from(vs).setState(TopSheetBehavior.STATE_COLLAPSED);
        TextView con = v.findViewById(R.id.txt);
        con.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getContext(), "подождите", Toast.LENGTH_LONG).show();
                return false;
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                con.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (TopSheetBehavior.from(vs).getState() == TopSheetBehavior.STATE_COLLAPSED) {
                            vs2.setVisibility(View.INVISIBLE);
                            vs.setVisibility(View.VISIBLE);
                            TopSheetBehavior.from(vs).setState(TopSheetBehavior.STATE_EXPANDED);
                            try {
                                description.setText(GetHolidays.description[0]);
                            }catch (Exception e){}
                        }
                        return false;
                    }
                });
            }
        }, 1000);

        mainList.setClickable(true);
        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                if (TopSheetBehavior.from(vs).getState() == TopSheetBehavior.STATE_COLLAPSED) {
                    System.out.println("SDgdfg  " + position);
                    vs2.setVisibility(View.INVISIBLE);
                    vs.setVisibility(View.VISIBLE);
                    try {
                        description.setText(GetHolidays.description[position]);
                    }catch (Exception e){}
                    TopSheetBehavior.from(vs).setState(TopSheetBehavior.STATE_EXPANDED);
                }

            }
        });


//        con.setBackgroundColor(getResources().getColor(R.color.p));
//
//        LinearLayout bottomSheet = v.findViewById(R.id.bottom_sheet_behavior_id);
//        View sheet = v.findViewById(R.id.top_sheet);
//        TopSheetBehavior.from(sheet).setState(TopSheetBehavior.STATE_EXPANDED);
//
//
//        constraintLayout = v.findViewById(R.id.top_sheet);
//        holidays = new GetHolidays("http://n3studio.ru/holidays.json", getActivity());
        return v;
    }
//    public boolean onTouch(View v, MotionEvent event) {
//        x = event.getX();
//        y = event.getY();
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN: // нажатие
//                sDown = "Down: " + x + "," + y;
//                sMove = ""; sUp = "";
//                x0 = x;
//                y0 = y;
//                break;
//            case MotionEvent.ACTION_MOVE: // движение
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