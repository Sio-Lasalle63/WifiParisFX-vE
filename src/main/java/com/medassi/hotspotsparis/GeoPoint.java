package com.medassi.hotspotsparis;

public class GeoPoint {

    private final double lat;
    private final double lng;

    public GeoPoint(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    private double radians(double val){
        return val*Math.PI/180 ;
    }
    public double distance(GeoPoint p) {
        return 6371 * Math.acos(Math.sin(radians(lat)) * Math.sin(radians(p.lat))
                + Math.cos(radians(lat)) * Math.cos(radians(p.lat))
                * Math.cos(radians(lng-p.lng)));
    }
   
    @Override
    public String toString() {
        return lat + "," + lng ;
    }
    
   
}
