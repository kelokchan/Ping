package com.teamveryniceah.ping;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.widget.RemoteViews;

import static android.app.PendingIntent.getActivity;

/**
 * Created by Chin on 14/6/2015.
 */
public class WidgetPolice extends AppWidgetProvider {
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for (int i = 0; i < appWidgetIds.length; i++) {
            int appWidgetId = appWidgetIds[i];

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.police_widget);


            Intent intent = new Intent(context,MainActivity.class);
            PendingIntent pending = getActivity(context, 0, intent, 0);

            views.setOnClickPendingIntent(R.id.Wpolice,pending);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
