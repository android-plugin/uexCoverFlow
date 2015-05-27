package org.zywx.wbpalmstar.plugin.uexcoverflow;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.zywx.wbpalmstar.base.ACEImageLoader;

public class CoverFlowAdapter extends BaseAdapter {

    private String[] mData =null;
    private Context mContext;

    public CoverFlowAdapter(Context context,String[] data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.length;
    }

    @Override
    public Object getItem(int pos) {
        return mData[pos];
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = (ImageView) convertView;
        if (imageView == null) {
            imageView=new ImageView(mContext);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            imageView.setLayoutParams(layoutParams);
        }
        ACEImageLoader.getInstance().displayImage(imageView, mData[position]);
        return imageView;
    }
}
