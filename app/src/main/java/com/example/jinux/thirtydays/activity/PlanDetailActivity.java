package com.example.jinux.thirtydays.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.jinux.thirtydays.R;
import com.example.jinux.thirtydays.bean.PlanItem;
import com.example.jinux.thirtydays.common.Controller;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

/**
 * Created by jinux on 15-4-6.
 */
public class PlanDetailActivity extends Activity {

    public static final String IS_ADD_NEW = "isAddNewPlan";
    private DbUtils mDb;
    private TextView mName;
    private TextView mStartTime;
    private TextView mEndTime;
    private TextView mDescription;
    private TextView mProgress;
    private PlanItem plan;
    private RatingBar mTodayReview;
    private TextView mTodaySummary;

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
            plan = mDb.findById(PlanItem.class, id);
            mName.setText(plan.getName());
            mStartTime.setText(plan.getStartTime());
            mEndTime.setText(plan.getEndTime());
            mProgress.setText(plan.getProgressDay());
            mDescription.setText(plan.getDescription());
            mTodayReview.setProgress(plan.getTodayReview());
            mTodaySummary.setText(plan.getTodaySummary());
        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    private void initUI() {
        mName = (TextView) findViewById(R.id.tvPlanTitle);
        mStartTime = (TextView) findViewById(R.id.tvPlanStartTime);
        mEndTime = (TextView) findViewById(R.id.tvPlanEndTime);
        mProgress = (TextView) findViewById(R.id.tvPlanProgress);
        mDescription = (TextView) findViewById(R.id.tvPlanDescription);
        mTodayReview = (RatingBar) findViewById(R.id.rtReview);
        mTodaySummary = (TextView) findViewById(R.id.tvSummary);
    }

    @Override
    protected void onPause() {
        super.onPause();
            plan.setTodayReview(mTodayReview.getProgress());
        plan.setTodaySummary(mTodaySummary.getText().toString());
        try {
            mDb.update(plan);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void onGoJourneyClick(View view) {
        Controller.launchActivity(this, JourneyActivity.class);
    }
}
