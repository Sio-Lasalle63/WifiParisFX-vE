package com.medassi.hotspotsparis;

import java.io.IOException;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GestionHotspots {

    private final ObservableList<Hotspot> listeHS = FXCollections.observableArrayList();
    private SortedList<Hotspot> sortedListeHS;

    public ObservableList<Hotspot> getListeHS() {
        return listeHS;
    }

    public ObservableList<Hotspot> getSortedListHS() {
        return this.sortedListeHS;
    }

    public void loadJSON(String sUrlWifi) throws IOException, ParseException {
        JSONParser jsonp = new JSONParser();
        OkHttpClient okHttpClient = SSLUtil.getUnsafeOkHttpClient();
        Request request = new Request.Builder()
                .url(sUrlWifi)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        String repStr = response.body().string();
        // A compléter
    }

    public static GeoPoint geoPointCentral(ObservableList<Hotspot> lHS) {
        // A Faire
        return new GeoPoint(0,0);
    }

    public ObservableList<String> extraireArrondissements() {
        ObservableList<String> l = FXCollections.observableArrayList();
        //A Faire        
        return l;
    }

    public ObservableList<Hotspot> extraireHotspotsArrondissement(String arrondissement) {
        ObservableList<Hotspot> listeRetour = FXCollections.observableArrayList();
        //A Faire
        return listeRetour;
    }

    public boolean existeDansArrondissement(String arrondissement) {
        //A Faire
        return false ;
    }

    public Hotspot hotspotLePlusProche(GeoPoint position) {
        //A Faire
        return null;
    }

    public char qualiteReception(GeoPoint position) {
        //A Faire
        return 'E' ;
    }

    public static HashMap<String, ObservableList<Connexion>> getConnexionsByConstr(Hotspot h) throws IOException, ParseException {
        HashMap<String, ObservableList<Connexion>> hm = new HashMap<>();
        //A Faire
        return hm;
    }

}
