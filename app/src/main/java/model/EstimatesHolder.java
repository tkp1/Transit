package model;

/**
 * Created by Tak on 01/06/2016.
 */
public class EstimatesHolder implements Comparable<EstimatesHolder>{
    private Schedule schedule;
    private Stop stop;

    public EstimatesHolder (Schedule schedule, Stop stop){
        this.stop = stop;
        this.schedule = schedule;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Stop getStop() {
        return stop;
    }


    @Override
    public int compareTo(EstimatesHolder another) {
        return this.getSchedule().getExpectedCountdown() -
                another.getSchedule().getExpectedCountdown();
    }
}
