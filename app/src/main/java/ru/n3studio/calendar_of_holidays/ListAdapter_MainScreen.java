package ru.n3studio.calendar_of_holidays;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.yandex.mobile.ads.banner.AdSize;
import com.yandex.mobile.ads.banner.BannerAdEventListener;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.common.AdRequest;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.ImpressionData;
import com.yandex.mobile.ads.common.InitializationListener;
import com.yandex.mobile.ads.common.MobileAds;

public class ListAdapter_MainScreen extends ArrayAdapter<String> {

    private final Activity context;
    private final String title[];
    private final String subtitle[];
    private final int q;


    public ListAdapter_MainScreen(Activity context, String[] title, String[] subtitle, int q) {
        super(context, R.layout.custom_list_layout, title);
        this.context = context;
        this.title = title;
        this.subtitle = subtitle;
        this.q = q;

    }

    boolean i = false;

    public View getView(int pos, View view, ViewGroup parent) {


        LayoutInflater inflater = context.getLayoutInflater();
        View rootView = inflater.inflate(R.layout.custom_list_layout, null, true);

        TextView title_text = rootView.findViewById(R.id.text_title_list);
        TextView subtitle_text = rootView.findViewById(R.id.text_subtitle_list);
        LinearLayout itemList = rootView.findViewById(R.id.itemList_Main);
        LinearLayout normalL = rootView.findViewById(R.id.normalL);
        System.out.println(pos);

//        if(pos == add){
//            itemList.removeAllViews();
//            i = true;
//            try {
//                itemList.addView(mBannerAdView);
//            }catch (Exception e){
//
//            }
////            mBannerAdView.loadAd(adRequest);
//        }else
//        if(!i){
        title_text.setText(title[pos]);
        subtitle_text.setText(subtitle[pos]);
        if (q == pos) {
            itemList.setBackgroundResource(R.drawable.corners);
        } else {
            itemList.setBackgroundResource(R.drawable.corners2);
        }
//        }else if(pos > 1){
//            normalL.setVisibility(View.VISIBLE);
//            title_text.setText(title[pos-1]);
//            subtitle_text.setText(subtitle[pos-1]);
//            if (q == pos-1) {
//                itemList.setBackgroundResource(R.drawable.corners);
//            } else {
//                itemList.setBackgroundResource(R.drawable.corners2);
//            }
//        }else if(pos == 0){
//            normalL.setVisibility(View.VISIBLE);
//            title_text.setText(title[pos]);
//            subtitle_text.setText(subtitle[pos]);
//            if (q == pos) {
//                itemList.setBackgroundResource(R.drawable.corners);
//            } else {
//                itemList.setBackgroundResource(R.drawable.corners2);
//            }
//        }
//        if(pos == title.length){
//            i = false;
//        }

        return rootView;
    }

}
