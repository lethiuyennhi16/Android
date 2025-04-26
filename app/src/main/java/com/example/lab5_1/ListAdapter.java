package com.example.lab5_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
    private Context context;
    private String[] names;
    private int[] images;
    private String[] versions;

    public ListAdapter(Context context, String[] names, int[] images, String[] versions) {
        this.context = context;
        this.names = names;
        this.images = images;
        this.versions = versions;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.single_list_item, null);
        }
        ImageView imageView = convertView.findViewById(R.id.appIcon);
        TextView nameTextView = convertView.findViewById(R.id.name);
        TextView versionTextView = convertView.findViewById(R.id.version);

        imageView.setImageResource(images[position]);
        nameTextView.setText(names[position]);
        versionTextView.setText(versions[position]);

        return convertView;
    }
}
