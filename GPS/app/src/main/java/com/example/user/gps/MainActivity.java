package com.example.user.gps;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.suitebuilder.annotation.MediumTest;
import android.text.InputType;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{


    private EditText HR;
    private EditText MM;
    private EditText SS;
    private Button Show;
    private Button start;
    long x , y , z ;


    private ListView listview = null;
    private ArrayList<AppInfo> mlistAppInfo;

    int boxNumber = 0;
    CheckBox chk;
    CheckBox clickchk;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HR = (EditText)findViewById(R.id.hr);
        MM = (EditText)findViewById(R.id.mm);
        SS = (EditText)findViewById(R.id.ss);
        Show = (Button)findViewById(R.id.Show);
        start = (Button)findViewById(R.id.Start);
        Show.setOnClickListener(this);
        start.setOnClickListener(this);


        listview = (ListView) findViewById(R.id.listviewApp);
        mlistAppInfo = new ArrayList<AppInfo>();
        queryAppInfo(); //查詢所有應用程式
        BrowseAppInfoAdapter browseAppInfoAdapter = new BrowseAppInfoAdapter(this,mlistAppInfo);
        listview.setAdapter(browseAppInfoAdapter);
        listview.setOnItemClickListener(this);


    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.Show:
                Toast toast = Toast.makeText(MainActivity.this, x+"時"+y+"分"+z+"秒", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.Start:
                int h,m,s,total;
                h = Integer.parseInt(HR.getText().toString());
                m = Integer.parseInt(MM.getText().toString());
                s = Integer.parseInt(SS.getText().toString());
                total = h*60*60*1000 + m*60*1000 + s*1000;
                HR.setCursorVisible(false);
                MM.setCursorVisible(false);
                SS.setCursorVisible(false);
                HR.setInputType(InputType.TYPE_NULL);
                MM.setInputType(InputType.TYPE_NULL);
                SS.setInputType(InputType.TYPE_NULL);
                new CountDownTimer(total, 1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                        x = millisUntilFinished / 60 /60 / 1000;
                        y = (millisUntilFinished -( x * 1000*60*60))/60/1000;
                        z = (millisUntilFinished - (x * 1000 * 60 * 60) - (y * 60 * 1000))/1000;
                    }

                    @Override
                    public void onFinish() {
                        HR.setCursorVisible(true);
                        MM.setCursorVisible(true);
                        SS.setCursorVisible(true);
                        Toast toast = Toast.makeText(MainActivity.this, "時間到！！！", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }.start();
                break;
            }
        }

   @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
        Intent intent = mlistAppInfo.get(position).getIntent();
        startActivity(intent);
    }
    public void queryAppInfo(){
        PackageManager pm = this.getPackageManager();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN,null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(mainIntent,PackageManager.MATCH_DEFAULT_ONLY);
        Collections.sort(resolveInfos,new ResolveInfo.DisplayNameComparator(pm));
            if(mlistAppInfo != null){
            mlistAppInfo.clear();
            for(ResolveInfo reInfo : resolveInfos){
                boxNumber = boxNumber + 1;
                chk.setId(boxNumber);
                String activityName = reInfo.activityInfo.name;
                String pkgName = reInfo.activityInfo.packageName;
                String appLabel = (String) reInfo.loadLabel(pm);
                Drawable icon = reInfo.loadIcon(pm);

                Intent launhIntent = new Intent();
                launhIntent.setComponent(new ComponentName(pkgName,activityName));

                AppInfo appInfo = new AppInfo();
                appInfo.setAppLabel(appLabel);
                appInfo.setPkgName(pkgName);
                appInfo.setAppIcon(icon);
                appInfo.setIntent(launhIntent);
                mlistAppInfo.add(appInfo);
                System.out.println(appLabel + "activityName---" + activityName + "pkgName---"+ pkgName);
            }
        }
    }
    public void checkBox(View view){

    }
}

