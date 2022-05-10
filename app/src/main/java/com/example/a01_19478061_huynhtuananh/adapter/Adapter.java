package com.example.a01_19478061_huynhtuananh.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.a01_19478061_huynhtuananh.ListSachMainActivity;
import com.example.a01_19478061_huynhtuananh.R;
import com.example.a01_19478061_huynhtuananh.model.Book;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Context context;
    private int idLayout;
    private List<Book> list;

    public Adapter(Context context, int idLayout, List<Book> list) {
        this.context = context;
        this.idLayout = idLayout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(idLayout, viewGroup, false);
            TextView txtTenSach = view.findViewById(R.id.txtTenSachItem);
            TextView txtTenTacGia = view.findViewById(R.id.txtTacGiaItem);
            ImageButton imgDelete = view.findViewById(R.id.imgRm);
            Book book = list.get(position);
            txtTenSach.setText(book.getTenSach());
            txtTenTacGia.setText(book.getTenTacGia());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ListSachMainActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putString("id", book.getId() + "");
                    bundle.putString("tenSach", book.getTenSach());
                    bundle.putString("tenTacGia", book.getTenTacGia());

                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ListSachMainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id", book.getId() + "");
                    bundle.putString("del", "del");

                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }

        return view;
    }
}
