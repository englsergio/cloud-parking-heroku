package one.digitalinnovation.cloudparking.service;

import one.digitalinnovation.cloudparking.model.Parking;
import org.aspectj.apache.bcel.generic.RET;

import java.time.temporal.ChronoUnit;

public abstract class ParkingCheckout {

    private static final int ONE_HOUR = 60;
    private static final int ONE_DAY = 24 * ONE_HOUR;
    private static final double ONE_HOUR_VALUE = 5.00;
    private static final double ADDITIONAL_PER_HOUR_VALUE = 2.00;
    private static final double DAY_VALUE = 20.00;

    public static Double calculateBill(Parking parking) {
        long minutes = parking
                .getEntryDate().until(parking.getExitDate(), ChronoUnit.MINUTES);
        if(minutes <= ONE_HOUR) return ONE_HOUR_VALUE;
        double subDayValue = 0;
        long hours;
        hours = minutes / ONE_HOUR;
        if(minutes % ONE_HOUR != 0) hours++;
        if(minutes < ONE_DAY) {
            subDayValue = ONE_HOUR_VALUE + (hours - 1) * ADDITIONAL_PER_HOUR_VALUE;
            return subDayValue;
        }
        long days = minutes / ONE_DAY;
        return days * DAY_VALUE + subDayValue;
    }

}
