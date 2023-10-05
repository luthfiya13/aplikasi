package com.example.elearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdetailbabActivity extends AppCompatActivity {

    private TextView idTextView, mapelTextView, kelasTextView;
    private EditText judulEditText, deskripsiEditText;

    private Spinner kelasspinner;
    private Spinner mapelspinner; // Tambahkan Spinner untuk mapel
    private DatabaseReference kelasReference;
    private DatabaseReference mapelReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adetailbab);

        CardView deleteButton = findViewById(R.id.deleteb);
        CardView savebutton = findViewById(R.id.updateb);

        // Inisialisasi elemen UI
        idTextView = findViewById(R.id.id);
        judulEditText = findViewById(R.id.namab);
        deskripsiEditText = findViewById(R.id.deskb);

        kelasspinner = findViewById(R.id.kelasspinner);
        mapelspinner = findViewById(R.id.mapelspinner);

        // Inisialisasi Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        kelasReference = database.getReference("Kelas");
        mapelReference = database.getReference("Mapel");

        // Mendapatkan data yang dikirim dari BabAdapter melalui Intent
        Intent intent = getIntent();
        if (intent != null) {
            String idBab = intent.getStringExtra("idBab");
            String judulBab = intent.getStringExtra("judulBab");
            String deskripsiBab = intent.getStringExtra("deskripsiBab");
            String mapel = intent.getStringExtra("mapel");
            String kelas = intent.getStringExtra("kelas");

            // Mengisi data ke elemen UI
            idTextView.setText(idBab);
            judulEditText.setText(judulBab);
            deskripsiEditText.setText(deskripsiBab);

            // Ambil data kelas dan mapel dari Firebase dan isi Spinner
            ambilDataKelasDanMapel(mapel, kelas);
        }

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Mengatur OnClickListener untuk cardview "Hapus"
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mengambil ID Bab yang akan dihapus
                String idBabToDelete = idTextView.getText().toString();

                // Menghapus data dari Firebase Realtime Database
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Bab");
                databaseReference.child(idBabToDelete).removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Data berhasil dihapus
                                Toast.makeText(AdetailbabActivity.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                                // Kembali ke aktivitas sebelumnya atau tampilkan pesan berhasil dihapus
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Gagal menghapus data
                                Toast.makeText(AdetailbabActivity.this, "Gagal menghapus data", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
    private void ambilDataKelasDanMapel(final String mapel, final String kelas) {
        // Ambil data kelas dari Firebase
        kelasReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> namaKelasList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Kelas kls = snapshot.getValue(Kelas.class);
                    if (kls != null) {
                        namaKelasList.add(kls.getNamaKelas());
                    }
                }

                // Isi Spinner dengan data namaKelas
                ArrayAdapter<String> adapter = new ArrayAdapter<>(AdetailbabActivity.this, android.R.layout.simple_spinner_item, namaKelasList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                kelasspinner.setAdapter(adapter);

                // Set item terpilih di Spinner Kelas
                int posKelas = namaKelasList.indexOf(kelas);
                kelasspinner.setSelection(posKelas);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle kesalahan jika terjadi
            }
        });

        // Ambil data mapel dari Firebase
        mapelReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> namaMapelList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Mapel mpl = snapshot.getValue(Mapel.class);
                    if (mpl != null) {
                        namaMapelList.add(mpl.getNamaMapel());
                    }
                }

                // Isi Spinner dengan data namaMapel
                ArrayAdapter<String> adapter = new ArrayAdapter<>(AdetailbabActivity.this, android.R.layout.simple_spinner_item, namaMapelList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mapelspinner.setAdapter(adapter);

                // Set item terpilih di Spinner Mapel
                int posMapel = namaMapelList.indexOf(mapel);
                mapelspinner.setSelection(posMapel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle kesalahan jika terjadi
            }
        });
    }
}