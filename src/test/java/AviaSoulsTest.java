import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AviaSoulsTest {

    AviaSouls aviaSouls = new AviaSouls();
    Ticket aviaSoul1 = new Ticket("Внуково", "Домодедово", 1200, 7, 10);
    Ticket aviaSoul2 = new Ticket("Домодедово", "Кольцово", 2500, 9, 11);
    Ticket aviaSoul3 = new Ticket("Внуково", "Домодедово", 1800, 8, 10);
    Ticket aviaSoul4 = new Ticket("Кольцово", "Шереметьево", 2200, 12, 14);
    Ticket aviaSoul5 = new Ticket("Внуково", "Домодедово", 1600, 9, 13);
    Ticket aviaSoul6 = new Ticket("Внуково", "Домодедово", 1400, 10, 12);
    Ticket aviaSoul7 = new Ticket("Кольцово", "Шереметьево", 2200, 11, 13);
    Ticket aviaSoul8 = new Ticket("Внуково", "Толмачево", 2700, 17, 19);
    Ticket aviaSoul9 = new Ticket("Домодедово", "Кольцово", 2500, 16, 16);
    Ticket aviaSoul10 = new Ticket("Домодедово", "Кольцово", 2500, 23, 0);

    @BeforeEach
    public void setup() {
        aviaSouls.add(aviaSoul1);
        aviaSouls.add(aviaSoul2);
        aviaSouls.add(aviaSoul3);
        aviaSouls.add(aviaSoul4);
        aviaSouls.add(aviaSoul5);
        aviaSouls.add(aviaSoul6);
        aviaSouls.add(aviaSoul7);
        aviaSouls.add(aviaSoul8);
        aviaSouls.add(aviaSoul9);
        aviaSouls.add(aviaSoul10);
    }

    @Test
    public void shouldSortByPriceWhenSearching() {
        String searchFrom = "Внуково";
        String searchTo = "Домодедово";

        Ticket[] expected = {aviaSoul1, aviaSoul6, aviaSoul5, aviaSoul3};
        Ticket[] actual = aviaSouls.search(searchFrom, searchTo);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void noResultsFound() {
        String searchFrom = "Домодедово";
        String searchTo = "Хитроу";

        Ticket[] expected = {};
        Ticket[] actual = aviaSouls.search(searchFrom, searchTo);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSortSamePriceWhenSearching() {
        String searchFrom = "Кольцово";
        String searchTo = "Шереметьево";

        Ticket[] expected = {aviaSoul4, aviaSoul7};
        Ticket[] actual = aviaSouls.search(searchFrom, searchTo);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void oneResultFound() {
        String searchFrom = "Внуково";
        String searchTo = "Толмачево";
        TicketTimeComparator timeComparator = new TicketTimeComparator();

        Ticket[] expected = {aviaSoul8};
        Ticket[] actual = aviaSouls.searchAndSortBy(searchFrom, searchTo, timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSortSameFlightTimeWhenSearching() {
        String searchFrom = "Кольцово";
        String searchTo = "Шереметьево";
        TicketTimeComparator timeComparator = new TicketTimeComparator();

        Ticket[] expected = {aviaSoul4, aviaSoul7};
        Ticket[] actual = aviaSouls.searchAndSortBy(searchFrom, searchTo, timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortByFlightTimeWhenSearching() {
        String searchFrom = "Внуково";
        String searchTo = "Домодедово";
        TicketTimeComparator timeComparator = new TicketTimeComparator();

        Ticket[] expected = {aviaSoul3, aviaSoul6, aviaSoul1, aviaSoul5};
        Ticket[] actual = aviaSouls.searchAndSortBy(searchFrom, searchTo, timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortByFlightTimeIfOneFlewAllDayWhenSearching() {
        String searchFrom = "Домодедово";
        String searchTo = "Кольцово";
        TicketTimeComparator timeComparator = new TicketTimeComparator();

        Ticket[] expected = {aviaSoul10, aviaSoul2, aviaSoul9};
        Ticket[] actual = aviaSouls.searchAndSortBy(searchFrom, searchTo, timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }
}