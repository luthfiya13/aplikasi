package com.example.elearning;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.transition.Hold;

import java.util.List;

public class BabAdapter extends RecyclerView.Adapter<BabAdapter.ViewHolder> {

    private Context context;
    private List<Bab> babList;

    private BabAdapter.OnItemClickListener itemClickListener;

    public BabAdapter(Context context, List<Bab> babList) {
        this.context = context;
        this.babList = babList;
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Kelas kelas);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bab_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bab bab = babList.get(position);

        holder.judulTextView.setText(bab.getJudulBab());
        holder.mapelTextView.setText(bab.getMapel());

        // Menangani klik item RecyclerView
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke DetailActivity dan kirim data bab
                Intent intent = new Intent(context, AdetailbabActivity.class);
                intent.putExtra("idBab", bab.getIdBab());
                intent.putExtra("judulBab", bab.getJudulBab());
                intent.putExtra("deskripsiBab", bab.getDeskripsiBab());
                intent.putExtra("mapel", bab.getMapel());
                intent.putExtra("kelas", bab.getKelas());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return babList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView judulTextView;
        TextView mapelTextView;
        CardView cardView;
        Spinner kelasspinner, mapelspinner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judulTextView = itemView.findViewById(R.id.judul);
            mapelTextView = itemView.findViewById(R.id.mapels);
            cardView = itemView.findViewById(R.id.cardviews);
            mapelspinner = itemView.findViewById(R.id.mapelspinner);
            kelasspinner = itemView.findViewById(R.id.kelasspinner);
        }
    }
}
