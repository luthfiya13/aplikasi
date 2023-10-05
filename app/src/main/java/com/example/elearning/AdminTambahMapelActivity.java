package com.example.elearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminTambahMapelActivity extends AppCompatActivity {

    private EditText namaMapelEditText;
    private EditText deskripsiMapelEditText;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tambah_mapel);

        // Inisialisasi elemen UI
        namaMapelEditText = findViewById(R.id.namas);
        deskripsiMapelEditText = findViewById(R.id.desks);

        CardView simpanButton = findViewById(R.id.simpans);

        // Inisialisasi Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Mapel");

        // Mengatur OnClickListener untuk tombol Simpan
        simpanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahkanMapel();
            }
        });
    }

    private void tambahkanMapel() {
        String namaMapel = namaMapelEditText.getText().toString().trim();
        String deskripsiMapel = deskripsiMapelEditText.getText().toString().trim();

        if (!namaMapel.isEmpty() && !deskripsiMapel.isEmpty()) {
            // Buat objek Mapel
            Mapel mapel = new Mapel(namaMapel, deskripsiMapel);

            // Push data ke Firebase Realtime Database
            String key = databaseReference.push().getKey(); // Mendapatkan ID otomatis
            mapel.setIdMapel(key); // Set ID pada objek Mapel
            databaseReference.child(key).setValue(mapel); // Menyimpan objek ke Firebase

            // Reset input fields
            namaMapelEditText.setText("");
            deskripsiMapelEditText.setText("");

            // Tambahkan pemberitahuan sukses
            Toast.makeText(this, "Mapel berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            finish();
            // Anda dapat menggunakan Toast atau sejenisnya di sini
        } else {
            // Tambahkan pemberitahuan bahwa semua kolom harus diisi
            Toast.makeText(this, "Harap isi semua field", Toast.LENGTH_SHORT).show();
            // Anda dapat menggunakan Toast atau sejenisnya di sini
        }
    }
}