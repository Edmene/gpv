package app.utils;

import app.enums.CalculationMethod;
import app.models.Plan;

import java.util.ArrayList;
import java.util.Map;

public class TotalValueOfPlanSelection {
    ArrayList<ArrayList<Map<String,Object>>> listOfDates;
    private Float totalValue = 0.0f;

    public TotalValueOfPlanSelection(ArrayList<ArrayList<Map<String,Object>>> listOfDates){
        this.listOfDates = listOfDates;
    }

    public Float calculateTotalValue(CalculationMethod type, Plan plan){
        if(type == CalculationMethod.T){
            for(int i=0;i<listOfDates.size();i++){
                if(!listOfDates.get(i).isEmpty()){
                    totalValue += plan.getFloat("ticket_price")
                            * (Integer) listOfDates.get(i).get(0).get("nTimes");
                }
            }
        }
        else {
            for (int i = 0; i < listOfDates.size(); i++) {
                if (!listOfDates.get(i).isEmpty()) {
                    totalValue += plan.getFloat("daily_value") * listOfDates.get(i).size()
                            * (Integer) listOfDates.get(i).get(0).get("nTimes"); }
            }
        }
        return totalValue;
    }
}
