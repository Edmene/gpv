let select = document.getElementById("select_item")

function addTable() {
    //a.value;
    let table = document.getElementById("table");
    let row = document.createElement("tr");
    let input = document.getElementById("item");
    let lastPosition = input.value.length;

    let inputContent = input.value.split(",");

    function checkValuePresence(value) {
        return value.toString() === select.value.toString();
    }

    function addValues() {
        let column = document.createElement("td");
        let columnDelete = document.createElement("td");
        let deleteButton = document.createElement("button");
        deleteButton.innerText = "Deletar"

        columnDelete.appendChild(deleteButton);

        column.innerText = select.options[select.selectedIndex].text;
        row.appendChild(column);
        row.appendChild(columnDelete);
        row.id = select.value.toString();
        row.className = "tr_destination";
        //deleteButton.addEventListener("onclick(removeLine("+row.id+"));
        deleteButton.onclick = function() { removeLine(row.id) };
        table.appendChild(row);

        if (input[lastPosition - 1] !== "," && lastPosition != 0) {
            input.value += ("," + select.value.toString());
        }
        else {
            input.value = select.value.toString();
        }
    }

    if(lastPosition === 0){
        addValues();
    }
    else {
        if (inputContent.find(checkValuePresence) === undefined) {
            addValues();
        }
    }
}

function removeLine(lineId){

    let input = document.getElementById("item");
    let inputContent = input.value.split(",");

    function checkValuePresence(value) {
        return value.toString() === lineId.toString();
    }

    let row = document.getElementById(lineId.toString());

    for (let i = 0;i < inputContent.length; i++) {
        if(inputContent[i].toString() === lineId.toString()){
            inputContent[i] = "";
        }
    }

    let newValues = "";
    for (let i = 0;i < inputContent.length; i++) {
        if(inputContent[i] !== ""){
            if(inputContent[i+1] !== undefined && inputContent[i+1] !== "") {
                newValues += inputContent[i] + ",";
            }
            else {
                newValues += inputContent[i];
            }
        }
        else{
            if((inputContent[i+1] !== undefined && inputContent[i+1] !== "")){
                if(i > 0){
                    newValues += ","
                }
            }
        }
    }
    input.value = newValues;
    row.parentElement.removeChild(row);

}