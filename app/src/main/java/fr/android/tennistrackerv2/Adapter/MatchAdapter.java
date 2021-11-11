package fr.android.tennistrackerv2.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.android.tennistrackerv2.Model.Match;
import fr.android.tennistrackerv2.R;
import fr.android.tennistrackerv2.StatsMatchBottomBarActivity;
import fr.android.tennistrackerv2.Utils.Utils;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {

    private Context context;
    private List<Match> matches;
    Activity  activity;


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
        holder.nameClub.setText(Utils.clubNameTooLong(match.getClub1().getName()) +" VS " + Utils.clubNameTooLong(match.getClub2().getName()));
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
                statsMatch.putExtra("match", match);
                context.startActivity(statsMatch);
                activity = (Activity) context;
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            });
        }
    }


}
