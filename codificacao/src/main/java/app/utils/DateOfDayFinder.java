package app.utils;

import app.json.ReservationJson;

import java.util.ArrayList;

public class DateOfDayFinder {
    public ArrayList<String> datesArrayList(ArrayList<ReservationJson> reservationJsonList) {
        ArrayList<String> dates = new ArrayList<>();
        return dates;
        //Bloco reutilizado nao ajustado
        /*
        for(Integer a=0;a<mes;a++){
            oosheet.setValueAt("Dia",1, lineIndex);
            oosheet.setValueAt(listaMes[a],2, lineIndex);
            String semana = "";
            String mesChecagem = "";
            Integer mesTeste=mesInicial+a;
            if(mesTeste > 12){
                ano=ano+1;
                mesTeste=mesTeste-12;
            }
            if(mesTeste<=9){
                mesChecagem="0"+mesTeste.toString();
            }
            else{
                mesChecagem=mesTeste.toString();
            }
            for(Integer i=1;i<=31;i++){

                String[] dataTeste={"",""};
                if(i<=9){
                    dataTeste[1]="0"+i.toString();
                }
                else{
                    dataTeste[1]=i.toString();
                }
                dataTeste[1]=dataTeste[1]+"/"+mesChecagem+"/"+ano.toString();
                Date data=null;
                try {
                    data = new SimpleDateFormat("dd/MM/yyyy").parse(dataTeste[1]);
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar c = Calendar.getInstance();
                c.setTime(data);
                int diaDaSemana = c.get(Calendar.DAY_OF_WEEK);
                if(diaDaSemana == dias[0]){
                    semana=dataTeste[1];
                    int diaDaSemana2 = 0;
                    if(i>=28){
                        for(int q=i;q<=31;q++){
                            String[] dataTeste1={"",""};
                            if(q<=9){
                                dataTeste1[1]="0"+q;
                            }
                            else{
                                dataTeste1[1]=String.valueOf(q);
                            }
                            dataTeste1[1]=dataTeste1[1]+"/"+mesChecagem+"/"+ano;
                            Date data2=null;
                            try {
                                data2 = new SimpleDateFormat("dd/MM/yyyy").parse(dataTeste1[1]);
                            }
                            catch (ParseException e) {
                                e.printStackTrace();
                            }
                            Calendar c2 = Calendar.getInstance();
                            c2.setTime(data2);
                            diaDaSemana2 = c2.get(Calendar.DAY_OF_WEEK);
                            if(diaDaSemana2 == dias[1]){
                                break;
                            }
                        }
                        int interrompeProcura = dias[1]+1;
                        if(dias[1] == 7){
                            interrompeProcura=1;
                        }
                        if(diaDaSemana2 != interrompeProcura){
                            for(int teste=1;teste<=5;teste++){
                                String[] dataMesSeguinte = {"", ""};
                                int mesSeguinte=(Integer.parseInt(mesChecagem)+1);
                                String mesSeg = "";
                                String diaSeg = "";
                                if(mesSeguinte<=9){
                                    mesSeg="0"+mesSeguinte;
                                }
                                else{
                                    mesSeg=String.valueOf(mesSeguinte);
                                }
                                if(teste<=9){
                                    diaSeg="0"+teste;
                                }
                                else{
                                    diaSeg=String.valueOf(teste);
                                }
                                dataMesSeguinte[0]=diaSeg+"/"+mesSeg+"/"+ano;
                                try {
                                    data = new SimpleDateFormat("dd/MM/yyyy").parse(dataMesSeguinte[0]);
                                }
                                catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                c.setTime(data);
                                int diaDaSemanaMseg = c.get(Calendar.DAY_OF_WEEK);
                                if(diaDaSemanaMseg == dias[1]){
                                    semana = semana +" e "+ dataMesSeguinte[0];
                                    continue;
                                }
                            }
                        }
                    }
                }
                if(diaDaSemana == dias[1]){
                    semana=semana+" e "+dataTeste[1];
                    if(semana.matches("[0-9*][0-9*]/[0-9*][0-9*]/[0-9*][0-9*][0-9*][0-9*] e [0-9*][0-9*]/[0-9*][0-9*]/[0-9*][0-9*][0-9*][0-9*]")){
                    }
                    else{
                        for(int teste=26;teste<=31;teste++){
                            String[] dataMesAnterior = {"", ""};
                            int mesAnterior=(Integer.parseInt(mesChecagem)-1);
                            String mesAnt = "";
                            if(mesAnterior<=9){
                                mesAnt="0"+mesAnterior;
                            }
                            else{
                                mesAnt=String.valueOf(mesAnterior);
                            }
                            dataMesAnterior[0]=teste+"/"+mesAnt+"/"+ano;
                            try {
                                data = new SimpleDateFormat("dd/MM/yyyy").parse(dataMesAnterior[0]);
                            }
                            catch (ParseException e) {
                                e.printStackTrace();
                            }
                            c.setTime(data);
                            int diaDaSemanaMan = c.get(Calendar.DAY_OF_WEEK);
                            if(diaDaSemanaMan == dias[0]){
                                semana = dataMesAnterior[0] + semana;
                                semana="";
                                continue;
                            }
                        }
                    }
                }
                if(semana.contains("e")){
                    numSemanas++;
                    semana="";
                }
            }
        }
        */
    }
}
