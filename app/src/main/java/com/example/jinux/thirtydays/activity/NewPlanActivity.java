package com.example.jinux.thirtydays.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jinux.thirtydays.common.Constants;
import com.example.jinux.thirtydays.R;
import com.example.jinux.thirtydays.bean.PlanItem;
import com.example.jinux.thirtydays.common.TimeUtil;
import com.example.jinux.thirtydays.common.Utils;
import com.example.jinux.thirtydays.widget.MyDatePickerDialog;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.text.ParseException;
import java.util.Calendar;


public class NewPlanActivity extends Activity implements TextWatcher{

    @ViewInject(R.id.tvPlanTitle)
    private EditText mName;
    @ViewInject(R.id.tvPlanDescription)
    private EditText mDescription;
    @ViewInject(R.id.tvEndTime)
    private TextView mEndTime;
    @ViewInject(R.id.tvStartTime)
    private TextView mStartTime;
    private DbUtils mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_plan);
        ViewUtils.inject(this);
        mDb = DbUtils.create(this);

        Calendar calendar = Calendar.getInstance();
        String nowDateString = TimeUtil.fmtCalendar2String(calendar, Constants.DATE_FMT);
        mStartTime.addTextChangedListener(this);
        mStartTime.setText(nowDateString);
    }

    @OnClick({R.id.tvEndTime,R.id.tvStartTime})
    public void OnEndTimeClick(View v) throws ParseException {
        Dialog d = MyDatePickerDialog.create((TextView)v);
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        Utils.toastShow(this,"text = " + s.toString());
        Calendar calendar = null;
        try {
            calendar = TimeUtil.fmtString2Calendar(s.toString(), Constants.DATE_FMT);
            calendar.add(Calendar.DAY_OF_YEAR,30);//加30天，多一天少一天都不行
            String nextMonthString = TimeUtil.fmtCalendar2String(calendar, Constants.DATE_FMT);
            mEndTime.setText(nextMonthString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
