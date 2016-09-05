package com.example.user.gps;

import android.content.Intent;
import android.graphics.drawable.Drawable;

/**
 * Created by User on 2016/9/5.
 */
public class AppInfo {
    private  String appLabel; //應用程式的標籤
    private Drawable appIcon; //應用程式的圖像
    private Intent intent;  //要啟動的Intent
    private String pkgName; //應用程式的包名

    public AppInfo(){}

    public String getAppLabel(){
        return appLabel;
    }
    public void setAppLabel(String appName){
        this.appLabel = appName;
    }
    public Drawable getAppIcon(){
        return appIcon;
    }
    public void setAppIcon(Drawable appIcon){
        this.appIcon = appIcon;
    }
    public Intent getIntent(){
        return intent;
    }
    public void setIntent(Intent intent){
        this.intent = intent;
    }
    public String getPkgName(){
        return pkgName;
    }
    public void setPkgName(String pkgName){
        this.pkgName = pkgName;
    }
}
