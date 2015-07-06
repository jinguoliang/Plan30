package com.example.jinux.thirtydays.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.jinux.thirtydays.R;
import com.example.jinux.thirtydays.bean.PlanItem;
import com.example.jinux.thirtydays.common.Constants;
import com.example.jinux.thirtydays.common.TimeUtil;
import com.example.jinux.thirtydays.widget.MyDatePickerDialog;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.text.ParseException;
import java.util.Calendar;


public class NewPlanActivity extends Activity {

    @ViewInject(R.id.tvPlanTitle)
    private com.rey.material.widget.EditText mName;
    @ViewInject(R.id.tvPlanDescription)
    private com.rey.material.widget.EditText mDescription;
    @ViewInject(R.id.tvEndDate)
    private TextView mEndDate;
    @ViewInject(R.id.tvStartDate)
    private TextView mStartDate;
    @ViewInject(R.id.tvPlanLength)
    private TextView mTvPlanLength;
    private DbUtils mDb;
    private int mPlanLength = Constants.DEFAULT_PLAN_LENGTH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_plan);
        ViewUtils.inject(this);
        mDb = DbUtils.create(this);

        Calendar calendar = Calendar.getInstance();
        String nowDateString = TimeUtil.fmtCalendar2String(calendar, Constants.DATE_FMT);
        mStartDate.addTextChangedListener(mStartDateWatcher);
        mEndDate.addTextChangedListener(mEndDateWatcher);
        mStartDate.setText(nowDateString);
    }

    @OnClick({R.id.tvEndDate, R.id.tvStartDate})
    public void onTimeButtonClick(View v) throws ParseException {
        Dialog d = MyDatePickerDialog.create((TextView) v);
        d.show();
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

    @OnClick({R.id.btnBack})
    public void onBackClick(View view) {
        finish();
    }

    @OnClick({R.id.btnFinish})
    public void onSaveClick(View view) {
        PlanItem plan = new PlanItem();
        plan.setName(mName.getText().toString());
        String time = "";
        plan.setStartTime(mStartDate.getText().toString());
        plan.setEndTime(mEndDate.getText().toString());
        plan.setDescription(mDescription.getText().toString());
        plan.setProgressDay("第一天");
        try {
            mDb.save(plan);
        } catch (DbException e) {
            e.printStackTrace();
        }
        finish();

    }

    TextWatcher mStartDateWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            Calendar calendar = null;
            try {
                calendar = TimeUtil.fmtString2Calendar(s.toString(), Constants.DATE_FMT);
                calendar.add(Calendar.DAY_OF_YEAR, mPlanLength);//加30天，多一天少一天都不行
                String nextMonthString = TimeUtil.fmtCalendar2String(calendar, Constants.DATE_FMT);
                mEndDate.setText(nextMonthString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    };

    TextWatcher mEndDateWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            try {
                int endDay = TimeUtil.fmtString2Calendar(s.toString(), Constants.DATE_FMT).get(Calendar.DAY_OF_YEAR);
                int start = TimeUtil.fmtString2Calendar(mStartDate.getText().toString(), Constants.DATE_FMT).get(Calendar.DAY_OF_YEAR);
                mPlanLength = endDay - start;
                mTvPlanLength.setText(String.format(getResources().getString(R.string.fmtPlanLength), mPlanLength));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    };
}
