package app.utils;

import app.models.Plan;

import java.util.ArrayList;

public class TotalValueOfPlanSelection {
    ArrayList<ArrayList<String>> listOfDates;
    private Float totalValue = 0.0f;

    public TotalValueOfPlanSelection(ArrayList<ArrayList<String>> listOfDates){
        this.listOfDates = listOfDates;
    }

    public Float calculateTotalValue(Boolean type, Plan plan){
        if(type){
            for(int i=0;i<listOfDates.size();i++){
                if(!listOfDates.get(i).isEmpty()){
                    totalValue += plan.getFloat("ticket_price");
                }
            }
        }
        else {
            for(int i=0;i<listOfDates.size();i++){
                if(!listOfDates.get(i).isEmpty()){
                    totalValue += plan.getFloat("ticket_price")*listOfDates.get(i).size();
                }
            }
        }
        return totalValue;
    }
}
