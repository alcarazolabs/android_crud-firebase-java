package com.example.crudfirebasejava.FiltroBuscador;

import android.widget.Filter;

import com.example.crudfirebasejava.Adaptadores.ListViewPersonasAdapter;
import com.example.crudfirebasejava.Models.Persona;

import java.util.ArrayList;

public class CustomFilter extends Filter {
    ArrayList<Persona> filterList;
    ListViewPersonasAdapter adapter;

    public CustomFilter(ArrayList<Persona> filterList, ListViewPersonasAdapter adapter) {
        this.filterList = filterList;
        this.adapter = adapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constrain) {
       FilterResults results = new FilterResults();
       if(constrain!=null &&constrain.length()>0){
           constrain = constrain.toString().toUpperCase();
           ArrayList<Persona> modeloFiltrado = new ArrayList<>();
           for(int i=0;i<filterList.size();i++){
               if(filterList.get(i).getNombres().toUpperCase().contains(constrain) || filterList.get(i).getTelefono().toUpperCase().contains(constrain)) {
                   modeloFiltrado.add(filterList.get(i));
               }
           }
           results.count = modeloFiltrado.size();
           results.values = modeloFiltrado;
       }else{
           results.count = filterList.size();
           results.values = filterList;
       }
       return  results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        adapter.personaData = (ArrayList<Persona>) filterResults.values;
        adapter.notifyDataSetChanged();
    }
}
