package fr.android.tennistrackerv2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.android.tennistrackerv2.MatchBottomBarActivity;
import fr.android.tennistrackerv2.Model.Match;
import fr.android.tennistrackerv2.Model.Statistique;
import fr.android.tennistrackerv2.R;
import fr.android.tennistrackerv2.StatsMatchBottomBarActivity;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {

    private Context context;
    private List<Match> matches;


    public MatchAdapter(Context context, List<Match> matches) {
        this.context = context;
        this.matches = matches;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.image_item_history, parent, false);
        return new MatchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        Match match = matches.get(position);
        holder.nameClub.setText(match.getClub1().getName() +" VS " + match.getClub2().getName());
        holder.dateMatch.setText(match.getDateMatch());

    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class MatchViewHolder extends RecyclerView.ViewHolder {
        TextView nameClub;
        TextView dateMatch;
        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            nameClub = itemView.findViewById(R.id.nameClub);
            dateMatch = itemView.findViewById(R.id.dateMatch);
            itemView.setOnClickListener(view -> {
                Match match = matches.get(getAdapterPosition());
                Intent statsMatch = new Intent(context, StatsMatchBottomBarActivity.class);
                statsMatch.putExtra("club1Img", match.getClub1().getImgUrl());
                statsMatch.putExtra("club1Name", match.getClub1().getName());
                statsMatch.putExtra("club1Score", match.getClub1().getStatistique().getScore());
                statsMatch.putExtra("club1Tir", match.getClub1().getStatistique().getTir());
                statsMatch.putExtra("club1TirCadre", match.getClub1().getStatistique().getTirCadre());
                statsMatch.putExtra("club1Passe", match.getClub1().getStatistique().getPasse());
                statsMatch.putExtra("club1Faute", match.getClub1().getStatistique().getFautes());
                statsMatch.putExtra("club1CartonJ", match.getClub1().getStatistique().getCartonJaune());
                statsMatch.putExtra("club1CartonR", match.getClub1().getStatistique().getCartonRouge());
                statsMatch.putExtra("club1HorsJeu", match.getClub1().getStatistique().getHorsJeu());

                statsMatch.putExtra("club2Img", match.getClub2().getImgUrl());
                statsMatch.putExtra("club2Name", match.getClub2().getName());
                statsMatch.putExtra("club2Score", match.getClub2().getStatistique().getScore());
                statsMatch.putExtra("club2Tir", match.getClub2().getStatistique().getTir());
                statsMatch.putExtra("club2TirCadre", match.getClub2().getStatistique().getTirCadre());
                statsMatch.putExtra("club2Passe", match.getClub2().getStatistique().getPasse());
                statsMatch.putExtra("club2Faute", match.getClub2().getStatistique().getFautes());
                statsMatch.putExtra("club2CartonJ", match.getClub2().getStatistique().getCartonJaune());
                statsMatch.putExtra("club2CartonR", match.getClub2().getStatistique().getCartonRouge());
                statsMatch.putExtra("club2HorsJeu", match.getClub2().getStatistique().getHorsJeu());

                context.startActivity(statsMatch);
            });
        }
    }


}
