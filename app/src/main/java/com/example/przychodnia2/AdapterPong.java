package com.example.przychodnia2;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AdapterPong extends RecyclerView.Adapter<AdapterPong.PongHolder>implements Filterable{

    public ArrayList<Pacjent> pacjents =new ArrayList<>();
    public ArrayList<Pacjent> pongsfull=new ArrayList<>();
    public ArrayList<Notatki> not= new ArrayList<>();
    public ListView listView;
    public TextView textImieDialog;
    public TextView textWlascicielDialog;
    public EditText EditWagaDialog;
    public EditText EditNumerDialog;
    public EditText EditNotatkaDialog;
    public Button ButtonEdytujDialog;
    public Button ButtonTak;
    public Button ButtonNie;
    public Button buttonNotatki;
    DatabaseReference reference;

    ArrayList <String> arrayList= new ArrayList<>();
    ArrayList<String> checkList= new ArrayList<>();

    Context context;
    String imie1="";
AdapterPong(Context context, ArrayList<Pacjent> pacjents, ArrayList<Notatki> not, ArrayList<Pacjent> pongsfull){
    this.context=context;
    this.pacjents = pacjents;
    this.not=not;
    this.pongsfull=pongsfull;
}

 

    @NonNull
    @Override
    public PongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_pacjentow, parent,false);
        return new PongHolder(itemView);




    }

    @Override
    public void onBindViewHolder(@NonNull PongHolder holder, int position) {
Notatki notatki=not.get(position);

        Pacjent pacjent = pacjents.get(position);
        holder.textImie.setText(pacjent.imie);
        holder.textGatunek.setText(pacjent.gatunek);
        holder.textDataUr.setText(pacjent.dataUrodzenia);
        holder.textDataPrzyj.setText(pacjent.dataPrzyjecia);
        holder.textWaga.setText(pacjent.waga);
        holder.textwlasciciel.setText(pacjent.wlasciciel);
        holder.textNumerTel.setText(pacjent.numerTelefonu);
        holder.textLekarz.setText(pacjent.lekarzPrzyjmujacy);
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String> (context,android.R.layout.simple_list_item_1,arrayList);

        Dialog dialogN= new Dialog(context);
        dialogN.setContentView(R.layout.notatki_dialog_lista);

        holder.buttonNotatki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.clear();
                listView=(ListView) dialogN.findViewById(R.id.listView);
                listView.setAdapter(arrayAdapter);
                reference= FirebaseDatabase.getInstance().getReference("Zwierzęta i właściciele").child(pacjent.getWlasciciel()).child(pacjent.getImie()).child("Notatki");
                reference.addChildEventListener(new ChildEventListener(){

                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        String imie= pacjent.getImie();
                        String dataWpisu=notatki.getDataWpisu();
                        for(DataSnapshot snaps:snapshot.getChildren()) {

                            if(snaps.getKey().equals("notatka")) {
                                String notatka=snaps.getValue(String.class);

                                String calaNotatka=dataWpisu+": "+ notatka;
                                if(imie.equals(imie1)) {


                                    arrayList.add(calaNotatka);
                                    arrayAdapter.notifyDataSetChanged();
                                }
                                else{


                                    arrayList.clear();
                                    arrayList.add(calaNotatka);
                                    arrayAdapter.notifyDataSetChanged();
                                }
                                imie1= pacjent.getImie();
                            }

                              else{
                                  dataWpisu=snaps.getValue(String.class);
                            }


                        }


                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                dialogN.show();

            }

        });



        holder.fabSendEdytuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.edytuj_view_dialog );

                TextView textImieDialog=dialog.findViewById(R.id.textImieDialog);
                TextView textWlascicielDialog=dialog.findViewById(R.id.textWlascicielDialog);
                EditText EditWagaDialog=dialog.findViewById(R.id.EditWagaDialog);
                EditText EditNumerDialog=dialog.findViewById(R.id.EditNumerDialog);
                EditText EditNotatkaDialog=dialog.findViewById(R.id.EditNotatkaDialog);
                Button ButtonEdytujDialog=dialog.findViewById(R.id.ButtonEdytujDialog);
                textImieDialog.setText(pacjent.imie);
                textWlascicielDialog.setText(pacjent.wlasciciel);
                String dataw=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                ButtonEdytujDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String wlasciciel= pacjent.wlasciciel;
                        String imie= pacjent.imie;
                        String numerTelefonu=EditNumerDialog.getText().toString();
                        String waga=EditWagaDialog.getText().toString();
                        String notatka=EditNotatkaDialog.getText().toString();
                        Map<String,Object> map= new HashMap<>();
                        if (EditNumerDialog.getText().toString().equals("") && EditWagaDialog.getText().toString().equals(""))
                        {


                        }
                       else if(EditWagaDialog.getText().toString().equals("")){

                            numerTelefonu=EditNumerDialog.getText().toString();

                          map.put("numerTelefonu",numerTelefonu);

                        }
                        else if(EditNumerDialog.getText().toString().equals(""))
                        {
                            waga=EditWagaDialog.getText().toString();

                            map.put("waga",waga);


                        }

                        else{
                            waga=EditWagaDialog.getText().toString();
                            numerTelefonu=EditNumerDialog.getText().toString();
                            map.put("waga",waga);
                            map.put("numerTelefonu",numerTelefonu);

                        }
                        if ( notatka.equals(""))
                        {

                        }
                        else {
                            FirebaseDatabase.getInstance().getReference("Zwierzęta i właściciele").child(wlasciciel).child(imie).child("Notatki").child(dataw).setValue(new Notatki(notatka));
                        }
                        FirebaseDatabase.getInstance().getReference("Zwierzęta i właściciele").child(wlasciciel).child(imie).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                dialog.dismiss();
                            }
                        });

                    }

                });
    dialog.show();

            }
        });
        holder.fabSendUsun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialogU = new Dialog(context);
                dialogU.setContentView(R.layout.usun_dialog );
                dialogU.show();
                Button ButtonTak=dialogU.findViewById(R.id.ButtonTak);
                ButtonTak.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String imie= pacjent.imie;
                        String wlasciciel= pacjent.wlasciciel;
                        FirebaseDatabase.getInstance().getReference("Zwierzęta i właściciele").child(wlasciciel).child(imie).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                dialogU.dismiss();

                            }
                        });
                    }
                });

            }
        });

    }




    @Override
    public int getItemCount() {
        return pacjents.size();

    }

    @Override
    public Filter getFilter() {
        return FilterUser;
    }
    public Filter FilterUser= new Filter()
    {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String searchtext=charSequence.toString().toLowerCase();

            ArrayList<Pacjent> tempList=new ArrayList<>();
            if(searchtext.length()== 0 || searchtext.isEmpty())
            {

                tempList.addAll(pongsfull);
            }
            else{
                for(Pacjent item:pongsfull){
                    if(item.getWlasciciel().toLowerCase().contains(searchtext) || item.getImie().toLowerCase().contains(searchtext))
                    {

                        tempList.add(item);
                    }

                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values=tempList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            pacjents.clear();
            pacjents.addAll((Collection<? extends Pacjent>) filterResults.values);
            notifyDataSetChanged();
        }
    };


    public class PongHolder extends RecyclerView.ViewHolder{
        public TextView textImie;
        public TextView textGatunek;
        public TextView textDataUr;
        public TextView textDataPrzyj;
        public TextView textWaga;
        public TextView textwlasciciel;
        public TextView textNumerTel;
        public TextView textLekarz;
        public Button buttonNotatki;

        public FloatingActionButton fabSendEdytuj;
        public FloatingActionButton fabSendUsun;
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
            fabSendEdytuj=(FloatingActionButton) itemView.findViewById(R.id.fabSendEdytuj);
            fabSendUsun=(FloatingActionButton) itemView.findViewById(R.id.fabSendUsun);
            buttonNotatki=(Button)itemView.findViewById(R.id.buttonNotatki);





           
           
        }
       
            
        
    }



}
