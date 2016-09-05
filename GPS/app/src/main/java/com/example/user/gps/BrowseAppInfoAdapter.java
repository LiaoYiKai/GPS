package com.example.user.gps;

import android.content.Context;
import android.support.v4.media.session.IMediaControllerCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 2016/9/5.
 */
public class BrowseAppInfoAdapter extends BaseAdapter{
    private List<AppInfo> mlistAppInfo = null;

    LayoutInflater inflater = null;

    public BrowseAppInfoAdapter(Context context, List<AppInfo> apps){
        inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mlistAppInfo = apps;
    }


    @Override
    public int getCount() {
        System.out.println("size"+mlistAppInfo.size());
        return mlistAppInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return mlistAppInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        System.out.println("getView at " + position);
        View view = null;
        ViewHolder holder = null;
        if(convertView == null || convertView.getTag() == null){
            view = inflater.inflate(R.layout.browse_app_item,null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        else {
            view = convertView;
            holder = (ViewHolder) convertView.getTag();
        }
        AppInfo appInfo = (AppInfo)getItem(position);
        holder.appIcon.setImageDrawable(appInfo.getAppIcon());
        holder.tvAppLabel.setText(appInfo.getAppLabel());
        holder.tvPkgName.setText(appInfo.getPkgName());
        return view;
    }
    class ViewHolder{
        ImageView appIcon;
        TextView tvAppLabel;
        TextView tvPkgName;

        public ViewHolder(View view){
            this.appIcon = (ImageView) view.findViewById(R.id.imgApp);
            this.tvAppLabel = (TextView)view.findViewById(R.id.tvAppLabel);
            this.tvPkgName = (TextView)view.findViewById(R.id.tvPkgName);
        }
    }
}
