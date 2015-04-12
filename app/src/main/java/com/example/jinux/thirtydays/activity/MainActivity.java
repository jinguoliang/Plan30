package com.example.jinux.thirtydays.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jinux.thirtydays.R;
import com.example.jinux.thirtydays.adapter.PlanListAdapter;
import com.example.jinux.thirtydays.bean.PlanItem;
import com.example.jinux.thirtydays.common.Controller;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import java.util.LinkedList;
import java.util.List;


public class MainActivity extends Activity {

    private ListView mPlanList;
    private PlanListAdapter mPlanListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {
        mPlanList = (ListView)findViewById(R.id.lvPlanList);
        mPlanListAdapter = new PlanListAdapter(this, preparePlanItemData());
        mPlanList.setAdapter(mPlanListAdapter);
        mPlanList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundle = new Bundle();
                bundle.putLong("id",id);
                Controller.launchActivity(MainActivity.this,PlanDetailActivity.class,bundle);
            }
        });
    }

    private List<PlanItem> preparePlanItemData() {
        String plansName[] = {"做饭","锻炼腹肌","跑步","做俯卧撑","爬山","跳舞"};
        List<PlanItem> data = new LinkedList<PlanItem>();

        for(String planName : plansName) {
            PlanItem item = new PlanItem();
            item.name = planName;
            data.add(item);
        }

        DbUtils db = DbUtils.create(this);
        try {
            List<PlanItem> plans = db.findAll(PlanItem.class);
            return plans;
        } catch (DbException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onNewPlanClick(View v){
        Controller.launchActivity(this, NewPlanActivity.class);
    }

}
