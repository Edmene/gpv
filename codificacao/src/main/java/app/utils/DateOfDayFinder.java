package app.utils;

import app.enums.Day;
import app.json.ReservationJson;

import java.time.DayOfWeek;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

public class DateOfDayFinder {
    public ArrayList<ArrayList<String>> datesArrayList(ArrayList<ReservationJson> reservationJsonList) {
        ArrayList<ArrayList<String>> dayArrayListList = new ArrayList<>();
        Integer month = YearMonth.now().getMonthValue();


        for(Day day : Day.values()){
            dayArrayListList.add(new ArrayList<>());
        }

        for(ReservationJson reservationJson : reservationJsonList){
            if(dayArrayListList.get(reservationJson.day).size() == 0) {
                dayArrayListList.get(reservationJson.day).add("s");
            }
        }

        for(java.lang.Integer i = 1; i<=31; i++){
            String testDate="";
            if(i<=9){
                testDate="0"+i.toString();
            }
            else{
                testDate=i.toString();
            }
            if(month < 10){
                testDate+="/0"+month.toString();
            }
            else {
                testDate+="/"+month.toString();
            }
            testDate+="/"+Year.now().getValue();

            TemporalAccessor date = DateTimeFormatter.ofPattern("dd/MM/yyyy").parse(testDate);
            int dayOfWeek = DayOfWeek.from(date).getValue();

            if(!dayArrayListList.get(dayOfWeek-1).isEmpty()) {
                if(dayArrayListList.get(dayOfWeek-1).get(0) == "s") {
                    dayArrayListList.get(dayOfWeek-1).remove("s");
                    dayArrayListList.get(dayOfWeek-1).add(testDate);
                }
                else {
                    dayArrayListList.get(dayOfWeek-1).add(testDate);
                }
            }
        }
        return dayArrayListList;

    }
}
