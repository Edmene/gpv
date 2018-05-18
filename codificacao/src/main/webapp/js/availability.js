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
    }
    else {
        let checkboxDiv = document.getElementById(elementId+"checkbox");
        checkboxDiv.parentElement.removeChild(checkboxDiv);
    }
}

function showStops(dayId){
    let divOfStops = document.getElementById("stopsOf"+dayId);
    let buttonDay = document.getElementById("button-"+dayId);
    if(divOfStops.className === "stops-box"){
        divOfStops.style.display = "inherit";
        divOfStops.style.visibility = "visible";
        buttonDay.innerText = "Esconder Paradas"
        divOfStops.className = "stops-box-show";

        //let req = new XMLHttpRequest();
        //let path = window.location.pathname;

        /*
        req.open("GET", "http://172.17.0.3:8080/gpv-1.0-SNAPSHOT/availability/stops");
        req.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
        req.send();
        */
        //let responseValues = JSON.parse(req.responseText);
        /*
        let jsonRequest = jQuery.getJSON("http://172.17.0.3:8080/gpv-1.0-SNAPSHOT/availability/stops");
        let json;
        json = jsonRequest.responseJSON;

        console.log(jsonRequest.responseJSON);

        //let select = document.getElementById("stops"+dayId);
        for (let element in json){
            alert(json[element].time);
            //select.appendChild(document.createElement("option").value=element.time)
        }
        */

        let select = document.getElementById("stops"+dayId);

        $.getJSON("http://172.17.0.3:8080/gpv-1.0-SNAPSHOT/availability/stops", function(data) {
            console.log(data);
            var items = [];
            $.each(data, function( key, val ) {
                //items.push( "<li id='" + key + "'>" + val + "</li>" );
                //alert(key+" "+val[key]);
                let option = document.createElement("option");
                $.each(val, function (key, val) {
                    if(key.toString() === "id"){
                        option.value = val;
                    }
                    if(key.toString() === "parents"){
                        option.innerText += val.addresses[0].name.toString();
                    }
                    if(key.toString() === "time"){
                        option.innerText += val;
                    }
                    //console.log(val);
                })
                select.appendChild(option);
            });
            /*
            $( "<ul/>", {
                "class": "my-new-list",
                html: items.join( "" )
            }).appendTo( "body" );
            */
        });
    }
    else {
        divOfStops.style.display = "none";
        divOfStops.style.visibility = "hidden";
        buttonDay.innerText = "Mostrar Paradas";
        divOfStops.className = "stops-box";
    }


}





