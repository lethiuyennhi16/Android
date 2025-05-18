package com.example.lab1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StudentAdapter extends BaseAdapter {
    private Context context;
    private Student[] students;

    public StudentAdapter(Context context, Student[] students) {
        this.context = context;
        this.students = students != null ? students : new Student[0];
    }

    @Override
    public int getCount() {
        return students.length;
    }

    @Override
    public Object getItem(int position) {
        return (position >= 0 && position < students.length) ? students[position] : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, null); // Sử dụng R.layout.item thay vì list_item
        }

        ImageView imageView = convertView.findViewById(R.id.item_image);
        TextView nameTextView = convertView.findViewById(R.id.item_name);

        if (imageView == null) {
            Log.e("StudentAdapter", "Không tìm thấy ImageView với ID item_image trong item.xml");
            return convertView;
        }
        if (nameTextView == null) {
            Log.e("StudentAdapter", "Không tìm thấy TextView với ID item_name trong item.xml");
            return convertView;
        }

        Student student = students[position];
        if (student == null) {
            Log.e("StudentAdapter", "Student tại vị trí " + position + " là null");
            nameTextView.setText("Không có dữ liệu");
            imageView.setImageResource(android.R.drawable.ic_menu_gallery);
            return convertView;
        }

        nameTextView.setText(student.getTen() != null ? student.getTen() : "Không có tên");
        if (student.getImage() != null) {
            imageView.setImageBitmap(student.getImage());
        } else {
            imageView.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        return convertView;
    }
}