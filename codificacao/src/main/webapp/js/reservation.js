function getPlanId() {
    let urlSplit = getSplittedUrl();
    return urlSplit[6];
}

function updatePassengers(){

    let selectedDay = document.getElementById("day-filter");
    let selectedShift = document.getElementById("shift-filter");

    let filter = {
        day: selectedDay.value,
        shift: selectedShift.value,
        plan_id: getPlanId()
    };

    $.getJSON(getUrlPath("reservation")+"reservation/filtered_list", JSON.stringify(filter), function (data) {
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