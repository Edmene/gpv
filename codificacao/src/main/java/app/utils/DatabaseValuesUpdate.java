package app.utils;

import app.models.Reservation;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDate;

public class DatabaseValuesUpdate implements org.quartz.Job {

    public DatabaseValuesUpdate() {
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {

        Base.open(Db.getInstance());
        LazyList<Reservation> reservations = Reservation.find("reservation_type = ? " +
                "AND alteration_date = ? AND status IS TRUE", "M", LocalDate.now());
        for (Reservation reservation : reservations){
            reservation.set("status", false);
            reservation.save();
        }
        reservations = Reservation.find("reservation_type = ? " +
                "AND date < ? AND status IS TRUE", "P", LocalDate.now());
        for (Reservation reservation : reservations){
            reservation.set("status", false);
            reservation.save();
        }
        Base.close();

    }

}

