
<@content for="title">Remocao de destinos de plano</@content>
<#assign plan = 0, destinations="", loops=0>

<table id="table">
    <tr>
        <td>Destino</td>
        <td>Remover</td>
    </tr>
    <#list destinationsPlan as destination>
        <tr id="${destination.destination_id}">
            <td>${destination.destination_id}</td>
            <td>
                <button onclick="removeLine(${destination.destination_id})">Remover</button>
            </td>
        </tr>
        <#assign plan = destination.plan_id?int>
        <#if loops < destinationsPlan?size - 1>
            <#assign destinations += destination.destination_id+",", loops++>
        <#else>
            <#assign destinations += destination.destination_id, loops++>
        </#if>
    </#list>
</table>
<@form action="rmDestinations" method="post" name="rmDestinations">
    <input id="destination" type="hidden" value="${destinations}" name="destinations">
    <input type="hidden" value="${plan}" name="plan">
    <input type="submit" value="Confirmar destinos">
</@form>
<script src="${context_path}/js/destinationTable.js" type="text/javascript"></script>

<@link_to>Retornar a listagem de planos</@link_to>
