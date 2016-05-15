package model;

import java.util.List;

/**
 * Created by Trevor on 5/14/2016.
 */
public class Stop {

    private int stopNo;
    private String Name;
    private int bayNo;
    private String city;
    private String onStreet;
    private String atStreet;
    private double latitude;
    private double longitude;
    private boolean wheelchairAccess;
    private double distance;
    private List<Route> routes;

    public Stop(int stopNo,
                String name,
                int bayNo,
                String city,
                String onStreet,
                String atStreet,
                double latitude,
                double longitude,
                boolean wheelchairAccess,
                double distance,
                List<Route> routes) {
        this.stopNo = stopNo;
        Name = name;
        this.bayNo = bayNo;
        this.city = city;
        this.onStreet = onStreet;
        this.atStreet = atStreet;
        this.latitude = latitude;
        this.longitude = longitude;
        this.wheelchairAccess = wheelchairAccess;
        this.distance = distance;
        this.routes = routes;
    }

    public Stop(int stopNo,
                String name,
                String city,
                double longitude,
                double latitude,
                String atStreet,
                String onStreet,
                boolean wheelchairAccess,
                double distance,
                int bayNo) {
        this.stopNo = stopNo;
        Name = name;
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
        this.atStreet = atStreet;
        this.onStreet = onStreet;
        this.wheelchairAccess = wheelchairAccess;
        this.distance = distance;
        this.bayNo = bayNo;
    }

    public int getStopNo() {
        return stopNo;
    }

    public String getName() {
        return Name;
    }

    public int getBayNo() {
        return bayNo;
    }

    public String getCity() {
        return city;
    }

    public String getOnStreet() {
        return onStreet;
    }

    public String getAtStreet() {
        return atStreet;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public boolean isWheelchairAccess() {
        return wheelchairAccess;
    }

    public double getDistance() {
        return distance;
    }

    public List<Route> getRoutes() {
        return routes;
    }
}
