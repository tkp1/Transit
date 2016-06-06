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

    private String vehicleNumber;
    private String tripID;
    private String routeNumber;
    private String direction;
    private String destination;
    private String pattern;
    private double longitude;
    private double lattiude;
    private String recordedTime;
    private String href;
    private int arrivalTime;

    public Bus (
            String vehicleNumber,
            String tripID,
            String routeNumber,
            String direction,
            String destination,
            String pattern,
            double longitude,
            double lattiude,
            String recordedTime,
            String href
    ) {
        this.vehicleNumber = vehicleNumber;
        this.tripID = tripID;
        this.routeNumber = routeNumber;
        this.direction = direction;
        this.destination = destination;
        this.pattern = pattern;
        this.longitude = longitude;
        this.lattiude = lattiude;
        this.recordedTime = recordedTime;
        this.href = href;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getTripID() {

        return tripID;
    }

    public String getRoute() {
        return routeNumber;
    }

    public String getDirection() {
        return direction;
    }

    public String getDestination() {return destination;}

    public String getPattern() {
        return pattern;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLattiude() {
        return lattiude;
    }

    public String getRecordedTime() {
        return recordedTime;
    }


    public String getHref() {
        return href;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

}
