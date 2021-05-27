package com.example.dogdog2.Profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.dogdog2.Home.Friends;
import com.example.dogdog2.LoveAndPetsApi;
import com.example.dogdog2.R;
import com.example.dogdog2.RetrofitUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private Org_Adapter adapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ProfileFragment() {    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);
        GridView gridView = view.findViewById(R.id.gridview1);

        adapter = new Org_Adapter(view.getContext());
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(onClCv1);

        LoveAndPetsApi api = RetrofitUtils.getApi();
        Call<List<Organization>> call = api.getOrganizations();

        call.enqueue(new Callback<List<Organization>>() {
            @Override
            public void onResponse(Call<List<Organization>> call, Response<List<Organization>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<Organization> organizationList = response.body();
                adapter.refresh(organizationList);
            }
            @Override
            public void onFailure(Call call, Throwable t) {
            }
        });

        return view;
    }

    private GridView.OnItemClickListener onClCv1 = new GridView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //мини приют
            if (id==1  ){
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/club183222116"));
                startActivity(browserIntent);
            }
            //право на жизнь
            else if (id==2){
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/pravo_na_zhizn"));
                startActivity(browserIntent);
            }
            //островок надежды
            else if(id==3){
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/public203991152"));
                startActivity(browserIntent);
            }

            //альма
            else {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/alma_vrn"));
                startActivity(browserIntent);
            }

        }
    };
}

