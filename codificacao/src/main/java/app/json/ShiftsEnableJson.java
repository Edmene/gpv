package app.json;

public class ShiftsEnableJson {
    public Boolean morning,afternoon,night;

    public ShiftsEnableJson(){}

    public ShiftsEnableJson(Boolean morning, Boolean afternoon, Boolean night){
        this.morning = morning;
        this.afternoon = afternoon;
        this.night = night;
    }
}
