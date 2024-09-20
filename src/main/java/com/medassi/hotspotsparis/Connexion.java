/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medassi.hotspotsparis;

/**
 *
 * @author AMedassi
 */
public class Connexion {
    private final String device_constructor_name ;
    private final String userlanguage ;
    private final String device_operating_system_name_version ;
    private final String device_browser_name_version ;
    private final double donnee_sortante_gigaoctet ;
    private final double donnee_entrante_go ;

    public Connexion(String device_constructor_name, String userlanguage, String device_operating_system_name_version, String device_browser_name_version, double donnee_sortante_gigaoctet, double donnee_entrante_go) {
        this.device_constructor_name = device_constructor_name;
        this.userlanguage = userlanguage;
        this.device_operating_system_name_version = device_operating_system_name_version;
        this.device_browser_name_version = device_browser_name_version;
        this.donnee_sortante_gigaoctet = donnee_sortante_gigaoctet;
        this.donnee_entrante_go = donnee_entrante_go;
    }

    public String getDevice_browser_name_version() {
        return device_browser_name_version;
    }

   
    
    public String getDevice_constructor_name() {
        return device_constructor_name;
    }

    public String getDevice_operating_system_name_version() {
        return device_operating_system_name_version;
    }

    public String getUserlanguage() {
        return userlanguage;
    }

    public double getDonnee_entrante_go() {
        return donnee_entrante_go;
    }

    public double getDonnee_sortante_gigaoctet() {
        return donnee_sortante_gigaoctet;
    }
    
}
