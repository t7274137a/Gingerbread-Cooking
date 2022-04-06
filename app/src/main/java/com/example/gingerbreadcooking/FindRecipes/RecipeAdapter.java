package com.example.gingerbreadcooking.FindRecipes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gingerbreadcooking.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class RecipeAdapter extends FirebaseRecyclerAdapter<Recipe, RecipeAdapter.RecipeViewholder> {

    private RecyclerViewClickListener listener;

    public RecipeAdapter(@NonNull FirebaseRecyclerOptions<Recipe> options, RecyclerViewClickListener listener){
        super(options);
        this.listener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecipeViewholder holder, int position, @NonNull Recipe model)
    {
        holder.title.setText(model.getTitle());
        holder.description.setText(model.getSummary());

    }

    @NonNull
    @Override
    public RecipeViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card_view, parent, false);
        return new RecipeAdapter.RecipeViewholder(view);
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    class RecipeViewholder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView title, description;
        public RecipeViewholder(@NonNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.FindRecipeCardTitle);
            description = itemView.findViewById(R.id.FindRecipeCardDescription);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }
}
