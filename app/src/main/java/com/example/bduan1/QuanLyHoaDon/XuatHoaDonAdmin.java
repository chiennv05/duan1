    package com.example.bduan1.QuanLyHoaDon;

    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;

    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;
    import androidx.fragment.app.Fragment;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import com.example.bduan1.R;
    import com.example.bduan1.chucnangAdminQuanLyPhongTo.QuanLyPhongTroAdapter;
    import com.example.bduan1.chucnangAdminQuanLyPhongTo.QuanLyPhongTroModel;
    import com.google.firebase.FirebaseApp;
    import com.google.firebase.firestore.DocumentSnapshot;
    import com.google.firebase.firestore.FirebaseFirestore;

    import java.util.ArrayList;
    import java.util.List;

    public class XuatHoaDonAdmin extends Fragment {
        FirebaseFirestore db;
        List<QuanLyPhongTroModel> list;
        RecyclerView recyclerView;
        HoaDonAdapterAdmin adapter;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(com.example.bduan1.R.layout.fragment_xuat_hoa_don_admin, container, false);
            FirebaseApp.initializeApp(requireContext());
            db = FirebaseFirestore.getInstance();
            recyclerView = view.findViewById(R.id.rvHoaDon);
            list = new ArrayList<>();
            adapter = new HoaDonAdapterAdmin(requireContext(), list);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            recyclerView.setAdapter(adapter);
            FirebaseApp.initializeApp(requireContext());
            OpenDanhSach();

            return view;
        }


        public void OpenDanhSach() {
            db.collection("PhongTro")
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        list.clear();
                        for (DocumentSnapshot document : queryDocumentSnapshots) {
                            String tenPhong = document.getString("tenPhong");
                            QuanLyPhongTroModel model = new QuanLyPhongTroModel();
                            model.setTenPhong(tenPhong);
                            list.add(model);
                        }
                        adapter.notifyDataSetChanged();
                    });

        }
    }
