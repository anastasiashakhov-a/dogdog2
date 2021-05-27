package com.example.dogdog2.Home;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.view.LayoutInflater;

import androidx.fragment.app.Fragment;

import com.example.dogdog2.LoveAndPetsApi;
import com.example.dogdog2.R;
import com.example.dogdog2.RetrofitUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    int petsPage = 1;
    Button buttonMore;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        ListView listView = view.findViewById(R.id.list_view1);
        buttonMore = view.findViewById(R.id.buttonMore);

        Friends_Adapter adapter = new Friends_Adapter(getContext());
        listView.setAdapter(adapter);

        LoveAndPetsApi api = RetrofitUtils.getApi();
        Call <List<Friends>> call = api.getFriends(1, 10);

        call.enqueue(new Callback<List<Friends>>() {
            @Override
            public void onResponse(Call<List<Friends>> call, Response<List<Friends>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<Friends> pets = response.body();
                adapter.refresh(pets);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
            }
        });



        ListView.OnItemClickListener onClCvFr = new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent launchVforum = new Intent(getActivity(), PetActivity.class);
                launchVforum.putExtra(PetActivity.PET_ID_ARG, id);
                startActivity(launchVforum);
            }
        };

        listView.setOnItemClickListener(onClCvFr);


        View.OnClickListener onClBut = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                petsPage = petsPage+1;
                LoveAndPetsApi api = RetrofitUtils.getApi();
                Call <List<Friends>> call = api.getFriends(petsPage,10);

                call.enqueue(new Callback<List<Friends>>() {
                    @Override
                    public void onResponse(Call<List<Friends>> call, Response<List<Friends>> response) {
                        if (!response.isSuccessful()) {
                            return;
                        }
                        adapter.append(response.body());
                    }
                    @Override
                    public void onFailure(Call call, Throwable t) {
                    }
                });
            }
        };
        buttonMore.setOnClickListener(onClBut);
        return view;


    }


}