package com.example.przychodnia;

import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterPong extends RecyclerView.Adapter<AdapterPong.PongHolder>{
    public ArrayList<Pong> pongs=new ArrayList<>();
    @NonNull
    @Override
    public PongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_pacjentow, parent,false);
        return new PongHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PongHolder holder, int position) {
        Pong pong=pongs.get(position);
        holder.textImie.setText(pong.imie);
        holder.textGatunek.setText(pong.gatunek);
        holder.textDataUr.setText(pong.dataUrodzenia);
        holder.textDataPrzyj.setText(pong.dataPrzyjecia);
        holder.textWaga.setText(pong.waga);
        holder.textwlasciciel.setText(pong.wlasciciel);
        holder.textNumerTel.setText(pong.numerTelefonu);
        holder.textLekarz.setText(pong.lekarzPrzyjmujacy);

    }



    @Override
    public int getItemCount() {
        return pongs.size();
    }
    public class PongHolder extends RecyclerView.ViewHolder{
        public TextView textImie;
        public TextView textGatunek;
        public TextView textDataUr;
        public TextView textDataPrzyj;
        public TextView textWaga;
        public TextView textwlasciciel;
        public TextView textNumerTel;
        public TextView textLekarz;

        public PongHolder(@NonNull View itemView){
            super(itemView);
            textImie=(TextView)itemView.findViewById(R.id.textImie);
            textGatunek=(TextView) itemView.findViewById(R.id.textGatunek);
            textDataUr=(TextView) itemView.findViewById(R.id.textDataUr);
            textDataPrzyj=(TextView) itemView.findViewById(R.id.textDataPrzyj);
            textWaga=(TextView) itemView.findViewById(R.id.textWaga);
            textwlasciciel=(TextView) itemView.findViewById(R.id.textwlasciciel);
            textNumerTel=(TextView) itemView.findViewById(R.id.textNumerTel);
            textLekarz=(TextView) itemView.findViewById(R.id.textLekarz);
        }
    }
}
