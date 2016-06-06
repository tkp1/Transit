package steakknife.transit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.text.DecimalFormat;
import java.util.List;

import model.EstimatesHolder;
import model.Route;
import model.RouteManager;
import model.Stop;

/**
 * an adapter to put the data from route manager into the recycler view card views.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{
    private LayoutInflater layoutInflater;
    private List<Route> routeList;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView routeNumber;
        TextView routeName;
        TextView stopLocation;
        TextView routeTime1;
        TextView routeTime2;

        public ViewHolder (View v){
            super(v);
            routeNumber = (TextView) itemView.findViewById(R.id.routeNumber);
            routeName = (TextView) itemView.findViewById(R.id.routeName);
            stopLocation = (TextView) itemView.findViewById(R.id.stopLocation);
            routeTime1 = (TextView) itemView.findViewById(R.id.time1);
            routeTime2 = (TextView) itemView.findViewById(R.id.time2);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            RouteManager rm = RouteManager.getInstance();
            Route route = rm.getRouteAtPosition(position);
            if(!(route==null)) {
                EstimatesHolder firstEstimate = route.findFirstEstimate();
                double latitude = firstEstimate.getStop().getLatitude();
                double longitude = firstEstimate.getStop().getLongitude();
                DecimalFormat numberFormat = new DecimalFormat("#.000000");
                Intent mapIntent = new Intent(v.getContext(), MapsActivity.class);
                mapIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                LatLng latLng = new LatLng(Double.parseDouble(numberFormat.format(latitude)),
                        Double.parseDouble(numberFormat.format(longitude)));
                mapIntent.putExtra("Coordinates", latLng);
                mapIntent.putExtra("StopNumber", firstEstimate.getStop().getStopNo());
                v.getContext().startActivity(mapIntent);
            }
        }
    }

    public DataAdapter (Context context, List<Route> listOfRoutes){
        layoutInflater = LayoutInflater.from(context);
        routeList = listOfRoutes;
    }

    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.my_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        Route current = routeList.get(position);
        if (current.getEstimatesSize() == 0) {
        return;
        }
        holder.routeNumber.setText(current.getRouteNumber());
        Stop currentStop = null;
        EstimatesHolder firstEstimate = current.findFirstEstimate();
        holder.routeName.setText(firstEstimate.getSchedule().getDestination());
        int expectedCountdownTime1;
        int expectedCountdownTime2;
        if (current.getEstimatesSize() == 1){
            currentStop = firstEstimate.getStop();
            expectedCountdownTime1 = firstEstimate.getSchedule().getExpectedCountdown();
            if (expectedCountdownTime1 <= 2){
                String message = "Now";
                holder.routeTime1.setText(message);
            }
            else {
                String message = String.valueOf(expectedCountdownTime1) + " min";
                holder.routeTime1.setText(message);
            }
            holder.routeTime2.setText(R.string.no_times_available);
        }
        else {
            currentStop = firstEstimate.getStop();
            expectedCountdownTime1 = firstEstimate.getSchedule().getExpectedCountdown();
            if (expectedCountdownTime1 <= 2){
                String message = "Now";
                holder.routeTime1.setText(message);
            }
            else {
                String message = String.valueOf(expectedCountdownTime1) + " min";
                holder.routeTime1.setText(message);
            }
            EstimatesHolder secondEstimate = current.findNextEstimate(currentStop);
            if(secondEstimate == null){
                holder.routeTime2.setText(R.string.no_times_available);
            }
            else {
                expectedCountdownTime2 = secondEstimate.getSchedule().getExpectedCountdown();
                if (expectedCountdownTime2 <= 2){
                    String message = "Now";
                    holder.routeTime2.setText(message);
                }
                else {
                    String message = String.valueOf(expectedCountdownTime2) + " min";
                    holder.routeTime2.setText(message);
                }
            }
        }
        String currentLocation;
        if (currentStop == null){
            currentLocation = "Data For Route Currently Unavailable";
        }
        else {
            currentLocation = currentStop.getName().substring(0,2) + " " +currentStop.getOnStreet()
                    + " @ " + currentStop.getAtStreet();
        }
        holder.stopLocation.setText(currentLocation);
    }

    @Override
    public int getItemCount() {
        return routeList.size();
    }

}
