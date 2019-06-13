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

    public TotalResult calculateTotalValue(CalculationMethod type, Plan plan){
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
        return new TotalResult(totalValue);
    }

    private class TotalResult{
        private float total;

        TotalResult(float total){
            this.total = total;
        }

        public float getTotal(){
            return this.total;
        }

        public void setTotal(float total){
            this.total = total;
        }
    }
}
