package com.medassi.hotspotsparis;

public class Hotspot {
    private final String idpw;
    private final String etat2;
    private final Long nombre_de_borne_wifi;
    private final String nom_site;
    private final String cp;
    private final String arc_adresse;
    private final GeoPoint geo_point_2d;

    public Hotspot(String idpw, String etat2, Long nombre_de_borne_wifi, String nom_site, String cp, String arc_adresse, GeoPoint geo_point_2d) {
        this.idpw = idpw;
        this.etat2 = etat2;
        this.nombre_de_borne_wifi = nombre_de_borne_wifi;
        this.nom_site = nom_site;
        this.cp = cp;
        this.arc_adresse = arc_adresse;
        this.geo_point_2d = geo_point_2d;
    }

    

    public double distanceAvecPosition(GeoPoint p) {
        //A Faire
        return 0 ;
    }

    public double distanceAvecHotspot(Hotspot h) {
        //A Faire
        return 0;
    }

    public String getIdpw() {
        return idpw;
    }

    public String getEtat2() {
        return etat2;
    }

    public Long getNombre_de_borne_wifi() {
        return nombre_de_borne_wifi;
    }

    public String getNom_site() {
        return nom_site;
    }

    public String getCp() {
        return cp;
    }

    public String getArc_adresse() {
        return arc_adresse;
    }

  
    public GeoPoint getGeo_point_2d() {
        return geo_point_2d;
    }

    @Override
    public String toString() {
        return idpw +" / "+this.nom_site ;
    }

}
