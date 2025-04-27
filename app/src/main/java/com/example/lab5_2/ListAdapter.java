package com.example.lab5_2;

import android.content.Context;
import android.speech.tts.TextToSpeech;
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
    private String[] calo;

    public ListAdapter (Context context, String[] names, int[] images, String[] calo){
        this.context = context;
        this.names = names;
        this.images = images;
        this.calo = calo;
    }

    public int getCount(){
        return names.length;
    }

    public Object getItem(int position){
        return names[position];
    }

    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView ==  null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, null);
        }
        ImageView imageView = convertView.findViewById(R.id.fruit);
        TextView nameTextView = convertView.findViewById(R.id.fruitName);
        TextView caloTextView = convertView.findViewById(R.id.calo);

        imageView.setImageResource(images[position]);
        nameTextView.setText(names[position]);
        caloTextView.setText(calo[position]);

        return convertView;
    }
}
