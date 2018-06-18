$(document).ready(function () {
    resetForms();
});

function resetForms() {
    let input = document.getElementsByTagName("input");
    for (let a in input){
        if(input[a].type === "checkbox"){
            input[a].checked = false;
        }
    }

}

function allowSelection(elementId) {
    let dayShiftDiv = document.getElementById(elementId);

    if(document.getElementById(elementId+"checkbox") === null) {

        let checkboxDiv = document.createElement("div");
        checkboxDiv.className = "check-box";
        checkboxDiv.id = elementId + "checkbox";

        let innerDivGoing = document.createElement("div");
        innerDivGoing.className = "individual-check";
        let pGoing = document.createElement("p");
        pGoing.innerText = "Ida";
        innerDivGoing.appendChild(pGoing);

        let checkboxGoing = document.createElement("input");
        checkboxGoing.type = "checkbox";
        checkboxGoing.name = elementId + "Ida";
        innerDivGoing.appendChild(checkboxGoing);


        let innerDivGoingBack = document.createElement("div");
        innerDivGoingBack.className = "individual-check";
        let pBack = document.createElement("p");
        pBack.innerText = "Volta";
        innerDivGoingBack.appendChild(pBack);

        let checkboxBack = document.createElement("input");
        checkboxBack.type = "checkbox";
        checkboxBack.name = elementId + "Volta";
        innerDivGoingBack.appendChild(checkboxBack);


        checkboxDiv.appendChild(innerDivGoing);
        checkboxDiv.appendChild(innerDivGoingBack);
        dayShiftDiv.appendChild(checkboxDiv);

        updateStops(elementId);
    }
    else {
        let checkboxDiv = document.getElementById(elementId+"checkbox");
        checkboxDiv.parentElement.removeChild(checkboxDiv);

        updateStops(elementId);
    }
}

function discoverShifts(dayShift) {
    return document.getElementById(dayShift + "checkbox") !== null;
}

function getPlanId() {
    let urlSplit = getSplittedUrl();
    let id = urlSplit[6];
    return id;
}



function getBaseCityId() {
    let select = document.getElementById("cities");
    return select.options[select.selectedIndex].value;
}

function updateStops(elementId){
    let dayId = elementId.split("-")[0];
    let selectStopsOfDestination = document.getElementById("stopsTarget"+dayId);
    let selectStopsOfBaseCity = document.getElementById("stopsBase"+dayId);

    for (let i = selectStopsOfDestination.childElementCount-1; i >= 0; i--){
        selectStopsOfDestination.removeChild(selectStopsOfDestination.children[i]);
    }

    for (let i = selectStopsOfBaseCity.childElementCount-1; i >= 0; i--){
        selectStopsOfBaseCity.removeChild(selectStopsOfBaseCity.children[i]);
    }

    let jsonSent = {
        morning: discoverShifts(dayId+"-Manha"),
        afternoon: discoverShifts(dayId+"-Tarde"),
        night: discoverShifts(dayId+"-Noite"),
        plan: getPlanId(),
        baseCity: getBaseCityId()
    };

    $.getJSON(getUrlPath("availability")+"availability/stops_of_destination", JSON.stringify(jsonSent), function (data) {
        $.each(data, function (key, val) {
            let option = document.createElement("option");
            $.each(val, function (key, val) {
                if (key.toString() === "id") {
                    option.value = val;
                }
                if (key.toString() === "address") {
                    option.innerText += val;
                }
                if (key.toString() === "time") {
                    let date = new Date(val);
                    let formattedMinutes;
                    let formattedHours;
                    if(date.getUTCMinutes() < 10){
                        formattedMinutes = date.getUTCMinutes() + "0";
                    }
                    else {
                        formattedMinutes = date.getUTCMinutes();
                    }

                    if(date.getUTCHours() < 10){
                        formattedHours =  "0" + date.getUTCHours();
                    }
                    else {
                        formattedHours = date.getUTCHours();
                    }
                    option.innerText += " "+ formattedHours + ":" + formattedMinutes + " ";
                }
            });
            selectStopsOfDestination.appendChild(option);
        });
    });

    $.getJSON(getUrlPath("availability")+"availability/stops_of_base", JSON.stringify(jsonSent), function (data) {
        $.each(data, function (key, val) {
            let option = document.createElement("option");
            $.each(val, function (key, val) {
                if (key.toString() === "id") {
                    option.value = val;
                }
                if (key.toString() === "address") {
                    option.innerText += val;
                }
                if (key.toString() === "time") {
                    let date = new Date(val);
                    let formattedMinutes;
                    let formattedHours;
                    if(date.getUTCMinutes() < 10){
                        formattedMinutes = date.getUTCMinutes() + "0";
                    }
                    else {
                        formattedMinutes = date.getUTCMinutes();
                    }

                    if(date.getUTCHours() < 10){
                        formattedHours =  "0" + date.getUTCHours();
                    }
                    else {
                        formattedHours = date.getUTCHours();
                    }
                    option.innerText += " "+ formattedHours + ":" + formattedMinutes + " ";
                }
            });
            selectStopsOfBaseCity.appendChild(option);
        });
    });
}

function showStops(dayId){
    let divOfStops = document.getElementById("stopsOf"+dayId);
    let buttonDay = document.getElementById("button-"+dayId);
    if(divOfStops.className === "stops-box"){
        divOfStops.style.display = "inherit";
        divOfStops.style.visibility = "visible";
        buttonDay.innerText = "Esconder Paradas";
        divOfStops.className = "stops-box-show";
    }
    else {
        divOfStops.style.display = "none";
        divOfStops.style.visibility = "hidden";
        buttonDay.innerText = "Mostrar Paradas";
        divOfStops.className = "stops-box";

        let select = document.getElementById("stops"+dayId);
        for (let i = select.childElementCount-1; i >= 0; i--){
            select.removeChild(select.children[i]);
        }
    }


}

function formJSON(){
    let days = ["Segunda","Terca","Quarta","Quinta","Sexta","Sabado","Domingo"];
    let shifts = ["Manha","Tarde","Noite"];

    for(let day in days){
        let section = document.getElementById(days[day]);
        for(let shift in shifts) {
            let checkBoxes = document.getElementsByClassName(day + "-" +shift+"checkbox");
            if(checkBoxes !== undefined){
                let going = checkBoxes.getElementsByName(day+" - "+shift+"Ida");
                going.checked;
                let back = checkBoxes.getElementsByName(day+" - "+shift+"Volta");
                back.checked;
            }
        }
    }

}

function updateCities(state) {
    let stateId = state.value;
    let citiesSelect = document.getElementById("cities");

    $.getJSON(getUrlPath("availability")+"city/list", stateId, function (data) {
        $.each(data, function (key, val) {
            let option = document.createElement("option");
            $.each(val, function (key, val) {
                if (key.toString() === "id") {
                    option.value = val;
                }
                if (key.toString() === "name") {
                    option.innerText = val;
                }
            });
            citiesSelect.appendChild(option);
        });
    });
}

function getTurnOfStop(selectedOption) {
    const regex = /(\d{2}):(\d{2})/gm;
    let match = regex.exec(selectedOption);
    let hour = match[0];
    //let minutes = match[1];

    let shiftValues = ["12","18","04"];
    if(hour < shiftValues[0] && hour >= shiftValues[2]){
        return "M";
    }
    if(hour < shiftValues[1] && hour >= shiftValues[0]){
        return "A";
    }
    if(hour >= shiftValues[1]){
        return "N";
    }
    if(hour >= 0 && hour < shiftValues[2]){
        return "N";
    }

}

function tableInteraction(element) {
    let select = document.getElementById("stops"+element.id);
    let tableClass;
    if(element.id.includes("Base")){
        tableClass = "table-base";
    }
    else {
        tableClass = "table-destination";
    }
    let tableList = element.parentElement.getElementsByClassName(tableClass);

    let allowAddition = true;

    if(tableList.item(0).rows.length > 1) {
        for (let i = 1; i < tableList.item(0).rows.length; i++) {
            if (tableList.item(0).rows.item(i).cells.item(1).innerText === select.value) {
                allowAddition = false;
            }
        }
    }

    if(allowAddition) {
        let row = document.createElement("tr");
        let column = document.createElement("td");
        column.innerText = select.options[select.selectedIndex].text;
        row.appendChild(column);

        let idColumn = document.createElement("td");
        idColumn.innerText = select.value;
        idColumn.style.display = "none";

        let turnColumn = document.createElement("td");
        turnColumn.innerText = getTurnOfStop(select.options[select.selectedIndex].text);
        turnColumn.style.display = "none";

        row.appendChild(idColumn);
        row.appendChild(turnColumn);

        let columnDelete = document.createElement("td");
        let deleteButton = document.createElement("button");
        deleteButton.innerText = "Deletar";

        deleteButton.onclick = function () {
            removeStopLine(this)
        };
        columnDelete.appendChild(deleteButton);
        row.appendChild(columnDelete);
        tableList[0].append(row);
    }
    //alert();
}

function removeStopLine(element) {
    element.parentElement.parentElement.parentElement.removeChild(element.parentElement.parentElement);
}



