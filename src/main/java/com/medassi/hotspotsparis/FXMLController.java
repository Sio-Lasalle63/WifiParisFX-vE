package com.medassi.hotspotsparis;

import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapLabel;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Marker;
import com.sothawo.mapjfx.event.MapViewEvent;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import org.json.simple.parser.ParseException;

public class FXMLController implements Initializable {

    @FXML
    private ListView<Hotspot> listview;
    @FXML
    private MapView mapView;

    private GestionHotspots gh;
    private final HashMap<Hotspot, Marker> hMap = new HashMap<>();
    @FXML
    private ComboBox<String> cbArr;
    @FXML
    private Label labelCentre;
    @FXML
    private Label labelHSProche;
    @FXML
    private Label labelQualite;

    @FXML
    private PieChart pieChartConstructor;
    @FXML
    private PieChart pieChartBrowser;
    @FXML
    private PieChart pieChartData;
    @FXML
    private HBox hbox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gh = new GestionHotspots();
        try {
            gh.loadJSON(ApisConfiguration.sUrlWifiTest);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        mapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                afterMapIsInitialized();
            }
        });
        mapView.initialize();
        

        for (Hotspot h : gh.getListeHS()) {
            Marker m = Marker.createProvided(Marker.Provided.RED).setVisible(false);
            Coordinate position = new Coordinate(h.getGeo_point_2d().getLat(), h.getGeo_point_2d().getLng());
            m.setPosition(position);
            MapLabel mapLabel;
            if (h.getIdpw() != null) {
                mapLabel = new MapLabel(h.getIdpw());
            } else {
                mapLabel = new MapLabel("?");
            }
            m.attachLabel(mapLabel);
            hMap.put(h, m);
        }

        refreshVisibility(gh.getListeHS());
        listview.setItems(gh.getSortedListHS());
        listview.getSelectionModel().selectedItemProperty().addListener((ov, t, t1) -> {
            changeLv(ov, t, t1);
        });
        ObservableList<String> lesArr = gh.extraireArrondissements();
        cbArr.setItems(lesArr);
        cbArr.getSelectionModel().selectedItemProperty().addListener((arr) -> {
            changeCbArr();
        });
        mapView.addEventHandler(MapViewEvent.MAP_CLICKED, event -> {
            clicMap(event);
        });

    }

    private void refreshVisibility(ObservableList<Hotspot> lesHotspots) {
        for (Hotspot h : hMap.keySet()) {
            if (lesHotspots.contains(h)) {
                hMap.get(h).setVisible(true);
            } else {
                hMap.get(h).setVisible(false);
            }
        }
    }

    private void afterMapIsInitialized() {
        mapView.setZoom(12);
        for (Marker m : hMap.values()) {
            mapView.addMarker(m);
        }
        Coordinate position;
        position = new Coordinate(48.856613d, 2.352222d);
        mapView.setCenter(position);
    }

    private void changeLv(ObservableValue<? extends Hotspot> ov, Hotspot t, Hotspot t1) {
        if (t1 != null) {
            mapView.setZoom(16);
            mapView.setCenter(new Coordinate(t1.getGeo_point_2d().getLat(), t1.getGeo_point_2d().getLng()));
            hMap.get(t1).setVisible(true);
            if (t != null) {
                hMap.get(t).setVisible(false);
            }

            HashMap<String, ObservableList<Connexion>> hm = new HashMap<>();
            try {
                hm = GestionHotspots.getConnexionsByConstr(t1);
            } catch (IOException | ParseException ex) {
                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            pieChartConstructor.getData().clear();
            pieChartBrowser.getData().clear();
            pieChartData.getData().clear();
            initPieFabricant(hm);
            initPieBrowser(hm);
            initPieDonnees(hm);
        }
    }

    
    private void changeCbArr() {
        String selectedItem = cbArr.getSelectionModel().getSelectedItem() ;
        ObservableList<Hotspot> extractHS = gh.extraireHotspotsArrondissement(selectedItem);
        listview.setItems(extractHS);
        refreshVisibility(extractHS);
        GeoPoint geoPointCentral = GestionHotspots.geoPointCentral(extractHS);
        labelCentre.setText("Centre de l'arrondissement " + selectedItem + " : " + geoPointCentral.getLat() + " ; " + geoPointCentral.getLng());
        mapView.setCenter(new Coordinate(geoPointCentral.getLat(), geoPointCentral.getLng()));
        mapView.setZoom(14);
    }

    private void clicMap(MapViewEvent event) {
        event.consume();
        final Coordinate newPosition = event.getCoordinate();
        GeoPoint pt = new GeoPoint(newPosition.getLatitude(), newPosition.getLongitude());
        Hotspot lePlusProche = gh.hotspotLePlusProche(pt);
        hMap.get(lePlusProche).setVisible(true);
        labelHSProche.setText("HS le pûs proche : " + gh.hotspotLePlusProche(pt));
        labelQualite.setText("Qualité de réception : " + gh.qualiteReception(pt));
    }

    private void initPieFabricant(HashMap<String, ObservableList<Connexion>> hm) {
       // A Faire 2
    }

    private void initPieBrowser(HashMap<String, ObservableList<Connexion>> hm) {
        // A Faire 2
    }

    private void initPieDonnees(HashMap<String, ObservableList<Connexion>> hm) {
        // A Faire 2
    }
}
