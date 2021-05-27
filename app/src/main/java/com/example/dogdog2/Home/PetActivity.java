package com.example.dogdog2.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dogdog2.ImageLoader;
import com.example.dogdog2.LoveAndPetsApi;
import com.example.dogdog2.Profile.Org_Adapter;
import com.example.dogdog2.R;
import com.example.dogdog2.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetActivity extends AppCompatActivity {

    public static final String PET_ID_ARG = "pet_id";
    private  long petId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        petId = getIntent().getLongExtra(PET_ID_ARG, 0);

        TextView petName = findViewById(R.id.nameProfilePet);
        TextView petDescr = findViewById(R.id.descrPetProfile);
        ImageView petImg = findViewById(R.id.imgProfilePet);


        LoveAndPetsApi api = RetrofitUtils.getApi();
        Call<Friend> call = api.getFriend(petId);

        call.enqueue(new Callback<Friend>() {
                 @Override
                 public void onResponse(Call<Friend> call, Response<Friend> response) {
                     Friend pet = response.body();
                     petName.setText(pet.getName());
                     petDescr.setText(pet.getDescription());

                     new ImageLoader(petImg, pet.getFriend_image_url()).execute();
                 }
                 @Override
                 public void onFailure(Call<Friend> call, Throwable t) {
                     Toast.makeText(PetActivity.this, "Error", Toast.LENGTH_SHORT).show();
                 }
             }
        );



    }
}