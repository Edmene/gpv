package app.json;

import app.models.Plan;

public class PlanJson {
    public Integer key;
    public Short availableReservations;
    public Float ticketValue, dailyValue;
    public char availableCondition;

    public PlanJson(){}

    public PlanJson(Short availableReservations, Float ticketValue, Float dailyValue,
                    char availableCondition){
        this.availableCondition = availableCondition;
        this.availableReservations = availableReservations;
        this.ticketValue = ticketValue;
        this.dailyValue = dailyValue;
    }

    public PlanJson(Plan plan){
        this.key = (Integer) plan.getId();
        this.availableReservations = plan.getShort("available_reservations");
        this.ticketValue = plan.getFloat("ticket_value");
        this.dailyValue = plan.getFloat("daily_value");
        this.availableCondition = plan.getString("availability_condition").charAt(0);
    }

    public void setAttributesOfPlan(Plan plan){
        plan.set("id", this.key,
                "available_reservations", this.availableReservations,
                "available_condition", this.availableCondition,
                "ticket_value", this.ticketValue,
                "daily_value", this.dailyValue);
    }
}
