package com.example.elearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AdetailkelasActivity extends AppCompatActivity {

    private EditText nama, desk;
    private TextView id;
    private DatabaseReference databaseReference;
    private String idKelasToUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adetailkelas);



// Inisialisasi elemen UI
        nama = findViewById(R.id.namadk);
        desk = findViewById(R.id.deskdk);
        id = findViewById(R.id.id);

        CardView updateButton = findViewById(R.id.updatec);
        CardView deleteButton = findViewById(R.id.deletec);

        // Inisialisasi Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Kelas");

        // Mengambil ID kelas dari intent
        Intent intent = getIntent();
        idKelasToUpdate = intent.getStringExtra("id");
        String namaKelas = intent.getStringExtra("namaKelas");
        String deskripsiKelas = intent.getStringExtra("deskripsiKelas");

        // Menampilkan data nama kelas, deskripsi kelas, dan id kelas di EditText
        id.setText(idKelasToUpdate);
        nama.setText(namaKelas);
        desk.setText(deskripsiKelas);

        // Mengatur OnClickListener untuk tombol "Update"
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateKelas();
            }
        });

        // Mengatur OnClickListener untuk tombol "Delete"
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteKelas();
            }
        });
    }

    private void updateKelas() {
        // Mendapatkan data yang diperbarui dari EditText
        String updatedNamaKelas = nama.getText().toString().trim();
        String updatedDeskripsiKelas = desk.getText().toString().trim();

        // Periksa apakah kedua field sudah diisi
        if (!updatedNamaKelas.isEmpty() && !updatedDeskripsiKelas.isEmpty()) {
            // Membuat objek Kelas yang diperbarui
            Kelas updatedKelas = new Kelas(updatedNamaKelas, updatedDeskripsiKelas);

            // Memperbarui data di Firebase Realtime Database
            databaseReference.child(idKelasToUpdate).setValue(updatedKelas)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AdetailkelasActivity.this, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show();
                            // Kembali ke aktivitas sebelumnya atau tampilkan pesan berhasil diperbarui
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AdetailkelasActivity.this, "Gagal memperbarui data", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            // Tampilkan pesan bahwa semua field harus diisi
            Toast.makeText(AdetailkelasActivity.this, "Harap isi semua field", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteKelas() {
        // Melakukan penghapusan data dari Firebase Realtime Database
        databaseReference.child(idKelasToUpdate).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AdetailkelasActivity.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                        // Kembali ke aktivitas sebelumnya atau tampilkan pesan berhasil dihapus
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdetailkelasActivity.this, "Gagal menghapus data", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}