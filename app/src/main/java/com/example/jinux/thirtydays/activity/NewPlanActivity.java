package com.example.jinux.thirtydays.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.jinux.thirtydays.R;
import com.example.jinux.thirtydays.bean.PlanItem;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.Calendar;
import java.util.Date;


public class NewPlanActivity extends Activity {

    @ViewInject(R.id.tvPlanTitle)
    private EditText mName;
    @ViewInject(R.id.tvPlanDescription)
    private EditText mDescription;
    @ViewInject(R.id.tvEndTime)
    private EditText mEndTime;
    @ViewInject(R.id.tvStartTime)
    private EditText mStartTime;
    private DbUtils mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_plan);
        ViewUtils.inject(this);
        mDb = DbUtils.create(this);

        initData();
    }

    private void initData() {

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_plan, menu);
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

    public void onBackClick(View view) {
        finish();
    }

    public void onSaveClick(View view) {
        PlanItem plan = new PlanItem();
        plan.setName(mName.getText().toString());
        String time = "";
        plan.setStartTime(mStartTime.getText().toString());
        plan.setEndTime(mEndTime.getText().toString());
        plan.setDescription( mDescription.getText().toString());
        plan.setProgressDay("第一天");
        try {
            mDb.save(plan);
        } catch (DbException e) {
            e.printStackTrace();
        }
        finish();

    }
}
