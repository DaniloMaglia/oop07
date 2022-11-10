package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    /*All'interno della classe MonthSorterNested, si crei una `enum Month` che modella i mesi dell'anno.
Si suggerisce di valutare l'utilizzo di un campo che modella il numero di giorni del mese.
Questa enum *deve* avere un metodo `Month fromString(String)` che, data una stringa di testo, restituisce il `Month`
che meglio la rappresenta. A tal proposito, si legga con molta attenzione la Javadoc di `MonthSorter`.
*/
    private enum Month {
        JANUARY("January", 31),
        FEBRUARY("February", 28),
        MARCH("March", 31),
        APRIL("April", 30),
        MAY("May", 31),
        JUNE("June", 30),
        JULY("July", 31),
        AUGUST("August", 31),
        SEPTEMBER("September", 30),
        OCTOBER("October", 31),
        NOVEMBER("November", 30),
        DECEMBER("December", 31);

        private final String name;
        private final int days;

        private Month(final String name, final int days) {
            this.name = name;
            this.days = days;
        }

        public static Month fromString(final String name) {
            Month found = null;
            for (Month month : Month.values()) {
                if(month.getName().toLowerCase().startsWith(name.toLowerCase())) {
                    if (found != null) {
                        throw new IllegalArgumentException(name + " is ambigous as both " + month.getName() + " and " + found.getName() + " are possible match");
                    }
                    found = month;
                }
            }
            if (found == null){
                throw new IllegalArgumentException("Name not valid: " + name);
            }
            return found;
        }

        public int getDays() {
            return days;
        }

        public String getName() {
            return name;
        }

    }

    class SortByMonthOrder implements Comparator<String> {

        @Override
        public int compare(final String o1, final String o2) {
            return Month.fromString(o1).compareTo(Month.fromString(o2));
        }

    }

    class SortByDate implements Comparator<String> {

        @Override
        public int compare(final String o1, final String o2) {
            return Month.fromString(o1).getDays() - Month.fromString(o2).getDays();
        }

    }

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }
}
