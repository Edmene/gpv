
<@content for="title">Plano: ${plan.id}</@content>

<select id="select_destination">
    <#list destinations as destination>
        <option value="${destination.id}" label="${destination.name}">${destination.name}</option>
    </#list>
</select>

<button id="select_add" onclick="addTable()">Adicionar destino</button>


<table id="table">
    <tr>
        <td>Destino</td>
        <td>Remover</td>
    </tr>
</table>

<@form action="addDestinations" method="post" name="addDestinations">
    <input id="destination" type="hidden" value="" name="destinations">
    <input type="hidden" value="${plan.id}" name="plan">
    <input type="submit" value="Confirmar destinos">
</@form>

<script>
    let select = document.getElementById("select_destination")

    function addTable() {
        //a.value;
        let table = document.getElementById("table");
        let row = document.createElement("tr");
        let input = document.getElementById("destination");
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

        let input = document.getElementById("destination");
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
                newValues += inputContent[i]+",";
            }
        }
        input.value = newValues;
        row.parentElement.removeChild(row);

    }

    $(document).ready(function () {
        resetForms();
    });

    function resetForms() {
        var input = document.getElementById("destination");
        input.value = "";
    }

</script>

<@link_to>Retornar a listagem de planos</@link_to>
