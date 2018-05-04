package app.utils;

import app.enums.Day;
import app.json.ReservationJson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;

public class DateOfDayFinder {
    public ArrayList<ArrayList<String>> datesMapList(ArrayList<ReservationJson> reservationJsonList) {
        //ArrayList<String> dates = new ArrayList<>();
        ArrayList<ArrayList<String>> dayArrayListList = new ArrayList<>();
        Integer month = Calendar.getInstance().get(Calendar.MONTH);

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
            Date date=null;

            try {
                date = new SimpleDateFormat("dd/MM/yyyy").parse(testDate);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

            if(!dayArrayListList.get(dayOfWeek-1).isEmpty()) {
                if(dayArrayListList.get(dayOfWeek-1).get(0) == "s") {
                    dayArrayListList.get(dayOfWeek-1).remove("s");
                    dayArrayListList.get(dayOfWeek-1).add(testDate);
                }
                else {
                    dayArrayListList.get(dayOfWeek-1).add(testDate);
                }
            }
            /*
            if (dayArrayListList.containsKey(Day.values()[dayOfWeek-1])){
                dayArrayListList.get(Day.values()[dayOfWeek-1]).add(testDate);
            }
            */
        }
        return dayArrayListList;

    }
}
