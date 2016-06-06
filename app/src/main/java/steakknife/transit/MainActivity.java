package steakknife.transit;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.Route;
import model.RouteManager;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter dataAdapter;
    private RecyclerView.LayoutManager layoutManager;
    static final int permissionFine = 50;
    static final int permissionCoarse = 100;
    private LocationManager lm;
    private Location location;
    private RouteManager routeManager = RouteManager.getInstance();
    private LocationListener listener = this;
    private int radius = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button refreshButton = (Button) findViewById(R.id.refreshButton);
        if (refreshButton != null) {
            refreshButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v){
                    routeManager.clearRouteManager();
                    new HarvestData().execute();
                        if (dataAdapter != null) {
                            dataAdapter.notifyDataSetChanged();
                        }
                }
            });
        }
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},permissionFine);
        }
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},permissionCoarse);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        dataAdapter = new DataAdapter(getApplicationContext(),routeManager.getAllCurrentRoutes());
        recyclerView.setAdapter(dataAdapter);
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED)&& (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)){
            new HarvestData().execute();
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                routeManager.clearRouteManager();
                new HarvestData().execute();
                if (dataAdapter != null) {
                    dataAdapter.notifyDataSetChanged();
                }
                handler.postDelayed(this,60000);
            }
        }, 60000);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case permissionFine: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {

                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                break;
            }
            case permissionCoarse: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    new HarvestData().execute();
                } else {

                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        }
    }

    private class HarvestData extends AsyncTask<Void, Void, List<Route>>{

        @Override
        protected void onPreExecute() {
            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION);
            ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
            try {
                location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
            catch (SecurityException e){
                e.printStackTrace();
            }
            if(!routeManager.getAllCurrentRoutes().isEmpty()){
                routeManager.clearRouteManager();
                dataAdapter.notifyDataSetChanged();
            }
        }

        @Override
        protected List<Route> doInBackground(Void... params) {
            return RouteManagerPopulator.populateRouteManager(radius, location);
        }

        @Override
        public void onPostExecute(List<Route> list){
            if (list == null || list.size()==0){
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,R.string.no_information, duration);
                toast.setGravity(Gravity.TOP, 0,400);
                toast.show();
            }
            else {
                dataAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
        new HarvestData().execute();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
