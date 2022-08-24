package com.example.kashmirtouristplaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kashmirtouristplaces.modalClasses.AddUnknownPlacesModal;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPlace extends AppCompatActivity {
    Button AddPlace;
    EditText PlaceName,PlaceDistrict,PlaceURL;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference("UNKNOWN PLACES");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        // tool bar coding
        androidx.appcompat.widget.Toolbar mToolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.add_place_toolbar);
        mToolbar.setTitle("Add Place");//setting text on toolbar
        mToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24); //fetching icon of back btn
        mToolbar.setNavigationOnClickListener(v -> finish()); // litner for back button on toolbar
        initViews();
       AddPlace.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String Pname=PlaceName.getText().toString();
               String Pdistrict=PlaceDistrict.getText().toString();
               String Purl=PlaceURL.getText().toString();
               if (Pname.equals(""))
               {
                   PlaceName.setError("Enter Place Nmae!!");
                   PlaceName.isFocused();
               }
               else{
                   AddUnknownPlacesModal addUnknownPlacesModal=new AddUnknownPlacesModal(Pname,Pdistrict,Purl);
                   databaseReference.push().setValue(addUnknownPlacesModal)
                           .addOnSuccessListener(new OnSuccessListener<Void>() {
                               @Override
                               public void onSuccess(Void aVoid) {

                                   Toast.makeText(AddPlace.this, "Place Added Successfully , Developer Will Add After Review", Toast.LENGTH_LONG).show();
                               }
                           })
                           .addOnFailureListener(new OnFailureListener() {
                               @Override
                               public void onFailure(@NonNull Exception e) {
                                   Toast.makeText(AddPlace.this, "Oops! ERROR Occured While adding place", Toast.LENGTH_LONG).show();
                               }
                           });

               }
           }
       });
    }

    private void initViews() {
        AddPlace=findViewById(R.id.addplace_addplace_btn);
        PlaceName=findViewById(R.id.addplace_placename_et);
        PlaceDistrict=findViewById(R.id.addplace_districtname_et);
        PlaceURL=findViewById(R.id.addplace_enterurl_et);

    }
}