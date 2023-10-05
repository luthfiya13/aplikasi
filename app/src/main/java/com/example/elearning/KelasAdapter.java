package com.example.elearning;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class KelasAdapter extends RecyclerView.Adapter<KelasAdapter.ViewHolder>{

    private Context context;
    private List<Kelas> kelasList;

    private OnItemClickListener itemClickListener;

    public KelasAdapter(Context context, List<Kelas> kelasList, OnItemClickListener itemClickListener) {
        this.context = context;
        this.kelasList = kelasList;
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Kelas kelas);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_kelas_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Kelas kelas = kelasList.get(position);

        holder.kelasName.setText(kelas.getNamaKelas());
        holder.kelasDescription.setText(kelas.getDeskripsiKelas());

        // Tambahkan OnClickListener ke item RecyclerView
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Menyiapkan intent untuk membuka AmapelActivity
                Intent intent = new Intent(context, AdetailkelasActivity.class);

                // Mengirim data kelas ke AdminTambahKelasActivity
                intent.putExtra("namaKelas", kelas.getNamaKelas());
                intent.putExtra("deskripsiKelas", kelas.getDeskripsiKelas());
                intent.putExtra("id", kelas.getId());

                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return kelasList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView kelasName, kelasDescription;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            kelasName = itemView.findViewById(R.id.namac);
            kelasDescription = itemView.findViewById(R.id.deskc);
            cardView = itemView.findViewById(R.id.cardviewk);
        }
    }
}
