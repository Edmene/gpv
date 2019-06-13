package app.json;

public class ShiftsEnableJson {
    public Boolean morning,afternoon,night;

    public ShiftsEnableJson(Boolean morning, Boolean afternoon, Boolean night, Integer plan, Integer baseCity){
        this.morning = morning;
        this.afternoon = afternoon;
        this.night = night;
    }
}
