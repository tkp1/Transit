package steakknife.transit;

import android.location.Location;

import java.util.List;

import API.StopEstimatesDataParser;
import API.StopEstimatesFeed;
import API.StopsDataParser;
import API.StopsFeed;
import model.EstimatesHolder;
import model.Route;
import model.RouteManager;
import model.Stop;

/**
 * puts data into the route manager
 */

public class RouteManagerPopulator {


    public static List<Route> populateRouteManager(int radius, Location location){
        RouteManager routeManager = RouteManager.getInstance();
        String response;
        if (location == null){
            return null;
        }
        else {
            try {
                response = StopsFeed.execute(radius, location.getLatitude(), location.getLongitude());
                List<Stop> stops = StopsDataParser.parse(response);
                List<Route> routes;
                for (Stop stop: stops){
                    routes = null;
                    String routeResponse = "";
                    try {
                        routeResponse = StopEstimatesFeed.execute(stop.getStopNo());
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    try {
                        routes = StopEstimatesDataParser.parse(routeResponse, stop);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    if(routes != null) {
                        for (Route route : routes) {
                            if (!routeManager.containsRoute(route)) {
                                routeManager.addRoute(route);
                                if (!routeManager.containsStop(stop)) {
                                    routeManager.addStop(stop);
                                }
                            } else {
                                int size = route.getEstimatesSize();

                                for (int i = 0; i < size; i++) {
                                    EstimatesHolder holder = route.getListOfEstimates().get(i);
                                    routeManager.addSchedule(holder.getSchedule(), route, holder.getStop());
                                }
                            }
                            route.sortEstimates();
                        }
                    }}
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        routeManager.sortRoutesInManager();
        return routeManager.getAllCurrentRoutes();
    }
}
