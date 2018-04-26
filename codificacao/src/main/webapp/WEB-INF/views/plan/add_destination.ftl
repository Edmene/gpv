
<@content for="title">Plano: ${plan.id}</@content>

<select id="select_item">
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
    <input id="item" type="hidden" value="" name="items">
    <input type="hidden" value="${plan.id}" name="plan">
    <input type="submit" value="Confirmar destinos">
</@form>

<script src="${context_path}/js/destinationTable.js" type="text/javascript"></script>
<script src="${context_path}/js/clearDestinationsOnReload.js" type="text/javascript"></script>

<@link_to>Retornar a listagem de planos</@link_to>
