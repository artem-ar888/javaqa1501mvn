import java.util.Comparator;

public class TicketTimeComparator implements Comparator<Ticket> {

    private int difference(Ticket t) {
        if (t.getTimeTo() > t.getTimeFrom()) {
            return t.getTimeTo() - t.getTimeFrom();
        } else if (t.getTimeTo() < t.getTimeFrom()) {
            return 24 - t.getTimeFrom() + t.getTimeTo();
        } else {
            return 24;
        }
    }

    @Override
    public int compare(Ticket t1, Ticket t2) {
        if (difference(t1) < difference(t2)) {
            return -1;
        } else if (difference(t1) > difference(t2)) {
            return 1;
        } else {
            return 0;
        }
    }
}