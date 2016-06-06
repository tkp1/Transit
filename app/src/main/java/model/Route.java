package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Trevor on 5/14/2016.
 */
public class Route {
    private String routeNumber;
    private String routeName;
    private String direction;
    private String routeMap;
    private List<EstimatesHolder> listOfEstimates;

    public Route (String routeNumber, String routeName, String direction, String routeMap){
        this.routeNumber = routeNumber;
        this.routeName = routeName;
        this.direction = direction;
        this.routeMap = routeMap;
        listOfEstimates = new ArrayList<>();
    }

    public void addSchedule (Schedule schedule, Stop stop){
        listOfEstimates.add(new EstimatesHolder(schedule,stop));
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public String getRouteName() {
        return routeName;
    }

    public String getDirection() {
        return direction;
    }

    public String getRouteMap() {
        return routeMap;
    }

    public List<EstimatesHolder> getListOfEstimates() {
        return listOfEstimates;
    }

    public void sortEstimatesOfRoute() {
        Collections.sort(listOfEstimates);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (routeNumber != null ? !routeNumber.equals(route.routeNumber) : route.routeNumber != null)
            return false;
        if (routeName != null ? !routeName.equals(route.routeName) : route.routeName != null)
            return false;
        return direction != null ? direction.equals(route.direction) : route.direction == null;

    }

    @Override
    public int hashCode() {
        int result = routeNumber != null ? routeNumber.hashCode() : 0;
        result = 31 * result + (routeName != null ? routeName.hashCode() : 0);
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }

    public EstimatesHolder getFirstEstimate() {
        return listOfEstimates.get(0);
    }

    public EstimatesHolder getNextEstimate() {
        for(int i = 1; i < listOfEstimates.size(); i++){
            EstimatesHolder first = this.getFirstEstimate();
            EstimatesHolder current = listOfEstimates.get(i);
            if(current.getStop()== first.getStop()){
                return current;
            }
        }
        return null;
    }
}
