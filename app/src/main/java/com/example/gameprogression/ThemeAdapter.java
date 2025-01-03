package com.example.gameprogression;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ThemeViewHolder> {

    private final Context context;
    private final List<String> themes;

    public ThemeAdapter(Context context, List<String> themes) {
        this.context = context;
        this.themes = themes;
    }

    @NonNull
    @Override
    public ThemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_theme, parent, false);
        return new ThemeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThemeViewHolder holder, int position) {
        String theme = themes.get(position);
        holder.themeName.setText(theme);

        switch (theme) {
            case "Achievements":
                holder.themeIcon.setImageResource(R.drawable.achievement_icon);
                break;
            case "Progress":
                holder.themeIcon.setImageResource(R.drawable.progress_icon);
                break;
            case "Diary":
                holder.themeIcon.setImageResource(R.drawable.diary_icon);
                break;
            case "Other":
                holder.themeIcon.setImageResource(R.drawable.placeholder);
                break;
            default:
                holder.themeIcon.setImageResource(R.drawable.placeholder);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, NotesListActivity.class);
            intent.putExtra("theme", theme);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return themes.size();
    }

    public static class ThemeViewHolder extends RecyclerView.ViewHolder {
        TextView themeName;
        ImageView themeIcon;

        public ThemeViewHolder(@NonNull View itemView) {
            super(itemView);
            themeName = itemView.findViewById(R.id.themeName);
            themeIcon = itemView.findViewById(R.id.themeIcon);
        }
    }
}
