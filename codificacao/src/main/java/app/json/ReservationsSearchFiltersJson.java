package app.json;

public class ReservationsSearchFiltersJson {
    public Integer day, shift, plan_id;

    public ReservationsSearchFiltersJson(Integer day, Integer shift, Integer plan_id){
        this.day = day;
        this.shift = shift;
        this.plan_id = plan_id;
    }
}
