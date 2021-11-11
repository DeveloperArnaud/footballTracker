package fr.android.tennistrackerv2.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.android.tennistrackerv2.ClubStatsDetailsActivity;
import fr.android.tennistrackerv2.Model.ClubStats;
import fr.android.tennistrackerv2.R;

public class ClubStatsAdapter extends RecyclerView.Adapter<ClubStatsAdapter.ClubStatsViewHolder> {

    private Context context;
    private List<ClubStats> clubStats;
    Activity activity;


    public ClubStatsAdapter(Context context, List<ClubStats> clubStats) {
        this.context = context;
        this.clubStats = clubStats;
    }

    @NonNull
    @Override
    public ClubStatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.image_item_club_stats, parent, false);
        return new ClubStatsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ClubStatsViewHolder holder, int position) {
        ClubStats clubStat = clubStats.get(position);
        holder.nameClub.setText(clubStat.getName());
        Picasso.with(context)
                .load(clubStat.getImgUrl())
                .fit()
                .centerCrop()
                .into(holder.img_stats_clb);

    }

    @Override
    public int getItemCount() {
        return clubStats.size();
    }

    public class ClubStatsViewHolder extends RecyclerView.ViewHolder {
        TextView nameClub;
        ImageView img_stats_clb;
        public ClubStatsViewHolder(@NonNull View itemView) {
            super(itemView);
            nameClub = itemView.findViewById(R.id.nameClubStats);
            img_stats_clb = itemView.findViewById(R.id.img_stats_clb);
            itemView.setOnClickListener(view -> {
                ClubStats clubStat = clubStats.get(getAdapterPosition());
                Intent i = new Intent(itemView.getContext(), ClubStatsDetailsActivity.class);
                i.putExtra("clubStat", clubStat);
                itemView.getContext().startActivity(i);
                activity = (Activity) itemView.getContext();
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            });
        }
    }


}
