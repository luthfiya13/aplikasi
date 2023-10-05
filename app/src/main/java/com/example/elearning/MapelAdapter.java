package com.example.elearning;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MapelAdapter extends RecyclerView.Adapter<MapelAdapter.ViewHolder> {

    private Context context;
    private List<Mapel> mapelList;

    private OnItemClickListener itemClickListener;

    public MapelAdapter(Context context, List<Mapel> mapelList, OnItemClickListener itemClickListener) {
        this.context = context;
        this.mapelList = mapelList;
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Mapel mapel);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mapel_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mapel mapel = mapelList.get(position);

        holder.mapelName.setText(mapel.getNamaMapel());
        holder.mapelDescription.setText(mapel.getDeskripsiMapel());

        // Tambahkan OnClickListener ke item RecyclerView
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Menyiapkan intent untuk membuka DetailMapelActivity (sesuaikan nama aktivitasnya)
                Intent intent = new Intent(context, AdetailmapelActivity.class);

                // Mengirim data mapel ke DetailMapelActivity
                intent.putExtra("namaMapel", mapel.getNamaMapel());
                intent.putExtra("deskripsiMapel", mapel.getDeskripsiMapel());
                intent.putExtra("idMapel", mapel.getIdMapel());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mapelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mapelName, mapelDescription;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mapelName = itemView.findViewById(R.id.namas); // Ganti ID sesuai dengan layout Anda
            mapelDescription = itemView.findViewById(R.id.desks); // Ganti ID sesuai dengan layout Anda
            cardView = itemView.findViewById(R.id.cardviewm);
            // Ganti ID sesuai dengan layout Anda
        }
    }
}
