package com.example.covid_19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    List<Country> list;
    Context context;

    public CountryAdapter(List<Country> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.country_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, int position) {
        Country country = list.get(position);
        DecimalFormat fm = new DecimalFormat("#####");

        holder.tvName.setText(country.getName());
        holder.tvNewConfirmed.setText(String.valueOf(fm.format(country.getNewConfirmed())));
        holder.tvTotalConfirmed.setText(String.valueOf(fm.format(country.getTotalConfirmed())));
        holder.tvNewDeaths.setText(String.valueOf(fm.format(country.getNewDeaths())));
        holder.tvTotalDeaths.setText(String.valueOf(fm.format(country.getTotalDeaths())));
        holder.tvNewRecovered.setText(String.valueOf(fm.format(country.getNewRecovered())));
        holder.tvTotalRecovered.setText(String.valueOf(fm.format(country.getTotalRecovered())));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvNewConfirmed,tvTotalConfirmed,tvNewDeaths,tvTotalDeaths,tvNewRecovered,tvTotalRecovered;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.country_name);
            tvNewConfirmed = itemView.findViewById(R.id.NewConfirmed);
            tvTotalConfirmed = itemView.findViewById(R.id.TotalConfirmed);
            tvNewDeaths =itemView.findViewById(R.id.NewDeaths);
            tvTotalDeaths =itemView.findViewById(R.id.TotalDeaths);
            tvNewRecovered =itemView.findViewById(R.id.NewRecovered);
            tvTotalRecovered =itemView.findViewById(R.id.TotalRecovered);

        }
    }
}
