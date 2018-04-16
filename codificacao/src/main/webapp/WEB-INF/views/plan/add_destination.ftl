
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
    </tr>
</table>

<@form action="addDestinations" method="post" name="addDestinations">
    <input id="destination" type="hidden" value="" name="destinations">
    <input type="hidden" value="${plan.id}" name="plan">
    <input type="submit" value="Confirmar destinos">
</@form>

<script>
    var select = document.getElementById("select_destination")

    function addTable() {
        //a.value;
        var table = document.getElementById("table");
        var row = document.createElement("tr");
        var input = document.getElementById("destination");
        var lastPosition = input.value.length;

        var inputContent = input.value.split(",");
        
        function checkValuePresence(value) {
            return value.toString() === select.value.toString();
        }

        function addValues() {
            row.innerHTML = select.options[select.selectedIndex].text;
            row.className = "tr_destination"
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

    $(document).ready(function () {
        resetForms();
    });

    function resetForms() {
        var input = document.getElementById("destination");
        input.value = "";
    }

</script>

<@link_to>Retornar a listagem de planos</@link_to>
