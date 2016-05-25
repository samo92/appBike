package samo92.mx.appbike;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.layers.CustomLayer;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.services.Constants;
import com.mapbox.services.commons.ServicesException;
import com.mapbox.services.commons.geojson.LineString;
import com.mapbox.services.commons.models.Position;
import com.mapbox.services.directions.v4.DirectionsCriteria;
import com.mapbox.services.directions.v4.MapboxDirections;
import com.mapbox.services.directions.v4.models.DirectionsResponse;
import com.mapbox.services.directions.v4.models.DirectionsRoute;
import com.mapbox.services.directions.v4.models.Waypoint;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by samo92 on 13/04/16.
 */
public class MapviewActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    private DirectionsRoute currentRoute;

    private MapboxMap map;

    // Create a mapView
    private MapView mapView;

    //accessToken
    //private String myToken = "pk.eyJ1Ijoic2FtbzkyIiwiYSI6ImNpbXprdzVyejA0eGF1bm00NGhpem15ajMifQ.R4M1a2dTm1szXs68C9vlzQ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapview);

        // Alhambra landmark in Granada, Spain.
        //Primera posicion
        //final Position origin = Position.fromCoordinates(-3.588098, 37.176164);
        final Waypoint origin = new Waypoint(-3.588098, 37.176164);
        //final Waypoint origin = new Waypoint(19.430349, -99.210860); //Segunda mano


        // Plaza del Triunfo in Granada, Spain.
        //Segunda posicion
        //final Position destination = Position.fromCoordinates(-3.601845, 37.184080);
        final Waypoint destination = new Waypoint(-3.601845, 37.184080);
        //final Waypoint destination = new Waypoint(19.433639, -99.190146);  //ALgun lugar


        // Create a mapView
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);


        //MapBoxMap object allows you to change styles and interact with your map.
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                map = mapboxMap;

                // Set map style
                mapboxMap.setStyleUrl(Style.MAPBOX_STREETS);

                // Set the camera's starting position
                CameraPosition cameraPosition = new CameraPosition.Builder()

                        //Chicago position
                        //.target(new LatLng(41.885, -87.679)) // set the camera's center position

                        //CU position
                        //.target(new LatLng(19.331576, -99.184483)) // set the camera's center position
                        .target(new LatLng(-3.601845, 37.184080)) //Posicion de la camara
                        .zoom(12)  // set the camera's zoom level
                        .tilt(20)  // set the camera's tilt (tilt==Inclinacion)
                        .build();

                // Move the camera to that position
                mapboxMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                //Creating Markers

                /** Use IconFactory, Drawable, and Icon to load our marker icon
                 * and assign it to a marker */
                IconFactory iconFactory = IconFactory.getInstance(MapviewActivity.this);
                Drawable iconDrawable = ContextCompat.getDrawable(MapviewActivity.this, R.mipmap.este);
                Icon icon_centro = iconFactory.fromDrawable(iconDrawable);

                Drawable iconParking = ContextCompat.getDrawable(MapviewActivity.this, R.mipmap.este2);
                Icon icon_parking = iconFactory.fromDrawable(iconParking);


                // Add origin and destination to the map
                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(origin.getLatitude(), origin.getLongitude()))
                        .title("Origin")
                        .snippet("Alhambra")
                        .icon(icon_centro));
                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(destination.getLatitude(), destination.getLongitude()))
                        .title("Destination")
                        .snippet("Plaza del Triunfo")
                        .icon(icon_parking));

                try {
                    getRoute(origin, destination);
                } catch (ServicesException e) {
                    e.printStackTrace();
                }

                //The Office

                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(19.326350, -99.181819, 1054.452))    //Office
                        .title("Here I am")
                        .snippet("Welcome to my office.")
                        .icon(icon_centro));
                //.setIcon(new Icon(this, Icon.Size.Large, "danger", "3887be"));

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

    private void getRoute(Waypoint origin, Waypoint destination) throws ServicesException {

        MapboxDirections client = new MapboxDirections.Builder()
                .setOrigin(origin)
                .setDestination(destination)
                .setProfile(DirectionsCriteria.PROFILE_CYCLING)
                .setAccessToken("pk.eyJ1Ijoic2FtbzkyIiwiYSI6ImNpbXprdzVyejA0eGF1bm00NGhpem15ajMifQ.R4M1a2dTm1szXs68C9vlzQ")
                .build();

        client.enqueueCall(new Callback<DirectionsResponse>() {
            @Override
            public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                // You can get the generic HTTP info about the response
                Log.d(TAG, "Response code: " + response.code());
                if (response.body() == null) {
                    Log.e(TAG, "No routes found, make sure you set the right user and access token.");
                    return;
                }

                // Print some info about the route
                currentRoute = response.body().getRoutes().get(0);
                Log.d(TAG, "Distance: " + currentRoute.getDistance());
                Toast.makeText(MapviewActivity.this, "Route is " + currentRoute.getDistance() + " meters long.", Toast.LENGTH_SHORT).show();

                // Draw the route on the map
                drawRoute(currentRoute);
            }

            @Override
            public void onFailure(Call<DirectionsResponse> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
                Toast.makeText(MapviewActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void drawRoute(DirectionsRoute route) {
        // Convert LineString coordinates into LatLng[]
        LineString lineString = LineString.fromPolyline(route.getGeometry(), Constants.OSRM_PRECISION_V4);
        List<Position> coordinates = lineString.getCoordinates();
        LatLng[] points = new LatLng[coordinates.size()];
        for (int i = 0; i < coordinates.size(); i++) {
            points[i] = new LatLng(
                    coordinates.get(i).getLatitude(),
                    coordinates.get(i).getLongitude());
        }

        // Draw Points on MapView
        map.addPolyline(new PolylineOptions()
                .add(points)
                .color(Color.parseColor("#009688"))
                .width(5));
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

