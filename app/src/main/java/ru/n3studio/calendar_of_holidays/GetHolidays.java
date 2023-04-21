package ru.n3studio.calendar_of_holidays;

import android.app.Activity;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

import ru.n3studio.calendar_of_holidays.Convectror.Converter;
import ru.n3studio.calendar_of_holidays.Convectror.Holiday;
import ru.n3studio.calendar_of_holidays.Convectror.Welcome;


public class GetHolidays {

    private Document doc;

    private String url;

    private Thread thread;

    private Runnable runnable;

    private Activity activity;

    ListView listView;
    TextView maintext;
    TextView maintext2;

    public static String[] title;
    String[] subtitle;
    public static String[] description;

    int day1 = 1;
    int month = 1;
    String datas = "10.03";
    Welcome[] data;
    Holiday[] hol;


    public static ListAdapter_MainScreen adapter;


    public GetHolidays(String url, Activity activity, ListView listView, TextView maintext , TextView maintext2) {
        this.url = url;
        this.activity = activity;
        this.listView = listView;
        this.maintext = maintext;
        this.maintext2 = maintext2;
        init();
    }
    boolean one = false;
    public void init() {
        runnable = new Runnable() {
            @Override
            public void run() {

                getWeb();
                try {
                    System.out.println(doc.text());
                    Element root = (Element) doc.getElementById("item0");
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        data = Converter.fromJsonString(doc.text());

                    }
                    for (int i = 0; i < data.length - 1; i++) {
                        System.out.println(data[i].getDate());
                        if ((data[i].getDate()).equals(datas)) {
                            hol = data[i].getHolidays();
                            title = new String[hol.length];
                            subtitle = new String[hol.length];
                            description = new String[hol.length];
                            System.out.println(title.length);
                            System.out.println(subtitle.length);
                            break;
                        }

                    }

                    for (int j = 0; j < hol.length; j++) {
                        title[j] = hol[j].getTitle();
                        subtitle[j] = hol[j].getShortDescription();
                        description[j] = hol[j].getDescription();
                        System.out.println(title[j]);
                        System.out.println(subtitle[j]);
                    }
                    synchronized (this) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter = new ListAdapter_MainScreen(activity, title, subtitle, 0);

                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                maintext.setText(title[0]);
                                maintext2.setText(title[0]);
                            }
                        });
                    }
                } catch (Exception e) {}

            }
        };
        thread = new Thread(runnable);
        thread.start();
    }

    public void theme(){
        adapter = new ListAdapter_MainScreen(activity, title, subtitle, 0);
        adapter.notifyDataSetChanged();
    }

//    public void setAdapter(ListView listView, ListAdapter_MainScreen adapter) {
//        synchronized (this) {
//            activity.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    listView.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
//                }
//            });
//        }
//    }

    public void getWeb() {
        try {
            doc = Jsoup.connect(url).ignoreContentType(true).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDescription(int pos){
        return description[pos];
    }
    public String title(int pos){
        return title[pos];
    }
}
