package app.json;

import app.models.ActivePeriod;
import app.models.DriverVehicle;

import java.sql.Date;
import java.time.LocalDate;

public class ActivePeriodJson {
    public Integer key;
    public LocalDate initialDate, finalDate;

    public ActivePeriodJson(){}

    public ActivePeriodJson(LocalDate initialDate, LocalDate finalDate){
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    public ActivePeriodJson(ActivePeriod activePeriod){
        this.key = (Integer) activePeriod.getId();
        this.initialDate = LocalDate.from(activePeriod.getDate("initial_date").toInstant());
        this.finalDate = LocalDate.from(activePeriod.getDate("final_date").toInstant());
    }

    public void setAttributesOfActivePeriod(ActivePeriod activePeriod){
        activePeriod.set("id", this.key,
                "initial_date", Date.valueOf(this.initialDate),
                "final_date", Date.valueOf(this.finalDate));
    }
}
