package com.example.kashmirtouristplaces;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kashmirtouristplaces.homeButtonBar.TopPlacesMainActivity;
import com.example.kashmirtouristplaces.homeButtonBar.TripPlannerMainActivity;
import com.example.kashmirtouristplaces.modalClasses.Model1;
import com.example.kashmirtouristplaces.recyclerViewAdapters.AllPlacesRecAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Context context;
    Button retryBtn;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference();
    NavigationView nav;
    ActionBarDrawerToggle toogle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    RecyclerView recyclerView;
    AllPlacesRecAdapter allPlacesRecAdapter;
    ImageView noConnectionIV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intitilizeViews();
        checkInternetConn();
        retryBtn.setOnClickListener(v -> {
            checkInternetConn();
            if(!isConnected())
            {
                noConnectionIV.setVisibility(View.VISIBLE);
                retryBtn.setVisibility(View.VISIBLE);
            }
            else {
                noConnectionIV.setVisibility(View.GONE);
                retryBtn.setVisibility(View.GONE);
            }
        });

        setSupportActionBar(toolbar);
        // SETTING REC VIEW
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Firebase recycler view code here
        FirebaseRecyclerOptions<Model1> options  =
                new FirebaseRecyclerOptions.Builder<Model1>()
                .setQuery(databaseReference.child("places"),Model1.class)
                .build();
        allPlacesRecAdapter=new AllPlacesRecAdapter(options,this);
        recyclerView.setAdapter(allPlacesRecAdapter);
        //NOTE:add strings into strings.xml naamely open and close
        toogle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               // Fragment tempFragment = null;
                switch(item.getItemId())
                {
                    case R.id.allplaces:

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.favorite:
                        Intent intent = new Intent(MainActivity.this,FavoritePlaces.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.topplaces:
                        Intent intent1=new Intent(MainActivity.this,TopPlacesMainActivity.class);
                        startActivity(intent1);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.tripplanner_menu:
                        Intent intent2=new Intent(MainActivity.this,TripPlannerMainActivity.class);
                        startActivity(intent2);
                        //drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.addplaces:
                        Intent intent4=new Intent(MainActivity.this,AddPlace.class);
                        startActivity(intent4);
                        break;
                    case R.id.aboutdeveloper_menu_id:
                        Intent intent3=new Intent(MainActivity.this,DeveloperDesk.class);
                        startActivity(intent3);
                        //drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.feedback_menu_id:
                        Intent intent5 =new Intent(MainActivity.this,FeedbackActivity.class);
                        startActivity(intent5);
                        break;
                    case R.id.aboutapp_menu_id:
                        Intent intent6=new Intent(MainActivity.this,AboutAppActivity.class);
                        startActivity(intent6);
                        break;
                }
               // getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,tempFragment).commit();
                return true;
            }
        });

    }

    private void checkInternetConn() {
        if (!isConnected())
        {
            noConnectionIV.setVisibility(View.VISIBLE);///////////////
            retryBtn.setVisibility(View.VISIBLE);
        }
        else
        {
            Toast.makeText(MainActivity.this,"Loading........",Toast.LENGTH_LONG).show();
        }

    }


    private void intitilizeViews() {
        toolbar =findViewById(R.id.toolbar);
        drawerLayout=findViewById(R.id.drawer);
        nav=findViewById(R.id.navmenu);
        recyclerView = findViewById(R.id.allplaces_rec_view);
        noConnectionIV = (ImageView) findViewById(R.id.no_internet_imageview_mainActvy);
        retryBtn = (Button) findViewById(R.id.main_actvy_retry_btn);
        /*
        topPlacesButton = findViewById(R.id.top_places_imgbtn);
        hotelButton = findViewById(R.id.restraunts_imgbtn);
        tentButton = findViewById(R.id.tent_imgbtn);
        tripPlannerButton = findViewById(R.id.tripplanner_imgbtn);

         */
    }
    //method for checking internet connection
    private boolean isConnected()
    {
        ConnectivityManager connectivityManager=(ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
    @Override
    protected void onStart() {
        super.onStart();
        allPlacesRecAdapter.startListening();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
            allPlacesRecAdapter.stopListening();
    }
    
}