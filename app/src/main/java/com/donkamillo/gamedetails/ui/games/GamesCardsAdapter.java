package com.donkamillo.gamedetails.ui.games;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.donkamillo.gamedetails.R;
import com.donkamillo.gamedetails.data.models.GameData;

import java.util.List;

/**
 * Created by DonKamillo on 16.06.2017.
 */

public class GamesCardsAdapter extends RecyclerView.Adapter<GamesCardsAdapter.GamesViewHolder> {

    public interface GameCardsInterface {
        void onItemClick(GameData.Data game);
    }

    private GameCardsInterface gameCardsInterface;
    private List<GameData.Data> games;
    private Context context;

    public GamesCardsAdapter(Context context, GameCardsInterface gameCardsInterface) {
        this.gameCardsInterface = gameCardsInterface;
        this.context = context;
    }

    public void swapItems(List<GameData.Data> games) {
        this.games = games;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (games == null) return 0;
        return games.size();
    }

    @Override
    public void onBindViewHolder(GamesViewHolder holder, int i) {
        GameData.Data game = games.get(i);

        holder.title.setText(game.getName());
        holder.setMainLayoutBackground(i);
        holder.setOnClickListener(game);

    }

    @Override
    public GamesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.games_list_item, viewGroup, false);

        return new GamesViewHolder(itemView);
    }


    public class GamesViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView image;
        LinearLayout mainLayout;

        public GamesViewHolder(View v) {
            super(v);

            mainLayout = (LinearLayout) v.findViewById(R.id.main_layout);
            image = (ImageView) v.findViewById((R.id.image));
            title = (TextView) v.findViewById(R.id.name);

        }

        private void setMainLayoutBackground(int position) {
            if (position % 2 == 1) {
                mainLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.light_gray_bg));
            } else {
                mainLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
            }
        }

        private void setOnClickListener(final GameData.Data game) {
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gameCardsInterface.onItemClick(game);
                }
            });

        }

    }


}

