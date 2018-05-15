let dayShiftDiv = document.getElementById("SegundaManha");

let checkboxDiv = document.createElement("div");
checkboxDiv.className = "check-box";
let innerDiv = document.createElement("div");
innerDiv.className = "individual-check";
let p = document.createElement("p")
p.innerText = "Ida";
innerDiv.appendChild(p);

let checkbox = document.createElement("input");
checkbox.type = "checkbox";
checkbox.name = "SegundaManha";
innerDiv.appendChild(checkbox);

checkboxDiv.appendChild(innerDiv);
dayShiftDiv.appendChild(checkboxDiv);



