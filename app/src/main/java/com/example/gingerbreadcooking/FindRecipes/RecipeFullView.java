package com.example.gingerbreadcooking.FindRecipes;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gingerbreadcooking.R;

public class RecipeFullView extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allrecipe_fullview);
        TextView title = findViewById(R.id.RecipeFullViewTitle);
        TextView summary = findViewById(R.id.RecipeFullViewSummary);
        TextView ingredients = findViewById(R.id.RecipeFullViewIngredients);
        TextView instructions = findViewById(R.id.RecipeFullViewInstructions);
        TextView credits = findViewById(R.id.RecipeFullViewCreditText);

        String titleText = "No Title";
        String summaryText = "No Summary";
        String ingredientsText = "No Ingredients";
        String instructionsText = "No Instructions";
        String creditText = "No Credit Text";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            titleText = extras.getString("title");
            summaryText = extras.getString("summary");
            ingredientsText = extras.getString("ingredients");
            instructionsText = extras.getString("instructions");
            creditText = extras.getString("creditText");
        }

        title.setText(titleText);
        summary.setText(summaryText);
        ingredients.setText(ingredientsText);
        instructions.setText(instructionsText);
        credits.setText(creditText);

    }
}
