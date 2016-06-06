package steakknife.transit;

import java.util.Comparator;

import model.Route;

/**
 * compares routes by their first EstimatesHolders.
 */

public class RouteComparator implements Comparator<Route> {
    @Override
    public int compare(Route lhs, Route rhs) {
        return lhs.findFirstEstimate().compareTo(rhs.findFirstEstimate());
    }
}
