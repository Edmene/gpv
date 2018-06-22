function getPlanId() {
    let urlSplit = getSplittedUrl();
    return urlSplit[6];
}

function updatePassengers(){

    let tableBody = document.getElementsByTagName("tbody");
    tableBody = tableBody.item(0);

    for(let item in tableBody.children) {
        tableBody.removeChild(tableBody.children[item]);
    }

    let selectedDay = document.getElementById("day-filter");
    let selectedShift = document.getElementById("shift-filter");

    let filter = {
        day: selectedDay.value,
        shift: selectedShift.value,
        plan_id: getPlanId()
    };

    let formUrl = getUrlPath("reservation", true)+"reservation/reservation_list";

    $.getJSON(getUrlPath("reservation")+"reservation/filtered_list", JSON.stringify(filter), function (data) {
        $.each(data, function (key, val) {

            let row = document.createElement("tr");

            let tdName = document.createElement("td");

            let tdDetails = document.createElement("td");

            let form = document.createElement("form");
            let inputPassenger = document.createElement("input");
            let inputPlan = document.createElement("input");
            let button = document.createElement("button");
            button.type = "submit";
            button.innerText = "Mostrar";
            form.action = formUrl;
            form.method = "get";

            inputPassenger.name = "passenger_id";
            inputPassenger.type = "hidden";
            inputPlan.name = "plan_id";
            inputPlan.type = "hidden";

            form.appendChild(inputPassenger);
            form.appendChild(inputPlan);
            form.appendChild(button);

            tdDetails.appendChild(form);

            let tdUserPlans = document.createElement("td");

            let linkToUserDetails = document.createElement("a");
            linkToUserDetails.href = getUrlPath("reservation", true)+"passenger/list_plan/";
            linkToUserDetails.innerText = "Listar";

            tdUserPlans.appendChild(linkToUserDetails);

            $.each(val, function (key, val) {

                if (key.toString() === "plan_id") {
                    inputPlan.value = val;
                }
                if (key.toString() === "passenger_id") {
                    inputPassenger.value = val;
                    linkToUserDetails.href += val;
                }

                if(key.toString() === "passenger"){
                    tdName.innerText = val;
                }

            });
            row.appendChild(tdName);
            row.appendChild(tdDetails);
            row.appendChild(tdUserPlans);

            tableBody.appendChild(row);
        });
    });
}