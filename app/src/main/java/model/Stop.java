package model;

import java.util.List;

/**
 * Created by Trevor on 5/14/2016.
 */
public class Stop {

    private int stopNo;
    private String name;
    private String bayNo;
    private String city;
    private String onStreet;
    private String atStreet;
    private double latitude;
    private double longitude;
    private boolean wheelchairAccess;
    private int distance;

    public Stop(int stopNo,
                String name,
                String bayNo,
                String city,
                String onStreet,
                String atStreet,
                double latitude,
                double longitude,
                boolean wheelchairAccess,
                int distance) {
        this.stopNo = stopNo;
        this.name = name;
        this.bayNo = bayNo;
        this.city = city;
        this.onStreet = onStreet;
        this.atStreet = atStreet;
        this.latitude = latitude;
        this.longitude = longitude;
        this.wheelchairAccess = wheelchairAccess;
        this.distance = distance;
    }

    public int getStopNo() {
        return stopNo;
    }

    public String getName() {
        return name;
    }

    public String getBayNo() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stop stop = (Stop) o;

        return stopNo == stop.stopNo;

    }

    @Override
    public int hashCode() {
        return stopNo;
    }
}
