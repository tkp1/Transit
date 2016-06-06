package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A bus route with associated stops, estimates holders, names and route map
 */
public class Route {
    private String routeName;
    private String routeNumber;
    private String direction;
    private String routeMap;
    private List<EstimatesHolder> listOfEstimates;

    public Route(
            String routeName,
            String routeNumber,
            String direction,
            String routeMap
    )
    {
        this.routeName = routeName;
        this.routeNumber = routeNumber;
        this.direction = direction;
        this.routeMap = routeMap;
        listOfEstimates = new ArrayList<>();
        }

    /**
     * adds a schedule estimate based on a stop and a given schedule as an EstimatesHolder
     * @param schedule the arrival estimate and other information from an arrival
     * @param stop the particular stop associated with the schedule for this route
     */
    public void addScheduleEstimate(Schedule schedule, Stop stop) {
        listOfEstimates.add(new EstimatesHolder(schedule,stop));
    }

    /**
     * checks to see if the list of Estimates for this particular route has a particular stop
     * @param stop the stop being searched for
     * @return a boolean
     */
    public boolean listOfEstimatesHasStop(Stop stop){
        for(EstimatesHolder holder: listOfEstimates){
            if(holder.getStop() == stop){
                return true;
            }
        }
        return false;
    }

    /**
     * sorts the EstimatesHolders based on arrival time
     */
    public void sortEstimates(){
        Collections.sort(listOfEstimates);
    }

    /**
     * finds the top most EstimatesHolder, should be the soonest to arrive if sorted
     * @return an EstimatesHolder
     */
    public EstimatesHolder findFirstEstimate() {
        if (listOfEstimates.isEmpty()){
            return null;
        }
        else {
            return listOfEstimates.get(0);
        }
    }

    /**
     * finds the next EstimatesHolder of a particular route given a particular stop
     * @param stop the stop being searched for to find the next EstimatesHolder
     * @return an EstimatesHolder
     */
    public EstimatesHolder findNextEstimate(Stop stop) {
        for(int i = 1; i < listOfEstimates.size(); i++){
            EstimatesHolder current = listOfEstimates.get(i);
            if (current.getStop() == stop) {
                return current;
            }
        }
    return null;
    }

    /**
     * clears all estimates
     */
    public void clearEstimates() {
        listOfEstimates.clear();
    }

    public String getRouteName() {
        return routeName;
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public String getDirection() {
        return direction;
    }

    public String getRouteMap() {
        return routeMap;
    }


    public List<EstimatesHolder> getListOfEstimates() {
        return  Collections.unmodifiableList(listOfEstimates);
    }

    public int getEstimatesSize() {
        return listOfEstimates.size();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route stops = (Route) o;

        if (routeName != null ? !routeName.equals(stops.routeName) : stops.routeName != null)
            return false;
        if (routeNumber != null ? !routeNumber.equals(stops.routeNumber) : stops.routeNumber != null)
            return false;
        return direction != null ? direction.equals(stops.direction) : stops.direction == null;

    }

    @Override
    public int hashCode() {
        int result = routeName != null ? routeName.hashCode() : 0;
        result = 31 * result + (routeNumber != null ? routeNumber.hashCode() : 0);
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }
}

