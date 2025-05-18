package com.example.lab1;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ListView listView;
    DatabaseHelper databaseHelper;
    Student[] studentArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list); // Sử dụng activity_list thay vì list_item

        listView = findViewById(R.id.list);
        databaseHelper = new DatabaseHelper(this);

        loadStudentList();

        if (studentArray == null || studentArray.length == 0) {
            Log.e("ListActivity", "Danh sách sinh viên rỗng hoặc không tải được");
            Toast.makeText(this, "Không có sinh viên nào trong danh sách", Toast.LENGTH_SHORT).show();
            studentArray = new Student[0];
        }

        StudentAdapter adapter = new StudentAdapter(this, studentArray);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = studentArray[position];
                if (student == null) {
                    Log.e("ListActivity", "Student tại vị trí " + position + " là null");
                    return;
                }
                Intent intent = new Intent(ListActivity.this, InfoActivity.class);
                intent.putExtra("TEN", student.getTen());
                intent.putExtra("MSSV", student.getMssv());
                intent.putExtra("LOP", student.getLop());
                intent.putExtra("SDT", student.getSdt());
                intent.putExtra("NAM", student.getNam());
                intent.putExtra("CHUYENNGANH", student.getChuyenNganh());
                intent.putExtra("PTBT", student.getPtbt());
                if (student.getImage() != null) {
                    byte[] imageBytes = bitmapToByteArray(student.getImage());
                    intent.putExtra("IMAGE", imageBytes);
                }
                startActivity(intent);
            }
        });
    }

    private void loadStudentList() {
        ArrayList<Student> studentList = new ArrayList<>();
        Cursor cursor = databaseHelper.getAllStudents();
        if (cursor == null) {
            Log.e("ListActivity", "Cursor trả về từ getAllStudents() là null");
            return;
        }

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex("id");
            int tenIndex = cursor.getColumnIndex("ten");
            int mssvIndex = cursor.getColumnIndex("mssv");
            int lopIndex = cursor.getColumnIndex("lop");
            int sdtIndex = cursor.getColumnIndex("sdt");
            int namIndex = cursor.getColumnIndex("nam");
            int chuyenNganhIndex = cursor.getColumnIndex("chuyennganh");
            int ptbtIndex = cursor.getColumnIndex("ptbt");
            int imageIndex = cursor.getColumnIndex("image");

            if (idIndex == -1 || tenIndex == -1 || mssvIndex == -1 || lopIndex == -1 ||
                    sdtIndex == -1 || namIndex == -1 || chuyenNganhIndex == -1 ||
                    ptbtIndex == -1 || imageIndex == -1) {
                Log.e("ListActivity", "Một hoặc nhiều cột không tồn tại trong bảng students");
                cursor.close();
                return;
            }

            do {
                int id = cursor.getInt(idIndex);
                String ten = cursor.getString(tenIndex);
                String mssv = cursor.getString(mssvIndex);
                String lop = cursor.getString(lopIndex);
                String sdt = cursor.getString(sdtIndex);
                int nam = cursor.getInt(namIndex);
                String chuyenNganh = cursor.getString(chuyenNganhIndex);
                String ptbt = cursor.getString(ptbtIndex);
                byte[] imageBytes = cursor.getBlob(imageIndex);
                Bitmap image = null;
                if (imageBytes != null) {
                    try {
                        image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                        if (image == null) {
                            Log.e("ListActivity", "Không thể giải mã imageBytes thành Bitmap");
                        }
                    } catch (Exception e) {
                        Log.e("ListActivity", "Lỗi giải mã Bitmap: " + e.getMessage());
                    }
                }
                Student student = new Student(id, ten, mssv, lop, sdt, nam, chuyenNganh, ptbt, image);
                studentList.add(student);
            } while (cursor.moveToNext());
        } else {
            Log.d("ListActivity", "Không có dữ liệu trong bảng students");
        }
        cursor.close();

        studentArray = studentList.toArray(new Student[0]);
    }

    private byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}