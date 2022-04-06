package com.example.gingerbreadcooking;

import static com.example.gingerbreadcooking.Utils.Constant.getPremiumStatus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.gingerbreadcooking.Screens.CardActivity;
import com.example.gingerbreadcooking.Screens.PrivacyActivity;
import com.example.gingerbreadcooking.Screens.PrivacyStatementActivity;
import com.example.gingerbreadcooking.Screens.VideoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.logout:
                LoginActivity.logout();
                Intent backToLogin = new Intent(this, LoginActivity.class);
                startActivity(backToLogin);
                return true;
            case R.id.privacy_policy:
                startActivity(new Intent(this, PrivacyActivity.class));
                return true;
            case R.id.privacy_statement:
                startActivity(new Intent(this, PrivacyStatementActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //called when user taps 7-Day Meal Plan Button
    public void showMealPlan(View view){
        Intent intent = new Intent(this, WeekMealPlan.class);
        startActivity(intent);
    }
    public void showShoppingList(View view){
        Intent intent = new Intent(this, ShoppingList.class);
        startActivity(intent);
    }

    public void showCurrentGroceries(View view){
        Intent intent = new Intent(this, CurrentGroceries.class);
        startActivity(intent);
    }
    public void showFavouriteRecipes(View view){
        Intent intent = new Intent(this, FavouriteRecipes.class);
        startActivity(intent);
    }
    public void showForum(View view){
        Intent intent = new Intent(this, Forum.class);
        startActivity(intent);
    }
    public void addRecipe(View view){
        Intent intent = new Intent(this, AddRecipe.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    public void showVideos(View view) {
        if(getPremiumStatus(this).equals("")){
            showAlert(MainActivity.this);
        }
        else{
            startActivity(new Intent(this, VideoActivity.class));
        }

    }
    public  void showAlert(Context context){
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setMessage("To watch video use premium package only for 10$ per month")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(MainActivity.this, CardActivity.class));
                    }
                });

        AlertDialog alert = builder.create();
        alert.setTitle("Alert");
        alert.show();
    }

    public void showPrivacyPolicy(View view) {
        startActivity(new Intent(this, PrivacyActivity.class));
    }

    public void showPrivacyStatement(View view) {
        startActivity(new Intent(this, PrivacyStatementActivity.class));
    }
}
