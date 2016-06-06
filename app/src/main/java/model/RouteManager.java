package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Tak on 31/05/2016.
 */
public class RouteManager {

    private List<Stop> listOfStops;
    private List<Route> listOfRoutes;
    private static  RouteManager instance = null;

    private RouteManager(){
        listOfStops = new ArrayList<>();
        listOfRoutes = new ArrayList<>();
    }

    public static RouteManager getInstance() {
        if (instance == null){
            instance = new RouteManager();
        }
        return instance;
    }

    public void addRoute (Route route){
        listOfRoutes.add(route);
    }

    public void addStop (Stop stop){
        listOfStops.add(stop);
    }
    public void addScheduleEstimate(Route route, Stop stop, Schedule schedule) {
        if (!(listOfRoutes.contains(route))){
            listOfRoutes.add(route);
            List<EstimatesHolder> listOfEstimates = route.getListOfEstimates();
            for(EstimatesHolder holder: listOfEstimates){
                Stop containedStop = holder.getStop();
                if(!(listOfStops.contains(containedStop))){
                    listOfStops.add(containedStop);
                }
            }
        }
        else {
            for(Route containedRoute : listOfRoutes){
                if(containedRoute == route){
                    containedRoute.addSchedule(schedule,stop);
                }
            }
        }
    }

    public void sortListOfRoutes(){
        Collections.sort(listOfRoutes,new RouteEstimatesComparator());
    }

    public class RouteEstimatesComparator implements Comparator<Route> {
        @Override
        public int compare(Route lhs, Route rhs) {
            return lhs.getFirstEstimate().getSchedule().getExpectedCountdown() -
                    rhs.getFirstEstimate().getSchedule().getExpectedCountdown();
        }
    }
}
