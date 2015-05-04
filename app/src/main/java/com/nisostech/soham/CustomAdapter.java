package com.nisostech.soham;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by soham on 3/5/15.
 */
public class CustomAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    List textitems;

    public CustomAdapter(Context context, List<String> list) {
        super();
        this.textitems = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        return textitems.size();
    }

    @Override
    public Object getItem(int position) {
        return textitems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return textitems.indexOf(getItem(position));
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.list, null);
            holder.textView = (TextView) convertView.findViewById(R.id.text1);
            holder.imageView = (ImageView) convertView.findViewById(R.id.delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(textitems.get(position).toString());
        holder.imageView.setImageResource(R.drawable.deleteicon);
        holder.textView.setTag(String.valueOf(position));
        return convertView;
    }

    class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
}
