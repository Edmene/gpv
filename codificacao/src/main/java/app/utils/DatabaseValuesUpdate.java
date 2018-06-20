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

        Base.open("org.postgresql.Driver", "jdbc:postgresql://172.17.0.2:5432/gpv", "postgres", "postgres");
        LazyList<Reservation> reservations = Reservation.find("reservation_type = ? " +
                "AND alteration_date = ? AND status IS TRUE", "M", LocalDate.now());
        for (Reservation reservation : reservations){
            reservation.set("status", false);
            reservation.save();
        }
        Base.close();

    }

}
