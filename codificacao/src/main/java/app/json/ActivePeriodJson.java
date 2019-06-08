package app.json;

import app.models.ActivePeriod;
import app.models.DriverVehicle;
import app.utils.JavaScriptDateDeserializer;
import app.utils.JavaScriptDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Date;
import java.time.LocalDate;

public class ActivePeriodJson {
    public Integer key, planKey;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    @JsonSerialize(using = JavaScriptDateSerializer.class)
    @JsonDeserialize(using = JavaScriptDateDeserializer.class)
    public LocalDate initialDate, finalDate;

    public ActivePeriodJson(){}

    public ActivePeriodJson(LocalDate initialDate, LocalDate finalDate, Integer planKey){
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.planKey = planKey;
    }

    public ActivePeriodJson(ActivePeriod activePeriod){
        this.key = (Integer) activePeriod.getId();
        this.initialDate = LocalDate.from(activePeriod.getDate("initial_date").toInstant());
        this.finalDate = LocalDate.from(activePeriod.getDate("final_date").toInstant());
        this.planKey = activePeriod.getInteger("plan_id");
    }

    public void setAttributesOfActivePeriod(ActivePeriod activePeriod){
        activePeriod.set("id", this.key,
                "initial_date", Date.valueOf(this.initialDate),
                "final_date", Date.valueOf(this.finalDate),
                "plan_id", this.planKey);
    }
}
