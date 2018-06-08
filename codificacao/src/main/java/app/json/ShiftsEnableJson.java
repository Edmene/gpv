package app.json;

public class ShiftsEnableJson {
    public Boolean morning,afternoon,night;
    public Integer plan;

    public ShiftsEnableJson(Boolean morning, Boolean afternoon, Boolean night, Integer plan){
        this.morning = morning;
        this.afternoon = afternoon;
        this.night = night;
        this.plan = plan;
    }
}
