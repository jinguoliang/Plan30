package com.example.jinux.thirtydays.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jinux.thirtydays.R;
import com.example.jinux.thirtydays.adapter.PlanListAdapter;
import com.example.jinux.thirtydays.bean.PlanItem;
import com.example.jinux.thirtydays.common.Constants;
import com.example.jinux.thirtydays.common.Controller;
import com.example.jinux.thirtydays.common.DialogUtil;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.rey.material.util.ViewUtil;
import com.rey.material.widget.FloatingActionButton;

import java.util.List;


public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @ViewInject(R.id.lvPlanList)
    private ListView mPlanList;
    @ViewInject(R.id.btnNewPlan)
    private FloatingActionButton newPlanBtn;

    private Dialog mDialog;
    private List<PlanItem> mPlans;
    private boolean isShowDialog;
    private PlanListAdapter mPlanListAdapter;
    private DbUtils db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);

        db = DbUtils.create(this);
        initUI();
    }

    private void initUI() {
        mPlanListAdapter = new PlanListAdapter(this, preparePlanItemData());
        mPlanList.setAdapter(mPlanListAdapter);
        mPlanList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundle = new Bundle();
                bundle.putLong("id",id);
                Controller.launchActivity(MainActivity.this,PlanDetailActivity.class,bundle);
                overridePendingTransition(R.anim.abc_slide_in_bottom,R.anim.abc_fade_out);
            }
        });
        mPlanList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, final long id) {
                mDialog = DialogUtil.createOkCancelDialog(MainActivity.this,"删除计划","你确定删除计划吗？","当然","算了",new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.hide();
                        try {
                            db.deleteById(PlanItem.class,id);
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                        refreshData();
                    }
                },new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.hide();
                    }
                });
                mDialog.show();

                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
    }

    private void refreshData(){
        mPlanListAdapter.setData(preparePlanItemData());
    }

    private List<PlanItem> preparePlanItemData() {

        try {
            mPlans = db.findAll(PlanItem.class);
            return mPlans;
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
            Controller.launchActivity(this, SettingsActivity.class);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.btnNewPlan})
    public void onNewPlanClick(View v){
        if(mPlans == null||mPlans.size()< Constants.SUGGEST_PLAN_COUNT) {
            isShowDialog = true;
            Controller.launchActivity(MainActivity.this,NewPlanActivity.class);
            return;
        }

        if(isShowDialog){
            if(mDialog == null) {
                mDialog = DialogUtil.createOkCancelDialog(this, "添加新计划", "你的计划已经10个了，你确定要这麽多吗？", "当然了", "也是哈", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.hide();
                        Controller.launchActivity(MainActivity.this, NewPlanActivity.class);
                        isShowDialog = false;
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.hide();
                    }
                });
            }
            mDialog.show();
        }else{
            Controller.launchActivity(MainActivity.this,NewPlanActivity.class);
        }
    }

    @OnClick({R.id.action_settings})
    public void onSettinsClick(View view) {
        Controller.launchActivity(this, SettingsActivity.class);
    }
}
