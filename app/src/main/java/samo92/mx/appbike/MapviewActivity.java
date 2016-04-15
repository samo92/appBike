package samo92.mx.appbike;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

/**
 * Created by samo92 on 13/04/16.
 */
public class MapviewActivity extends AppCompatActivity {

    // Create a mapView
    private MapView mapView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapview);

        // Create a mapView
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);


        //MapBoxMap object allows you to change styles and interact with your map.
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {

                // Set map style
                mapboxMap.setStyleUrl(Style.MAPBOX_STREETS);

                // Set the camera's starting position
                CameraPosition cameraPosition = new CameraPosition.Builder()

                        //Chicago position
                        //.target(new LatLng(41.885, -87.679)) // set the camera's center position

                        //CU position
                        .target(new LatLng(19.331576, -99.184483)) // set the camera's center position
                        .zoom(12)  // set the camera's zoom level
                        .tilt(20)  // set the camera's tilt (tilt==Inclinacion)
                        .build();

                // Move the camera to that position
                mapboxMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            //Creating Markers


            //The Office
            mapboxMap.addMarker(new MarkerOptions()
                    .position(new LatLng(19.326350, -99.181819))    //Office
                    .title("Here I am")
                    .snippet("Welcome to my office."));

            //Parking
            mapboxMap.addMarker(new MarkerOptions()
                    .position(new LatLng(19.332345, -99.184451))
                    .title("Hello World!")
                    .snippet("Welcome to my marker."));

            mapboxMap.addMarker(new MarkerOptions()
                    .position(new LatLng(19.332022, -99.190577))
                    .title("Hello World!")
                    .snippet("Welcome to my marker."));

            mapboxMap.addMarker(new MarkerOptions()
                    .position(new LatLng(19.332790, -99.181875))
                    .title("Hello World!")
                    .snippet("Welcome to my marker."));

            mapboxMap.addMarker(new MarkerOptions()
                    .position(new LatLng(19.326341, -99.182306))
                    .title("Hello World!")
                    .snippet("Welcome to my marker."));

            //Bike
            mapboxMap.addMarker(new MarkerOptions()
                    .position(new LatLng(19.333506, -99.179225))
                    .title("Hello World!")
                    .snippet("Welcome to my marker."));

            mapboxMap.addMarker(new MarkerOptions()
                    .position(new LatLng(19.333157, -99.182261))
                    .title("Hello World!")
                    .snippet("Welcome to my marker."));

            mapboxMap.addMarker(new MarkerOptions()
                    .position(new LatLng(19.331907, -99.187693))
                    .title("Hello World!")
                    .snippet("Welcome to my marker."));

            mapboxMap.addMarker(new MarkerOptions()
                    .position(new LatLng(19.333448, -99.191377))
                    .title("Hello World!")
                    .snippet("Welcome to my marker."));

            }

        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

}

