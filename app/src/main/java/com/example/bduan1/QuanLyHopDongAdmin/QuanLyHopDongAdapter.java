package com.example.bduan1.QuanLyHopDongAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bduan1.R;
import com.example.bduan1.chucnangAdminQuanLyPhongTo.QuanLyPhongTroAdapter;
import com.example.bduan1.chucnangAdminQuanLyPhongTo.QuanLyPhongTroModel;

import java.util.List;

public class QuanLyHopDongAdapter extends RecyclerView.Adapter<QuanLyHopDongAdapter.ViewHolder> {
    Context context;
    List<QuanLyPhongTroModel> list;

    public QuanLyHopDongAdapter(Context context, List<QuanLyPhongTroModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hop_dong_admin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuanLyPhongTroModel model = list.get(position);
        holder.tvTenPhong.setText(model.getTenPhong());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenPhong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenPhong = itemView.findViewById(R.id.tvtenphong_HopDong);

        }
    }
}
