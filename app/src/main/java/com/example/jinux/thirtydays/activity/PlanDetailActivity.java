package com.example.jinux.thirtydays.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jinux.thirtydays.Constants;
import com.example.jinux.thirtydays.R;
import com.example.jinux.thirtydays.bean.PlanItem;
import com.example.jinux.thirtydays.common.Controller;
import com.example.jinux.thirtydays.common.TimeUtil;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by jinux on 15-4-6.
 */
public class PlanDetailActivity extends Activity {

    public static final String IS_ADD_NEW = "isAddNewPlan";
    private DbUtils mDb;
    private PlanItem plan;

    @ViewInject(R.id.tvPlanTitle)
    private TextView mName;
    @ViewInject(R.id.tvPlanStartTime)
    private TextView mStartTime;
    @ViewInject(R.id.tvPlanEndTime)
    private TextView mEndTime;
    @ViewInject(R.id.tvPlanDescription)
    private TextView mDescription;
    @ViewInject(R.id.tvPlanProgress)
    private TextView mProgress;
    @ViewInject(R.id.rtReview)
    private RatingBar mTodayReview;
    @ViewInject(R.id.tvSummary)
    private TextView mTodaySummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_detail);
        ViewUtils.inject(this);
        mDb = DbUtils.create(this);
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
            setPlanProgress();
            mDescription.setText(plan.getDescription());
            mTodayReview.setProgress(plan.getTodayReview());
            mTodaySummary.setText(plan.getTodaySummary());
        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    private void setPlanProgress()  {
        Calendar today = Calendar.getInstance();
        try {
            Calendar start = TimeUtil.fmtString2Calendar(mStartTime.getText().toString(), Constants.DATE_FMT);
            int todayInYear = today.get(Calendar.DAY_OF_YEAR);
            int startInYear = start.get(Calendar.DAY_OF_YEAR);
            if(todayInYear>=startInYear){
                mProgress.setText("第"+(todayInYear-startInYear)+"天");
            }else{
                Toast.makeText(this,"跨世纪的计划，佩服",Toast.LENGTH_LONG);
                return;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
