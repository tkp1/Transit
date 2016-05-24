package API;

/**
 * Created by Tak on 22/05/2016.
 */
public class StopEstimatesData {
    private String RouteNo;
    private String RouteName;
    private String Direction;
    private RouteMap RouteMap;
    private Schedule[] Schedules;

    public String getRouteNo() {
        return RouteNo;
    }

    public String getRouteName() {
        return RouteName;
    }

    public String getDirection() {
        return Direction;
    }

    public StopEstimatesData.RouteMap getRouteMap() {
        return RouteMap;
    }

    public Schedule[] getSchedules() {
        return Schedules;
    }

    public class RouteMap {
        private String Href;

        public String getHref() {
            return Href;
        }
    }

    public class Schedule {
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
    }
}


