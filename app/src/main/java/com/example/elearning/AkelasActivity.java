package com.example.elearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AkelasActivity extends AppCompatActivity {

    private FloatingActionButton btnTambahKelas;
    private RecyclerView recyclerView;
    private KelasAdapter adapter;
    private List<Kelas> kelasList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akelas);

        // Inisialisasi tombol
        btnTambahKelas = findViewById(R.id.btnTambahKelas);

        // Menambahkan OnClickListener pada tombol "Tambah Kelas"
        btnTambahKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Memulai aktivitas TambahKelasActivity ketika tombol diklik
                Intent intent = new Intent(AkelasActivity.this, AdminTambahKelasActivity.class);
                startActivity(intent);
            }
        });
        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inisialisasi List dan Adapter
        kelasList = new ArrayList<>();
        adapter = new KelasAdapter(this, kelasList, new KelasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Kelas kelas) {
                // Implementasi tindakan saat item diklik (jika diperlukan)
            }
        });
        recyclerView.setAdapter(adapter);

        // Inisialisasi DatabaseReference untuk Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Kelas");

        // Mengambil data dari Firebase Realtime Database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                kelasList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Kelas kelas = dataSnapshot.getValue(Kelas.class);
                    kelasList.add(kelas);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Penanganan kesalahan jika terjadi
            }
        });
    }
}