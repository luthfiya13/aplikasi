package com.example.elearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AdetailmapelActivity extends AppCompatActivity {

    private EditText nama, desk;
    private TextView idmapel;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adetailmapel);

        // Inisialisasi elemen UI
        nama = findViewById(R.id.namads);
        desk = findViewById(R.id.deskds);
        idmapel = findViewById(R.id.idmapel);

        CardView deleteButton = findViewById(R.id.delete);
        CardView simpanButton = findViewById(R.id.simpanc);

        // Inisialisasi Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Mapel");

        // Mengambil ID mapel yang akan dihapus/diperbarui dari intent
        Intent intent = getIntent();
        final String idMapelToDelete = intent.getStringExtra("idMapel");
        final String idMapelToUpdate = intent.getStringExtra("idMapel");

        // Menampilkan data nama kelas, deskripsi kelas, dan id kelas di EditText
        String namaMapel = intent.getStringExtra("namaMapel");
        String deskripsiMapel = intent.getStringExtra("deskripsiMapel");
        idmapel.setText(idMapelToDelete); // Untuk data yang akan dihapus
        idmapel.setText(idMapelToUpdate); // Untuk data yang akan diperbarui
        nama.setText(namaMapel);
        desk.setText(deskripsiMapel);

        // Mengatur OnClickListener untuk cardview "delete"
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Melakukan penghapusan data dari Firebase Realtime Database
                databaseReference.child(idMapelToDelete).removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Data idMapel berhasil dihapus
                                Toast.makeText(AdetailmapelActivity.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                                // Kembali ke aktivitas sebelumnya atau tampilkan pesan berhasil dihapus
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AdetailmapelActivity.this, "Gagal menghapus data idMapel", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
            }
        });

        // Mengatur OnClickListener untuk tombol "Simpan"
        simpanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mendapatkan data yang diperbarui dari EditText
                String updatedNamaMapel = nama.getText().toString().trim();
                String updatedDeskripsiMapel = desk.getText().toString().trim();

                // Periksa apakah kedua field sudah diisi
                if (!updatedNamaMapel.isEmpty() && !updatedDeskripsiMapel.isEmpty()) {
                    // Membuat objek Mapel yang diperbarui
                    Mapel updatedMapel = new Mapel(updatedNamaMapel, updatedDeskripsiMapel);

                    // Memperbarui data di Firebase Realtime Database
                    databaseReference.child(idMapelToUpdate).setValue(updatedMapel)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(AdetailmapelActivity.this, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show();
                                    // Kembali ke aktivitas sebelumnya atau tampilkan pesan berhasil diperbarui
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AdetailmapelActivity.this, "Gagal memperbarui data", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    // Tampilkan pesan bahwa semua field harus diisi
                    Toast.makeText(AdetailmapelActivity.this, "Harap isi semua field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}