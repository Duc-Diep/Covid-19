package com.example.covid_19;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.databinding.CountryFragmentBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class CountryFragment extends Fragment {
    CountryFragmentBinding binding;

    public static CountryFragment newInstance() {

        Bundle args = new Bundle();

        CountryFragment fragment = new CountryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.country_fragment, container, false);
        (new DoGetData()).execute();
        return binding.getRoot();
    }

    class DoGetData extends AsyncTask<Void, Void, Void> {
        String result = "";
        List<Country> list;

        @Override
        protected void onPreExecute() {
            binding.progessbar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("https://api.covid19api.com/summary");
                URLConnection connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();
                int byteCharacter;
                while ((byteCharacter = inputStream.read()) != -1) {
                    result += (char) byteCharacter;
                }
                Log.d("Hello", result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            getJson();
            binding.progessbar.setVisibility(View.GONE);
        }

        private void getJson() {
            list = new ArrayList<>();
            String country_name;
            double NewConfirmed, TotalConfirmed, NewDeaths, TotalDeaths, NewRecovered, TotalRecovered;
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONObject global = jsonObject.getJSONObject("Global");
                NewConfirmed = global.getDouble("NewConfirmed");
                TotalConfirmed = global.getDouble("TotalConfirmed");
                NewDeaths = global.getDouble("NewDeaths");
                TotalDeaths = global.getDouble("TotalDeaths");
                NewRecovered = global.getDouble("NewRecovered");
                TotalRecovered = global.getDouble("TotalRecovered");
                list.add(new Country("Global", NewConfirmed, TotalConfirmed, NewDeaths, TotalDeaths, NewRecovered, TotalRecovered));
                //add Countries
                JSONArray jsonArray = jsonObject.getJSONArray("Countries");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    country_name = object.getString("Country");
                    NewConfirmed = object.getDouble("NewConfirmed");
                    TotalConfirmed = object.getDouble("TotalConfirmed");
                    NewDeaths = object.getDouble("NewDeaths");
                    TotalDeaths = object.getDouble("TotalDeaths");
                    NewRecovered = object.getDouble("NewRecovered");
                    TotalRecovered = object.getDouble("TotalRecovered");
                    list.add(new Country(country_name, NewConfirmed, TotalConfirmed, NewDeaths, TotalDeaths, NewRecovered, TotalRecovered));
                }
                JSONObject object = jsonArray.getJSONObject(0);
                String date = object.getString("Date");
                binding.dateUpdate.setText("Data is updated at "+ date);
                //cho VN len
                Country temp = new Country();
                int index = 0;
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getName().contains("Viet Nam")){
                        temp = list.get(i);
                        index = i;
                        break;
                    }
                }
                list.remove(index);
                list.add(1,temp);
                //add adapter
                CountryAdapter adapter = new CountryAdapter(list, getContext());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                binding.listCountry.setAdapter(adapter);
                binding.listCountry.setLayoutManager(layoutManager);
                Toast.makeText(getContext(),"Data is updated at "+date,Toast.LENGTH_LONG);
                binding.edtSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        List<Country> templist =new ArrayList<>();
                        for(int i=0;i<list.size();i++){
                            if(list.get(i).getName().toLowerCase().contains(s.toString().toLowerCase())){
                                templist.add(list.get(i));
                            }
                        }
                        CountryAdapter adapter = new CountryAdapter(templist, getContext());
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                        binding.listCountry.setAdapter(adapter);
                        binding.listCountry.setLayoutManager(layoutManager);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
