package fr.android.tennistrackerv2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.android.tennistrackerv2.Model.Statistique;
import fr.android.tennistrackerv2.R;

public class StatistiqueAdapter extends RecyclerView.Adapter<StatistiqueAdapter.StatistiqueViewHolder> {

    private Context context;
    private List<Statistique> statistiques;


    public StatistiqueAdapter(Context context, List<Statistique> statistiques) {
        this.context = context;
        this.statistiques = statistiques;
    }

    @NonNull
    @Override
    public StatistiqueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.image_item_history, parent, false);
        return new StatistiqueViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StatistiqueViewHolder holder, int position) {
        Statistique statistique = statistiques.get(position);
        holder.nameClub.setText(statistique.getMatch().getClub1().getName() + "VS" + statistique.getMatch().getClub2().getName());
        holder.dateMatch.setText(statistique.getMatch().getDateMatch());

    }

    @Override
    public int getItemCount() {
        return statistiques.size();
    }

    public class StatistiqueViewHolder extends RecyclerView.ViewHolder {
        TextView nameClub;
        TextView dateMatch;
        public StatistiqueViewHolder(@NonNull View itemView) {
            super(itemView);
            nameClub = itemView.findViewById(R.id.nameClub);
            dateMatch = itemView.findViewById(R.id.dateMatch);
        }
    }


}
