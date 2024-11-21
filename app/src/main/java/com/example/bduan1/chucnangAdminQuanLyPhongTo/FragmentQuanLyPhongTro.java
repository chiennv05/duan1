package com.example.bduan1.chucnangAdminQuanLyPhongTo;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bduan1.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FragmentQuanLyPhongTro extends Fragment {
    FirebaseFirestore db;
    List<QuanLyPhongTroModel> list;
    RecyclerView recyclerView;
    QuanLyPhongTroAdapter adapter;
    Button btnadd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_phong_tro, container, false);
        recyclerView = view.findViewById(R.id.rvDanhSachPhong);
        list = new ArrayList<>();
        adapter = new QuanLyPhongTroAdapter(requireContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
        FirebaseApp.initializeApp(requireContext());
        db = FirebaseFirestore.getInstance();
        fetchDataFromFirestore();
        btnadd = view.findViewById(R.id.btnThemPhong);
        btnadd.setOnClickListener(view1 -> openAddDialog());
        requireActivity().getWindow().setStatusBarColor(
                ContextCompat.getColor(requireContext(), R.color.your_primary_dark_color)
        );

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        ImageView btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        });
        return view;
    }

    private void openAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View dialogView = inflater.inflate(R.layout.item_themphongtro, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        EditText edtTenPhong = dialogView.findViewById(R.id.edtTenPhong_Them);
        Spinner spinnerTrangThai = dialogView.findViewById(R.id.spinnerTrangThaiThem);
        EditText edtTienPhong = dialogView.findViewById(R.id.edtTienPhong_them);
        EditText edtTienDien = dialogView.findViewById(R.id.edtTienDien_them);
        EditText edtTienNuoc = dialogView.findViewById(R.id.edtTienNuoc_them);
        EditText edtPhuPhi = dialogView.findViewById(R.id.edtPhuPhi_them);
        Button btnLuuPhong = dialogView.findViewById(R.id.btnLuuPhong_them);
        // Các trường ngày bắt đầu và ngày kết thúc
        EditText edtNgayBatDau = dialogView.findViewById(R.id.edtNgayBatDau_them);
        EditText edtNgayKetThuc = dialogView.findViewById(R.id.edtNgayKetThuc_them);

        // Sử dụng DatePickerDialog để chọn ngày
        edtNgayBatDau.setOnClickListener(v -> showDatePickerDialog(edtNgayBatDau));
        edtNgayKetThuc.setOnClickListener(v -> showDatePickerDialog(edtNgayKetThuc));
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.trang_thai_phong,
                android.R.layout.simple_spinner_item
        );
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTrangThai.setAdapter(adapterSpinner);
        btnLuuPhong.setOnClickListener(view -> {
            String tenPhong = edtTenPhong.getText().toString();
            String trangThai = spinnerTrangThai.getSelectedItem().toString();
            String tienPhongText = edtTienPhong.getText().toString();
            String tienDienText = edtTienDien.getText().toString();
            String tienNuocText = edtTienNuoc.getText().toString();
            String phuPhiText = edtPhuPhi.getText().toString();
            String ngayBatDau = edtNgayBatDau.getText().toString();
            String ngayKetThuc = edtNgayKetThuc.getText().toString();
            if (tenPhong.isEmpty() || tienPhongText.isEmpty() || tienDienText.isEmpty() || tienNuocText.isEmpty() || phuPhiText.isEmpty()) {
                edtTenPhong.setError("Không được để trống!");
                return;
            }

            try {
                double tienPhong = Double.parseDouble(tienPhongText);
                double tienDien = Double.parseDouble(tienDienText);
                double tienNuoc = Double.parseDouble(tienNuocText);
                double phuPhi = Double.parseDouble(phuPhiText);

                // Set ngày bắt đầu nếu trạng thái là đã cho thuê, ngày kết thúc là rỗng nếu trạng thái còn trống

                QuanLyPhongTroModel phongTro = new QuanLyPhongTroModel(tenPhong, trangThai, tienPhong, tienDien, tienNuoc, phuPhi, ngayBatDau, ngayKetThuc);
                db.collection("PhongTro").add(phongTro)
                        .addOnSuccessListener(documentReference -> dialog.dismiss())
                        .addOnFailureListener(e -> {
                        });
            } catch (NumberFormatException e) {
                edtTienPhong.setError("Vui lòng nhập số hợp lệ!");
            }
        });

        dialog.show();
    }

    private void showDatePickerDialog(EditText editText) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), (view, year, monthOfYear, dayOfMonth) -> {
            String selectedDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
            editText.setText(selectedDate);
        }, 2024, 0, 1);
        datePickerDialog.show();
    }

    private void fetchDataFromFirestore() {
        db.collection("PhongTro").addSnapshotListener((value, error) -> {
            if (error != null) {
                return;
            }
            if (value != null) {

                for (DocumentChange doc : value.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        QuanLyPhongTroModel phongTro = doc.getDocument().toObject(QuanLyPhongTroModel.class);
                        phongTro.setDocumentId(doc.getDocument().getId()); // Set document ID
                        list.add(phongTro);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}
