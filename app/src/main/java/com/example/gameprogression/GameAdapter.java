package com.example.gameprogression;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {
    private List<Game> games;
    private final Context context;
    private final OnGameClickListener listener;

    public interface OnGameClickListener {
        void onGameClick(Game game);
    }

    public GameAdapter(List<Game> games, Context context, OnGameClickListener listener) {
        this.games = games;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        Game game = games.get(position);

        holder.gameTitle.setText(game.getName());
        holder.gameCover.setImageResource(game.getCover());

        // Update favorite icon
        holder.favoriteIcon.setImageResource(
                game.isFavorite() ? R.drawable.ic_favorite : R.drawable.ic_favorite_border
        );

        // Toggle favorite status
        holder.favoriteIcon.setOnClickListener(v -> {
            game.setFavorite(!game.isFavorite());
            notifyItemChanged(position);
        });
        // Handle click on game item
        holder.itemView.setOnClickListener(v -> listener.onGameClick(game));
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public void updateGames(List<Game> updatedGames) {
        this.games = updatedGames;
        notifyDataSetChanged();
    }

    public static class GameViewHolder extends RecyclerView.ViewHolder {
        TextView gameTitle;
        ImageView gameCover;
        ImageView favoriteIcon;
        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            gameTitle = itemView.findViewById(R.id.gameTitle);
            gameCover = itemView.findViewById(R.id.gameCover);
            favoriteIcon = itemView.findViewById(R.id.favoriteIcon);
        }
    }
}
