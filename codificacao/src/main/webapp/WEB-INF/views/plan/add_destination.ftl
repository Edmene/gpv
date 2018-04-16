
<@content for="title">Plano: ${plan.id}</@content>

<select id="select_destination">
    <#list destinations as destination>
        <option value="${destination.id}">${destination.name}</option>
    </#list>
</select>


<table>
    <tr>
        <td>Destino</td>
    </tr>
    <tr>
        <td id="destination_name"></td>
    </tr>
</table>

<script>
    var select = document.getElementById("select_destination")

    function addTable() {
        a.value;
        var table = document.getElementById("table");
        var row = document.createElement("tr");
        row.innerHTML = "Teste";
        table.appendChild(row);
    }

</script>

<@link_to>Retornar a listagem de planos</@link_to>
