package com.example.a01_19478061_huynhtuananh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a01_19478061_huynhtuananh.adapter.Adapter;
import com.example.a01_19478061_huynhtuananh.model.Book;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListSachMainActivity extends AppCompatActivity {

    private ListView listView;
    private Button btnSave,btnUpd;
    private EditText txtTenSach;
    private EditText txtTenTacGia;
    private DatabaseReference mDatabase;
    private int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sach_main);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        txtTenSach = findViewById(R.id.txtTenSachMain);
        txtTenTacGia = findViewById(R.id.txtTenTacGiaMain);
        btnSave = findViewById(R.id.btnSave);
        btnUpd = findViewById(R.id.btnUpdate);
        btnUpd.setEnabled(false);
        listView = findViewById(R.id.listView);
        layDuLieu();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edtTenSach = txtTenSach.getText().toString().trim();
                String edtTenTacGia = txtTenTacGia.getText().toString().trim();
                layDuLieu();
                ThemDuLieu(i, edtTenSach, edtTenTacGia);
                ++i;
            }
        });
        Intent intent = getIntent();
        if(intent != null) {
            String id = intent.getStringExtra("id");
            String name = intent.getStringExtra("tenSach");
            String author = intent.getStringExtra("tenTacGia");
//            Toast.makeText(this, ""+money, Toast.LENGTH_SHORT).show();

            txtTenSach.setText(name);
            txtTenTacGia.setText(author);
            btnUpd.setEnabled(true);

            btnUpd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String _name  = txtTenSach.getText().toString().trim();
                    String _author = txtTenTacGia.getText().toString().trim();

                    ThemDuLieu(Integer.parseInt(id), _name, _author);
                    layDuLieu();
                }
            });


            if(intent.getStringExtra("del") != null) {
                mDatabase.child("book").child(id).removeValue();
                layDuLieu();
            }
        }
        else{
            Toast.makeText(this, "Là null", Toast.LENGTH_SHORT).show();
        }
    }


    public void layDuLieu(){
        List<Book> list = new ArrayList<>();
        mDatabase.child("book").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot sn : snapshot.getChildren()) {
                    Book book = sn.getValue(Book.class);
                    list.add(book);
                }

//                i = list.get(list.size()-1).getId() + 1;
                loadDuLieu(list);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void ThemDuLieu(int id, String ten, String tacGia) {
        Book book = new Book(id, ten, tacGia);

        mDatabase.child("book").child(book.getId() + "").setValue(book);
    }
    public void loadDuLieu(List<Book> list){
        txtTenSach.setText("");
        txtTenTacGia.setText("");
        Adapter adapter = new Adapter(this, R.layout.item, list);
        listView.setAdapter(adapter);
    }
}