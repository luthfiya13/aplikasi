package com.example.elearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminTambahBabActivity extends AppCompatActivity {

    private Spinner kelasspinner;
    private Spinner mapelspinner; // Tambahkan Spinner untuk mapel
    private DatabaseReference kelasReference;
    private DatabaseReference mapelReference;

    private EditText judulBabEditText, deskripsiBabEditText;
    private CardView simpanButton;
    private DatabaseReference babReference; // Referensi ke node "Bab" di Firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tambah_bab);


        kelasspinner = findViewById(R.id.kelasspinner);
        mapelspinner = findViewById(R.id.mapelspinner);
        judulBabEditText = findViewById(R.id.namab);
        deskripsiBabEditText = findViewById(R.id.deskb);
        simpanButton = findViewById(R.id.simpanb);

        // Inisialisasi Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        kelasReference = database.getReference("Kelas");
        mapelReference = database.getReference("Mapel"); // Sesuaikan dengan nama referensi Mapel Anda
        babReference = database.getReference("Bab");
        // Ambil data dari Firebase
        ambilDataKelas();
        ambilDataMapel();


        // Menyimpan data Bab ke Firebase ketika tombol "Simpan" diklik
        simpanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahBabKeFirebase();
            }
        });

    }

    // Pada metode tambahBabKeFirebase
    private void tambahBabKeFirebase() {
        // Mendapatkan data dari komponen UI
        String judulBab = judulBabEditText.getText().toString();
        String deskripsiBab = deskripsiBabEditText.getText().toString();
        String mapelTerpilih = mapelspinner.getSelectedItem().toString();
        String kelasTerpilih = kelasspinner.getSelectedItem().toString();

        if (!judulBab.isEmpty() && !deskripsiBab.isEmpty()) {
            // Buat objek Kelas
            Bab bab = new Bab(judulBab, deskripsiBab, mapelTerpilih, kelasTerpilih);

            String key = babReference.push().getKey(); // Mendapatkan ID otomatis
            bab.setidBab(key); // Set ID pada objek Bab
            babReference.child(key).setValue(bab);


            // Reset input fields
            judulBabEditText.setText("");
            deskripsiBabEditText.setText("");

            Toast.makeText(this, "bab berhasil ditambahkan", Toast.LENGTH_SHORT).show();

            finish();
        } else {
            Toast.makeText(this, "Harap isi semua field", Toast.LENGTH_SHORT).show();
        }
    }

    private void ambilDataKelas() {
        kelasReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> namaKelasList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Kelas kelas = snapshot.getValue(Kelas.class);
                    if (kelas != null) {
                        namaKelasList.add(kelas.getNamaKelas());
                    }
                }

                // Isi Spinner dengan data namaKelas
                ArrayAdapter<String> adapter = new ArrayAdapter<>(AdminTambahBabActivity.this, android.R.layout.simple_spinner_item, namaKelasList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                kelasspinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle kesalahan jika terjadi
            }
        });
    }

    private void ambilDataMapel() {
        mapelReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> namaMapelList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Mapel mapel = snapshot.getValue(Mapel.class);
                    if (mapel != null) {
                        namaMapelList.add(mapel.getNamaMapel());
                    }
                }

                // Isi Spinner dengan data namaMapel
                ArrayAdapter<String> adapter = new ArrayAdapter<>(AdminTambahBabActivity.this, android.R.layout.simple_spinner_item, namaMapelList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mapelspinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle kesalahan jika terjadi
            }
        });
    }
}