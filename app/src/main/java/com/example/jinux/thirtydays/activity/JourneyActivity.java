package com.example.jinux.thirtydays.activity;

import android.app.Activity;
import android.app.IntentService;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.IBinder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.jinux.thirtydays.R;
import com.example.jinux.thirtydays.adapter.JouneyListAdapter;
import com.example.jinux.thirtydays.adapter.PlanListAdapter;
import com.example.jinux.thirtydays.bean.JourneyItem;
import com.example.jinux.thirtydays.bean.PlanItem;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jinux on 15-4-8.
 */
public class JourneyActivity extends Activity {
    private ListView mPlanList;
    private JouneyListAdapter mPlanListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey);
        initUI();
    }

    private void initUI() {
        mPlanList = (ListView)findViewById(R.id.lvPlanList);
        mPlanListAdapter = new JouneyListAdapter(this, prepareJourneyItemData());
        mPlanList.setAdapter(mPlanListAdapter);
    }

    private List<JourneyItem> prepareJourneyItemData() {
        String journeyName[] = {"做饭","锻炼腹肌","跑步","做俯卧撑","爬山","跳舞"};
        List<JourneyItem> data = new LinkedList<JourneyItem>();

        for(String journey : journeyName) {
            JourneyItem item = new JourneyItem();
            item.day = "第一天";
            item.content = journey;
            data.add(item);
        }
        return data;
    }

    class M extends Service{

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }
}
