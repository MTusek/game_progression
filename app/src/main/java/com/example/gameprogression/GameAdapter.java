package com.example.gameprogression;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private List<Game> games;
    private final OnGameClickListener listener;

    public interface OnGameClickListener {
        void onGameClick(Game game);
    }

    public GameAdapter(List<Game> games, OnGameClickListener listener) {
        this.games = games;
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
        holder.bind(game, listener);
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    static class GameViewHolder extends RecyclerView.ViewHolder {
        private final ImageView gameCover;
        private final TextView gameTitle;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            gameCover = itemView.findViewById(R.id.gameCover);
            gameTitle = itemView.findViewById(R.id.gameTitle);
        }

        public void bind(Game game, OnGameClickListener listener) {
            gameTitle.setText(game.getName());
            gameCover.setImageResource(game.getCover());
            itemView.setOnClickListener(v -> listener.onGameClick(game));
        }
    }
    public void updateGames(List<Game> updatedGames) {
        this.games = updatedGames;
        notifyDataSetChanged();
    }

}
