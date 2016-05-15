package model;

/**
 * Created by Trevor on 5/14/2016.
 */

import java.net.URL;
import java.util.List;

/** represents a bus object containing time to arrival to a particular stop, bus ID
 * the route, the direction, and the location of the bus.
 */
public class Bus {
<<<<<<< HEAD
    private String vehicleNumber;
    private String tripID;
    private Route route;
    private String direction;
    private String pattern;
    private int longitude;
    private int lattiude;
    private String recordedTime;
    private String routeMap;
    private URL href;
    private int arrivalTime;

    public Bus (
            String vehicleNumber,
            String tripID,
            Route route,
            String direction,
            String pattern,
            int longitude,
            int lattiude,
            String recordedTime,
            String routeMap,
            URL href
    ) {
        this.vehicleNumber = vehicleNumber;
        this.tripID = tripID;
        this.route = route;
        this.direction = direction;
        this.pattern = pattern;
        this.longitude = longitude;
        this.lattiude = lattiude;
        this.recordedTime = recordedTime;
        this.routeMap = routeMap;
        this.href = href;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getTripID() {

        return tripID;
    }

    public Route getRoute() {
        return route;
    }

    public String getDirection() {
        return direction;
    }

    public String getPattern() {
        return pattern;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLattiude() {
        return lattiude;
    }

    public String getRecordedTime() {
        return recordedTime;
    }

    public String getRouteMap() {
        return routeMap;
    }

    public URL getHref() {
        return href;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }
=======



>>>>>>> 012af08b00ef9dbe7925bf10498cf87486f48dfe
}
