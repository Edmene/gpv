package app.utils;

import app.enums.Day;
import app.json.ReservationJson;
import app.models.ActivePeriod;
import org.javalite.activejdbc.LazyList;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class DateOfDayFinder {
    public ArrayList<ArrayList<Map<String,Object>>> datesArrayList(ArrayList<ReservationJson> reservationJsonList) {
        ArrayList<ArrayList<String>> dayArrayListList = new ArrayList<>();

        ArrayList<ArrayList<Map<String,Object>>> dayArrayListListMap = new ArrayList<>();
        Integer month = YearMonth.now().getMonthValue();


        for(Day day : Day.values()){
            dayArrayListList.add(new ArrayList<>());
            dayArrayListListMap.add(new ArrayList<>());
        }

        for(ReservationJson reservationJson : reservationJsonList){
            if(dayArrayListList.get(reservationJson.day).size() == 0 ||
                    dayArrayListList.get(reservationJson.day).size() > 0) {
                dayArrayListList.get(reservationJson.day).add(
                        String.valueOf(reservationJson.shift+
                                reservationJson.direction+reservationJson.day));
            }
        }

        for(Integer i = 1; i<=31; i++) {
            String testDate = "";
            if (i <= 9) {
                testDate = "0" + i.toString();
            } else {
                testDate = i.toString();
            }
            if (month < 10) {
                testDate += "/0" + month.toString();
            } else {
                testDate += "/" + month.toString();
            }
            testDate += "/" + Year.now().getValue();
            Date dateTest = Date.valueOf(LocalDate.from(DateTimeFormatter.ofPattern("dd/MM/yyyy").parse(testDate)));

            LazyList<ActivePeriod> activePeriods = ActivePeriod.find("plan_id = ? AND ? BETWEEN initial_date AND final_date",
                    reservationJsonList.get(0).planId, dateTest);

            if (activePeriods.size() != 0) {
                TemporalAccessor date = DateTimeFormatter.ofPattern("dd/MM/yyyy").parse(testDate);

                int dayOfWeek = DayOfWeek.from(date).getValue();

                if (!dayArrayListList.get(dayOfWeek - 1).isEmpty()) {
                    Integer numberOfSelectionsOnDay = dayArrayListList.get(dayOfWeek - 1).size();
                /*
                if(dayArrayListList.get(dayOfWeek-1).get(0) == "s") {
                    dayArrayListList.get(dayOfWeek-1).remove("s");
                    dayArrayListList.get(dayOfWeek-1).add(testDate);
                }
                else {
                    dayArrayListList.get(dayOfWeek-1).add(testDate);
                }
                */
                    TreeMap<String, Object> dayMap = new TreeMap<>();
                    dayMap.put("day", testDate);
                    dayMap.put("nTimes", numberOfSelectionsOnDay);
                    dayArrayListListMap.get(dayOfWeek - 1).add(dayMap);
                }
            }
        }
        return dayArrayListListMap;

    }
}
