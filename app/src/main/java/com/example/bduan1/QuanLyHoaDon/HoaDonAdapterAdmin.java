package com.example.bduan1.QuanLyHoaDon;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bduan1.R;
import com.example.bduan1.chucnangAdminQuanLyPhongTo.QuanLyPhongTroModel;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class HoaDonAdapterAdmin extends RecyclerView.Adapter<HoaDonAdapterAdmin.ViewHolder> {
    Context context;
    List<QuanLyPhongTroModel> list;

    private FirebaseFirestore db;

    public HoaDonAdapterAdmin(Context context, List<QuanLyPhongTroModel> list) {
        this.context = context;
        this.list = list;
        db = FirebaseFirestore.getInstance(); // Khởi tạo Firestore
    }

    @NonNull
    @Override
    public HoaDonAdapterAdmin.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_xuat_hoa_don, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonAdapterAdmin.ViewHolder holder, int position) {
        QuanLyPhongTroModel phongTro = list.get(position);
        holder.tvTenPhong.setText(phongTro.getTenPhong());

        holder.itemView.setOnClickListener(v -> {
            // Kiểm tra xem hóa đơn có tồn tại hay không, nếu có thì mở màn hình cập nhật
            checkAndUpdateInvoice(phongTro);
        });
    }

    private void checkAndUpdateInvoice(QuanLyPhongTroModel phongTro) {
        // Lấy thông tin phòng để tìm hóa đơn đã tồn tại
        String tenPhong = phongTro.getTenPhong();
        DocumentReference invoiceRef = db.collection("hoadon").document(tenPhong); // Sử dụng tên phòng làm ID hóa đơn

        invoiceRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                // Nếu hóa đơn tồn tại, gửi dữ liệu vào màn hình ThemChiTietHoaDonAdmin để cập nhật
                HoaDon existingInvoice = documentSnapshot.toObject(HoaDon.class);
                Intent intent = new Intent(context, ThemChiTietHoaDonAdmin.class);
                intent.putExtra("isUpdate", true); // Đánh dấu đây là chế độ cập nhật
                intent.putExtra("tenPhong", existingInvoice.getTenPhong());
                intent.putExtra("soDien", existingInvoice.getSoDien());
                intent.putExtra("giaPhong", existingInvoice.getGiaPhong());
                intent.putExtra("tienDien", existingInvoice.getTienDien());
                intent.putExtra("tienNuoc", existingInvoice.getTienNuoc());
                intent.putExtra("tienDichVu", existingInvoice.getTienDichVu());
                intent.putExtra("soNguoi", existingInvoice.getSoNguoi());
                context.startActivity(intent);
            } else {
                // Nếu hóa đơn không tồn tại, mở dialog để tạo mới
                hienthidailog(phongTro, new HoaDon());
            }
        }).addOnFailureListener(e -> {
            // Xử lý nếu không tìm thấy hóa đơn
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenPhong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenPhong = itemView.findViewById(R.id.tvtenphong_hoadon);
        }
    }

    private void hienthidailog(QuanLyPhongTroModel phongTro, HoaDon hoaDon) {
        // Tạo LayoutInflater để lấy view từ XML
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.item_xuat_hoa_don_them, null);

        // Tìm các EditText trong dialog
        EditText edtTenPhong = dialogView.findViewById(R.id.edt_phongHoaDon);
        EditText edtSoDien = dialogView.findViewById(R.id.edt_soDien_hoadon);
        EditText edtGiaPhong = dialogView.findViewById(R.id.edt_giaPhong_hoadon);
        EditText edtTienDien = dialogView.findViewById(R.id.edt_tienDien_hoadon);
        EditText edtTienNuoc = dialogView.findViewById(R.id.edt_tienNuoc_hoadon);
        EditText edtTienDichVu = dialogView.findViewById(R.id.edt_dichVu_hoadon);
        EditText edtSoNguoi = dialogView.findViewById(R.id.edt_soNguoi_hoadon);
        Button btnGenerateInvoice = dialogView.findViewById(R.id.btn_generate_invoice);


        // Gán dữ liệu từ phongTro vào các EditText
        edtTenPhong.setText(phongTro.getTenPhong());
        edtSoDien.setText(hoaDon.getSoDien());
        edtGiaPhong.setText(hoaDon.getGiaPhong());
        edtTienDien.setText(hoaDon.getTienDien());
        edtTienNuoc.setText(hoaDon.getTienNuoc());
        edtTienDichVu.setText(hoaDon.getTienDichVu());
        edtSoNguoi.setText(hoaDon.getSoNguoi());



        // Tạo và hiển thị AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);
        builder.setCancelable(true);

        // Tạo Dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        btnGenerateInvoice.setOnClickListener(v -> {
            // Lấy dữ liệu từ các EditText
            String tenPhong = edtTenPhong.getText().toString();
            String soDien = edtSoDien.getText().toString();
            String giaPhong = edtGiaPhong.getText().toString();
            String tienDien = edtTienDien.getText().toString();
            String tienNuoc = edtTienNuoc.getText().toString();
            String tienDichVu = edtTienDichVu.getText().toString();
            String soNguoi = edtSoNguoi.getText().toString();

            // Tính tổng tiền
            double tienDienDouble = Double.parseDouble(tienDien.isEmpty() ? "0" : tienDien);
            double tienNuocDouble = Double.parseDouble(tienNuoc.isEmpty() ? "0" : tienNuoc);
            double tienDichVuDouble = Double.parseDouble(tienDichVu.isEmpty() ? "0" : tienDichVu);
            double giaPhongDouble = Double.parseDouble(giaPhong.isEmpty() ? "0" : giaPhong);

            double tongTien = tienDienDouble + tienNuocDouble + tienDichVuDouble + giaPhongDouble;

            // Tạo Intent chuyển đến ThemChiTietHoaDonAdmin
            Intent intent = new Intent(context, ThemChiTietHoaDonAdmin.class);
            intent.putExtra("isUpdate", false); // Chế độ thêm mới
            intent.putExtra("tenPhong", tenPhong);
            intent.putExtra("soDien", soDien);
            intent.putExtra("giaPhong", giaPhong);
            intent.putExtra("tienDien", tienDien);
            intent.putExtra("tienNuoc", tienNuoc);
            intent.putExtra("tienDichVu", tienDichVu);
            intent.putExtra("soNguoi", soNguoi);
            intent.putExtra("tongTien", tongTien); // Gửi tổng tiền

            // Mở Activity ThemChiTietHoaDonAdmin
            context.startActivity(intent);

            // Đóng dialog sau khi hoàn thành
            dialog.dismiss();
        });
    }

}
