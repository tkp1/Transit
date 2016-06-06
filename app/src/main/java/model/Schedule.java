package model;

/**
 * Created by Tak on 31/05/2016.
 */
public class Schedule {
    private String pattern;
    private String destination;
    private String expectedLeaveTime;
    private int expectedCountdown;
    private String scheduleStatus;
    private boolean cancelledTrip;
    private boolean cancelledStop;
    private boolean addedTrip;
    private boolean addedStop;
    private String lastUpdate;

    public Schedule (
            String pattern,
            String destination,
            String expectedLeaveTime,
            int expectedCountdown,
            String scheduleStatus,
            boolean cancelledTrip,
            boolean cancelledStop,
            boolean addedTrip,
            boolean addedStop,
            String lastUpdate
    ){
        this.pattern = pattern;
        this.destination = destination;
        this.expectedLeaveTime = expectedLeaveTime;
        this.expectedCountdown = expectedCountdown;
        this.scheduleStatus = scheduleStatus;
        this.cancelledTrip = cancelledTrip;
        this.cancelledStop = cancelledStop;
        this.addedTrip = addedTrip;
        this.addedStop = addedStop;
        this.lastUpdate = lastUpdate;
    }

    public String getPattern() {
        return pattern;
    }

    public String getDestination() {
        return destination;
    }

    public String getExpectedLeaveTime() {
        return expectedLeaveTime;
    }

    public int getExpectedCountdown() {
        return expectedCountdown;
    }

    public String getScheduleStatus() {
        return scheduleStatus;
    }

    public boolean isCancelledTrip() {
        return cancelledTrip;
    }

    public boolean isCancelledStop() {
        return cancelledStop;
    }

    public boolean isAddedTrip() {
        return addedTrip;
    }

    public boolean isAddedStop() {
        return addedStop;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }
}
