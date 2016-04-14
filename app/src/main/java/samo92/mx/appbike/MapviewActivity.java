package samo92.mx.appbike;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mapbox.mapboxsdk.maps.MapView;

/**
 * Created by samo92 on 13/04/16.
 */
public class MapviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapview);

        // Create a mapView
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
    }
}
