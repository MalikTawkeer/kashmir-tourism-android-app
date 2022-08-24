package com.example.kashmirtouristplaces;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kashmirtouristplaces.modalClasses.FeedbackAndSuggestionsModal;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FeedbackActivity extends AppCompatActivity{
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("FEEDBACK ANd SUGGESTIONS");
    
    EditText feedBackField,suggestionsField;
    Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        // tool bar coding
        androidx.appcompat.widget.Toolbar mToolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.feedback_toolbar_id);
        mToolbar.setTitle("About Developer");//setting text on toolbar
        mToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24); //fetching icon of back btn
        mToolbar.setNavigationOnClickListener(v -> finish()); // litner for back button on toolbar
        initViews();

                    // SUBMIT BUTTON ONCLICK LISTENER
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedbackText = feedBackField.getText().toString();
                String suggestionsText = suggestionsField.getText().toString();
                if (feedbackText.equals(NULL) || feedbackText.equals("") && suggestionsText.equals(NULL) || suggestionsText.equals(""))
                {
                    Toast.makeText(FeedbackActivity.this,"Text Feilds Can't be Empety !!",Toast.LENGTH_LONG).show();
                }else if (feedbackText.length()<=12 && suggestionsText.length()<=12)
                {
                    Toast.makeText(FeedbackActivity.this, "Add at least 3 to 5 words", Toast.LENGTH_SHORT).show();
                }
                else {
                   // Date cDate = new Date();
                    //String dateText = new SimpleDateFormat("yyyy-MM-dd").format(cDate);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                    String currentDateandTime = sdf.format(new Date());

                    FeedbackAndSuggestionsModal feedbackAndSuggestionsModal = new FeedbackAndSuggestionsModal(feedbackText, suggestionsText,currentDateandTime);
                    databaseReference.child(currentDateandTime).setValue(feedbackAndSuggestionsModal)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(FeedbackActivity.this, "Thanks For Feedback and Suggestion", Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(FeedbackActivity.this, "ERROR!! Occured while submitting feedback and suggestions", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }

    private void initViews() {
        feedBackField = findViewById(R.id.feedbackactivity_feedback_et);
        suggestionsField = findViewById(R.id.feedbackactivity_Suggestions_et);
        submitButton = findViewById(R.id.feedbackactivity_submit_btn);
    }
}