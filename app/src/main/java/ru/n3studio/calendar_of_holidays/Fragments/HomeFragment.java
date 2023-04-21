package ru.n3studio.calendar_of_holidays.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.yandex.mobile.ads.banner.AdSize;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.common.AdRequest;

import java.util.Date;

import android.text.format.DateFormat;

import ru.n3studio.calendar_of_holidays.BuildConfig;
import ru.n3studio.calendar_of_holidays.GetHolidays;
import ru.n3studio.calendar_of_holidays.R;
import ru.n3studio.calendar_of_holidays.TopSheetBehavior;
import ru.n3studio.calendar_of_holidays.kot.TopInfoBehavior;

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
    TextView textData;
    TextView textData_;
    public static ScrollView scrollView;
    public static TextView description2;
    ImageButton but_back;
    AppBarLayout ablAppbar;
    public static NestedScrollView nesteScroll;
    TextView day;

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
        description2 = v.findViewById(R.id.description2);
        textData = v.findViewById(R.id.tvTopDetails2);
        textData_ = v.findViewById(R.id.tvTopDetails2_);
        but_back = v.findViewById(R.id.but_back);
        ablAppbar = v.findViewById(R.id.ablAppbar);
        vs = v.findViewById(R.id.top_sheet);//выдвигающееся окно
        vs2 = v.findViewById(R.id.iTopDetails);// колапсирующая
        vs2.setVisibility(View.VISIBLE);
        vs.setVisibility(View.INVISIBLE);
        nesteScroll = v.findViewById(R.id.nesteScroll);
        nesteScroll.setVisibility(View.VISIBLE);
        description2.setVisibility(View.VISIBLE);
        day = v.findViewById(R.id.tvCollapsedTop);

        ConstraintLayout con = v.findViewById(R.id.constraintLayout22);
        LinearLayout con2 = v.findViewById(R.id.tvTopDetails22);
        con.setVisibility(View.INVISIBLE);
        scrollView = v.findViewById(R.id.ScrollView1);
        scrollView.setVisibility(View.INVISIBLE);
//        scrollView.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // TODO Auto-generated method stub
//                return true;
//            }
//        });
//        description.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                vs2.setVisibility(View.VISIBLE);
//                vs.setVisibility(View.INVISIBLE);
//            }
//        });

        TextView txt = v.findViewById(R.id.txt);
        txt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getContext(), "подождите", Toast.LENGTH_LONG).show();
                return false;
            }
        });


        but_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopSheetBehavior.from(vs).setState(TopSheetBehavior.STATE_COLLAPSED);
                vs.setVisibility(View.INVISIBLE);
                vs2.setVisibility(View.VISIBLE);
                con.setVisibility(View.INVISIBLE);
                con2.setVisibility(View.INVISIBLE);
                ablAppbar.setVisibility(View.VISIBLE);
                nesteScroll.setVisibility(View.VISIBLE);
            }
        });

        txt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                vs2.setVisibility(View.INVISIBLE);
                vs.setVisibility(View.VISIBLE);
                con.setVisibility(View.VISIBLE);
                con2.setVisibility(View.VISIBLE);

                ablAppbar.setVisibility(View.INVISIBLE);
                try {
                    description.setText(GetHolidays.description[0]);
                    description2.setText(GetHolidays.description[0]);
                } catch (Exception e) {
                }
                HomeFragment.scrollView.setVisibility(View.INVISIBLE);
                HomeFragment.description2.setVisibility(View.VISIBLE);

                return false;
            }
        });

        if (GetHolidays.adapter == null) {
            holidays = new GetHolidays("http://n3studio.ru/holiday_example.xml", getActivity(), mainList, title1, title2);
        } else {
            try {
                GetHolidays.adapter.notifyDataSetChanged();
                mainList.setAdapter(GetHolidays.adapter);
                day.setText(GetHolidays.title[0]);
                description.setText(GetHolidays.description[0]);
                description2.setText(GetHolidays.description[0]);
                title1.setText(GetHolidays.title[0]);
                title2.setText(GetHolidays.title[0]);
            } catch (Exception e) {
            }
        }


        mainList.setClickable(true);
        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                if (TopSheetBehavior.from(vs).getState() == TopSheetBehavior.STATE_COLLAPSED) {
                    vs2.setVisibility(View.INVISIBLE);
                    vs.setVisibility(View.VISIBLE);
                    con.setVisibility(View.INVISIBLE);
                    con2.setVisibility(View.INVISIBLE);
                    ablAppbar.setVisibility(View.INVISIBLE);
                    nesteScroll.setVisibility(View.INVISIBLE);


                    try {
                        description.setText(GetHolidays.description[position]);
                        description2.setText(GetHolidays.description[position]);
                    } catch (Exception e) {
                    }
                    TopSheetBehavior.from(vs).setState(TopSheetBehavior.STATE_EXPANDED);
                }

            }
        });


        getData();

        return v;
    }


    public void creat_load_add() {
        mBannerAdView = v.findViewById(R.id.banner_ad_view);
        mBannerAdView.setAdUnitId("demo-banner-yandex");
        mBannerAdView.setAdSize(AdSize.stickySize(800));
        adRequest = new AdRequest.Builder().build();
        mBannerAdView.loadAd(adRequest);

    }


    public void getData() {
        try {
            Date date = new Date();
            String day = (String) DateFormat.format("dd", date); // 20
            String monthString = (String) DateFormat.format("MMM", date); // Jun
            String monthNumber = (String) DateFormat.format("MM", date); // 06
            switch (monthNumber) {
                case "01":
                    textData.setText(day + " " + "январь");
                    this.day.setText(day + " " + "январь");
                    textData_.setText(day + " " + "январь");
                    break;
                case "02":
                    textData.setText(day + " " + "февраль");
                    this.day.setText(day + " " + "февраль");
                    textData_.setText(day + " " + "февраль");
                    break;
                case "03":
                    textData.setText(day + " " + "марта");
                    this.day.setText(day + " " + "марта");
                    textData_.setText(day + " " + "марта");
                    break;
                case "04":
                    textData.setText(day + " " + "апрель");
                    this.day.setText(day + " " + "апрель");
                    textData_.setText(day + " " + "апрель");
                    break;
                case "05":
                    textData.setText(day + " " + "май");
                    this.day.setText(day + " " + "май");
                    textData_.setText(day + " " + "май");
                    break;
                case "06":
                    textData.setText(day + " " + "июнь");
                    this.day.setText(day + " " + "июнь");
                    textData_.setText(day + " " + "июнь");
                    break;
                case "07":
                    textData.setText(day + " " + "июль");
                    this.day.setText(day + " " + "июль");
                    textData_.setText(day + " " + "июль");
                    break;
                case "08":
                    textData.setText(day + " " + "август");
                    this.day.setText(day + " " + "август");
                    textData_.setText(day + " " + "август");
                    break;
                case "09":
                    textData.setText(day + " " + "сентябрь");
                    this.day.setText(day + " " + "сентябрь");
                    textData_.setText(day + " " + "сентябрь");
                    break;
                case "10":
                    textData.setText(day + " " + "октябрь");
                    this.day.setText(day + " " + "октябрь");
                    textData_.setText(day + " " + "октябрь");
                    break;
                case "11":
                    textData.setText(day + " " + "ноябрь");
                    this.day.setText(day + " " + "ноябрь");
                    textData_.setText(day + " " + "ноябрь");
                    break;
                case "12":
                    textData.setText(day + " " + "декабрь");
                    this.day.setText(day + " " + "декабрь");
                    textData_.setText(day + " " + "декабрь");
                    break;
            }
        } catch (Exception e) {
        }


    }

}