package model;

/**
 * a schedule for an arrival of a bus from a particular route, at a particular stop
 */

public class Schedule implements Comparable<Schedule> {
    private String Pattern;
    private String Destination;
    private String ExpectedLeaveTime;
    private int ExpectedCountdown;
    private String ScheduleStatus;
    private boolean CancelledTrip;
    private boolean CancelledStop;
    private boolean AddedTrip;
    private boolean AddedStop;
    private String LastUpdate;

    public Schedule(
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
        this.Pattern = pattern;
        this.Destination = destination;
        this.ExpectedLeaveTime = expectedLeaveTime;
        this.ExpectedCountdown = expectedCountdown;
        this.ScheduleStatus = scheduleStatus;
        this.CancelledTrip = cancelledTrip;
        this.CancelledStop = cancelledStop;
        this.AddedTrip = addedTrip;
        this.AddedStop = addedStop;
        this.LastUpdate = lastUpdate;
    }

    public String getPattern() {
        return Pattern;
    }

    public String getDestination() {
        return Destination;
    }

    public String getExpectedLeaveTime() {
        return ExpectedLeaveTime;
    }

    public int getExpectedCountdown() {
        return ExpectedCountdown;
    }

    public String getScheduleStatus() {
        return ScheduleStatus;
    }

    public boolean isCancelledTrip() {
        return CancelledTrip;
    }

    public boolean isCancelledStop() {
        return CancelledStop;
    }

    public boolean isAddedTrip() {
        return AddedTrip;
    }

    public boolean isAddedStop() {
        return AddedStop;
    }

    public String getLastUpdate() {
        return LastUpdate;
    }


    @Override
    public int compareTo(Schedule schedule) {
        return (this.getExpectedCountdown() - schedule.getExpectedCountdown());
    }
}