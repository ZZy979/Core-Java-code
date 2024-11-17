package bridgeMethod;

import java.time.LocalDate;

import pair.Pair;

public class BridgeMethodTest {
    public static void main(String[] args) {
        var interval = new DateInterval(LocalDate.of(2022, 7, 31), LocalDate.of(2023, 11, 4));
        Pair<LocalDate> pair = interval;
        interval.setSecond(LocalDate.of(2024, 7, 7));
        System.out.println("first = " + pair.getFirst());
        System.out.println("second = " + pair.getSecond());
    }
}

class DateInterval extends Pair<LocalDate> {
    public DateInterval(LocalDate first, LocalDate second) {
        if (first.isAfter(second)) { var temp = first; first = second; second = temp; }
        setFirst(first);
        setSecond(second);
    }

    @Override
    public void setSecond(LocalDate second) {
        if (second.isAfter(getFirst()))
            super.setSecond(second);
    }

    @Override
    public LocalDate getSecond() {
        return super.getSecond();
    }
}
