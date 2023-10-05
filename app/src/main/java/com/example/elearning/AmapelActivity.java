package com.example.elearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AmapelActivity extends AppCompatActivity {

    private FloatingActionButton btnTambahMapel;
    private RecyclerView recyclerView;
    private MapelAdapter adapter;
    private List<Mapel> mapelList;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amapel);

        btnTambahMapel = findViewById(R.id.btnTambahMapel);

        // Menambahkan OnClickListener pada tombol "Tambah Kelas"
        btnTambahMapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Memulai aktivitas TambahKelasActivity ketika tombol diklik
                Intent intent = new Intent(AmapelActivity.this, AdminTambahMapelActivity.class);
                startActivity(intent);
            }
        });

        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inisialisasi List dan Adapter
        mapelList = new ArrayList<>();
        adapter = new MapelAdapter(this, mapelList, new MapelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Mapel mapel) {
                // Implementasi tindakan saat item diklik (jika diperlukan)
            }
        });
        recyclerView.setAdapter(adapter);

        // Inisialisasi DatabaseReference untuk Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Mapel");

        // Mengambil data dari Firebase Realtime Database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mapelList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Mapel mapel = dataSnapshot.getValue(Mapel.class);
                    mapelList.add(mapel);
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
//
//
//
//        // Mendapatkan informasi nama kelas dari intent yang dikirimkan
//        Intent intent = getIntent();
//        String kelasNama = intent.getStringExtra("namaKelas");
//
//        // Menampilkan nama kelas di TextView dengan ID TVnamakelas
//        TextView tvNamaKelas = findViewById(R.id.TVnamakelas);
//        tvNamaKelas.setText(kelasNama);
//    }
//}