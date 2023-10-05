package com.example.elearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserActivity extends AppCompatActivity {

    TextView date, namauser;
    de.hdodenhof.circleimageview.CircleImageView profil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        profil = findViewById(R.id.profil);
        namauser = findViewById(R.id.namauser);

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, ProfileActivity.class);
                intent.putExtra("name", getIntent().getStringExtra("name")); // Mengirim nama pengguna ke ProfileActivity
                intent.putExtra("username", getIntent().getStringExtra("username")); // Mengirim nama pengguna ke ProfileActivity
                intent.putExtra("email", getIntent().getStringExtra("email")); // Mengirim nama pengguna ke ProfileActivity
                intent.putExtra("password", getIntent().getStringExtra("password")); // Mengirim nama pengguna ke ProfileActivity
                startActivity(intent);
            }
        });

        // Menampilkan nama pengguna di TextView
        String namaUser = getIntent().getStringExtra("name");
        namauser.setText(namaUser);

        Calendar calendar = Calendar.getInstance();
        String currtentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        TextView textView = findViewById(R.id.date);
        textView.setText(currtentDate);


    }
}