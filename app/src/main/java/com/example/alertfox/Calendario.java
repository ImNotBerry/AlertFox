package com.example.alertfox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Calendario {
    private static Calendario singleton;

    private ArrayList<Recordatorio> recordatorios;

    public static Calendario getSingleton(){
        if (Calendario.singleton==null){
            Calendario.singleton = new Calendario();
        }
        return Calendario.singleton;
    }
    private Calendario(){
        recordatorios = new ArrayList<>();
    }

    public ArrayList<Recordatorio> getRecordatorios(){
        return recordatorios;
    }

    public void sortRecordatorios(){
        Collections.sort(recordatorios, new Comparator<Recordatorio>(){
            public int compare(Recordatorio o1, Recordatorio o2){
                return o1.fecha.compareTo(o2.fecha);
            }
        });
    }
}
