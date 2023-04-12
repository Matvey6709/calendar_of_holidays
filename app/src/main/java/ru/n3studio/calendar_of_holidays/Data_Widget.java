package ru.n3studio.calendar_of_holidays;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.text.format.DateFormat;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.Date;


public class Data_Widget extends AppWidgetProvider {



    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

//        CharSequence widgetText = Data_WidgetConfigureActivity.loadTitlePref(context, appWidgetId);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.data__widget);
//        views.setTextViewText(R.id.appwidget_text, widgetText);
        views.setTextViewText(R.id.widget_mount, getData().split(" ")[1]);
        views.setTextViewText(R.id.widget_day, getData().split(" ")[0]);
        views.setTextViewText(R.id.widget_week, getData().split(" ")[2]);
        views.setTextViewText(R.id.widget_holiday, "Праздников: " + getData().split(" ")[3]);
        appWidgetManager.updateAppWidget(appWidgetId, views);
        TextView widget_mount;
        TextView widget_week;
        TextView widget_day;
        TextView widget_holiday;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
            System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhh");
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            Data_WidgetConfigureActivity.deleteTitlePref(context, appWidgetId);
        }
    }

    public static String getData() {
        String str = "";
        String week = "";
        try {
            Date date = new Date();
            String day = (String) DateFormat.format("dd", date); // 20
            week = (String) DateFormat.format("EEEE", date);
            String monthString = (String) DateFormat.format("MMM", date); // Jun
            String monthNumber = (String) DateFormat.format("MM", date); // 06
            System.out.println(date);
            switch (monthNumber) {
                case "01":
                    str = day + " " + "Янв.";
                    break;
                case "02":
                    str = day + " " + "Февр.";
                    break;
                case "03":
                    str = day + " " + "Март";
                    break;
                case "04":
                    str = day + " " + "Апр.";
                    break;
                case "05":
                    str = day + " " + "Май";
                    break;
                case "06":
                    str = day + " " + "Июнь";
                    break;
                case "07":
                    str = day + " " + "Июль";
                    break;
                case "08":
                    str = day + " " + "Авг.";
                    break;
                case "09":
                    str = day + " " + "Сен.";
                    break;
                case "10":
                    str = day + " " + "Окт.";
                    break;
                case "11":
                    str = day + " " + "Ноя.";
                    break;
                case "12":
                    str = day + " " + "Дек.";

                    break;
            }
        } catch (Exception e) {
        }
        return str + " " + week.split("")[0]+ week.split("")[1]+ week.split("")[2]+". " + GetHolidays.title.length;
    }

}