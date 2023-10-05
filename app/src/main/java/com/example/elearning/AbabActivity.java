package com.example.elearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AbabActivity extends AppCompatActivity {

    private FloatingActionButton btnTambahBab;

    private RecyclerView recyclerView;
    private BabAdapter babAdapter;
    private List<Bab> babList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abab);

        // Inisialisasi RecyclerView dan layout manager
        recyclerView = findViewById(R.id.recyclerViewbab);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inisialisasi list dan adapter
        babList = new ArrayList<>();
        babAdapter = new BabAdapter(this, babList);
        recyclerView.setAdapter(babAdapter);

        // Inisialisasi DatabaseReference untuk Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Bab");

        // Mendapatkan data dari Firebase Realtime Database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                babList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Bab bab = dataSnapshot.getValue(Bab.class);
                    if (bab != null) {
                        babList.add(bab);
                    }
                }
                babAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Menangani kesalahan jika diperlukan
            }
        });

        btnTambahBab = findViewById(R.id.btnTambahBab);
        btnTambahBab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ke = new Intent(AbabActivity.this, AdminTambahBabActivity.class);
                startActivity(ke);
            }
        });
    }
}