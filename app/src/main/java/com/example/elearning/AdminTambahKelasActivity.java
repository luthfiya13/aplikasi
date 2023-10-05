package com.example.elearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdminTambahKelasActivity extends AppCompatActivity {

    private EditText namaKelasEditText;
    private EditText deskripsiKelasEditText;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tambah_kelas);


        // Inisialisasi elemen UI
        namaKelasEditText = findViewById(R.id.namac);
        deskripsiKelasEditText = findViewById(R.id.deskc);
        CardView simpanButton = findViewById(R.id.simpanc);

        // Inisialisasi Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Kelas");

        // Mengatur OnClickListener untuk tombol Simpan
        simpanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahkanKelas();
            }
        });
    }

    private void tambahkanKelas() {
        String namaKelas = namaKelasEditText.getText().toString().trim();
        String deskripsiKelas = deskripsiKelasEditText.getText().toString().trim();

        if (!namaKelas.isEmpty() && !deskripsiKelas.isEmpty()) {
            // Buat objek Kelas
            Kelas kelas = new Kelas(namaKelas, deskripsiKelas);

            // Push data ke Firebase Realtime Database
            String key = databaseReference.push().getKey(); // Mendapatkan ID otomatis
            kelas.setId(key); // Set ID pada objek Kelas
            databaseReference.child(key).setValue(kelas); // Menyimpan objek ke Firebase

            // Reset input fields
            namaKelasEditText.setText("");
            deskripsiKelasEditText.setText("");

            Toast.makeText(this, "Kelas berhasil ditambahkan", Toast.LENGTH_SHORT).show();

            finish();
        } else {
            Toast.makeText(this, "Harap isi semua field", Toast.LENGTH_SHORT).show();
        }
    }
}