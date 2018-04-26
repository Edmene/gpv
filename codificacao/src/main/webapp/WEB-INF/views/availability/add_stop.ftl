
<@content for="title">Adicionando paradas</@content>

<select id="select_item">
    <#list stops as stop>
        <option value="${stop.id}" label="${stop.address_id}">${stop.address_id}</option>
    </#list>
</select>

<button id="select_add" onclick="addTable()">Adicionar parada</button>


<table id="table">
    <tr>
        <td>Destino</td>
        <td>Remover</td>
    </tr>
</table>

<@form action="addStops" method="post" name="addStops">
    <input id="item" type="hidden" value="" name="items">
    <input type="hidden" value="${plan}" name="plan">
    <input type="hidden" value="${day}" name="day">
    <input type="hidden" value="${shift}" name="shift">
    <input type="hidden" value="${driver}" name="driver">
    <input type="hidden" value="${vehicle}" name="vehicle">
    <input type="submit" value="Confirmar paradas">
</@form>

<script src="${context_path}/js/destinationTable.js" type="text/javascript"></script>
<script src="${context_path}/js/clearDestinationsOnReload.js" type="text/javascript"></script>

