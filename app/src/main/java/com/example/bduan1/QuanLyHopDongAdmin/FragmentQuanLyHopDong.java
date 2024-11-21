package com.example.bduan1.QuanLyHopDongAdmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bduan1.R;
import com.example.bduan1.chucnangAdminQuanLyPhongTo.QuanLyPhongTroAdapter;
import com.example.bduan1.chucnangAdminQuanLyPhongTo.QuanLyPhongTroModel;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FragmentQuanLyHopDong extends Fragment {
    FirebaseFirestore db;
    List<QuanLyPhongTroModel> list;
    RecyclerView recyclerView;
    QuanLyHopDongAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_hop_dong, container, false);
        recyclerView = view.findViewById(R.id.rcv_hopdong);
        list = new ArrayList<>();
        adapter = new QuanLyHopDongAdapter(requireContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
        FirebaseApp.initializeApp(requireContext());
        db = FirebaseFirestore.getInstance();
        fetchDataFromFirestore();
        return view;

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
