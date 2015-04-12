package com.example.jinux.thirtydays.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jinux.thirtydays.R;
import com.example.jinux.thirtydays.bean.PlanItem;
import com.example.jinux.thirtydays.common.Controller;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import java.util.ResourceBundle;

/**
 * Created by jinux on 15-4-6.
 */
public class PlanDetailActivity extends Activity{

    public static final String IS_ADD_NEW = "isAddNewPlan";
    private DbUtils mDb;
    private TextView mName;
    private TextView mStartTime;
    private TextView mEndTime;
    private TextView mDescription;
    private TextView mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_detail);
        mDb = DbUtils.create(this);
        initUI();
        initData();
    }

        private void initData() {
            Bundle bundle = getIntent().getExtras();
            long id = bundle.getLong("id");
            try {
                PlanItem plan =  mDb.findById(PlanItem.class,id);
                mName.setText(plan.name);
                mStartTime.setText(plan.startTime);
                mEndTime.setText(plan.endTime);
                mProgress.setText(plan.progressDay);
                mDescription.setText(plan.description);
            } catch (DbException e) {
                e.printStackTrace();
            }

        }

    private void initUI() {
        mName = (TextView)findViewById(R.id.tvPlanTitle);
        mStartTime = (TextView)findViewById(R.id.tvPlanStartTime);
        mEndTime = (TextView)findViewById(R.id.tvPlanEndTime);
        mProgress = (TextView)findViewById(R.id.tvPlanProgress);
        mDescription = (TextView)findViewById(R.id.tvPlanDescription);
    }

    public void onGoJourneyClick(View view){
        Controller.launchActivity(this,JourneyActivity.class);
    }
}
