package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import steakknife.transit.RouteComparator;

/**
 * a route manager to hold all current routes, and their associated stops and schedules
 */

public class RouteManager implements Iterable<Route>{
    private Route currentRoute;
    private Stop currentStop;
    private List<Route> allCurrentRoutes;
    private List<Stop> allCurrentStops;
    private static RouteManager instance;

    private RouteManager() {
        currentRoute = null;
        allCurrentRoutes = new ArrayList<>();
        allCurrentStops = new ArrayList<>();
    }

    public static RouteManager getInstance() {
        if (instance == null) {
            instance = new RouteManager();
        }
        return instance;
    }

    public void addRoute(Route route) {
        if (!allCurrentRoutes.contains(route)) {
            allCurrentRoutes.add(route);
        }
    }

    public void addStop(Stop stop) {
        if (!allCurrentStops.contains(stop)) {
            allCurrentStops.add(stop);
        }
    }

    /**
     *
     * @return a list of all current routes
     */

    public List<Route> getAllCurrentRoutes(){
        return allCurrentRoutes;
    }

    /**
     *
     * @return a list of all current stops
     */
    public List<Stop> getAllCurrentStops(){
        return allCurrentStops;
    }

    /**
     * adds a schedule to a particular route
     * @param schedule a retrieved schedule for a particular stop and route
     * @param route the route to which we want to add a schedule
     * @param stop the stop from which the schedule was retrieved
     */
    public void addSchedule(Schedule schedule, Route route, Stop stop){
        if (!allCurrentStops.contains(stop)){
            allCurrentStops.add(stop);
        }
        for (Route routeFromManager: allCurrentRoutes){
            if(routeFromManager == route){
                routeFromManager.addScheduleEstimate(schedule,stop);
            }
        }
    }

    /**
     * checks to see if the route manager has a particular stop
     * @param stop the stop being searched for
     * @return a boolean
     */
    public boolean containsStop(Stop stop){
        return allCurrentStops.contains(stop);
    }

    /**
     * checks to see if the route manager has a particular route
     * @param route the route being searched for
     * @return a boolean
     */
    public boolean containsRoute(Route route){
        return allCurrentRoutes.contains(route);
    }

    /**
     * clears the route manager of all stops and routes
     */
    public void clearRouteManager() {
        allCurrentRoutes.clear();
        allCurrentStops.clear();
    }

    /**
     * sorts all the routes in the route manager based on the schedule arrival times, with the
     * lowest on top
     */

    public void sortRoutesInManager(){
        Collections.sort(allCurrentRoutes,new RouteComparator());
    }

    /**
     * gets the route a particular position in the route manager allCurrentRoutes list
     * @param i the index of the route
     * @return a route
     */

    public Route getRouteAtPosition(int i){
        if (i < 0 || i > allCurrentRoutes.size()) {
            return null;
        }
        return allCurrentRoutes.get(i);
    }

    @Override
    public Iterator<Route> iterator() {
        return Collections.unmodifiableList(allCurrentRoutes).iterator();
    }
}
