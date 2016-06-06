package model;


/** represents a bus object containing time to arrival to a particular stop, bus ID
 * the route, the direction, and the location of the bus.
 */
public class Bus {

    private String vehicleNumber;
    private int tripID;
    private String routeNo;
    private String direction;
    private String pattern;
    private Double longitude;
    private Double lattiude;
    private String destination;
    private String recordedTime;
    private String href;
    private int arrivalTime;

    public Bus(
            String vehicleNumber,
            int tripID,
            String routeNo,
            String direction,
            String pattern,
            Double longitude,
            Double lattiude,
            String destination,
            String recordedTime,
            String href
    ) {
        this.vehicleNumber = vehicleNumber;
        this.tripID = tripID;
        this.routeNo = routeNo;
        this.direction = direction;
        this.pattern = pattern;
        this.longitude = longitude;
        this.lattiude = lattiude;
        this.destination = destination;
        this.recordedTime = recordedTime;
        this.href = href;

    }

    /**
     * sets the arrival time of a bus
     * @param arrivalTime
     */
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public int getTripID() {
        return tripID;
    }

    public String getRouteNo() {
        return routeNo;
    }

    public String getDirection() {
        return direction;
    }

    public String getPattern() {
        return pattern;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLattiude() {
        return lattiude;
    }

    public String getDestination() {
        return destination;
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
}

