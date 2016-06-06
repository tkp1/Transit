package model;

/**
 * a holder to keep a schedule (an arrival estimate) with a particular stop number
 */

public class EstimatesHolder implements Comparable<EstimatesHolder> {
    private Schedule schedule;
    private Stop stop;

    public EstimatesHolder(Schedule schedule, Stop stop){
        this.schedule = schedule;
        this.stop = stop;
    }

    public Stop getStop(){
        return this.stop;
    }

    public Schedule getSchedule(){
        return this.schedule;
    }

    @Override
    public int compareTo(EstimatesHolder holder) {
        return (this.getSchedule().getExpectedCountdown() -
                holder.getSchedule().getExpectedCountdown());
    }
}
