
<@content for="title">Adicionando paradas</@content>
<#assign shiftValues=[12,18,04] shift_ordinal=0/>
<#if shift?contains("Tarde")><#assign shift_ordinal = 1/>
<#elseif shift?contains("Noite")><#assign shift_ordinal = 2/></#if>
<select id="select_item">
    <#list stops as stop>
        <#if shift_ordinal == 0>
            <#if stop.time?string["HH"]?number < shiftValues[shift_ordinal]>
                <option value="${stop.id}" label="${stop.address_id}">${stop.time?string["HH:mm"]}|${stop.address.name} ${stop.address.extra}</option>
            </#if>
            <#elseif shift_ordinal == 1>
                <#if stop.time?string["HH"]?number < shiftValues[shift_ordinal] && shiftValues[shift_ordinal-1] < stop.time?string["HH"]?number >
                    <option value="${stop.id}" label="${stop.address_id}">${stop.time?string["HH:mm"]}|${stop.address.name} ${stop.address.extra}</option>
                </#if>
            <#elseif shift_ordinal == 2>
                <#if shiftValues[1] < stop.time?string["HH"]?number && shift_ordinal == 2>
                    <option value="${stop.id}" label="${stop.address_id}">${stop.time?string["HH:mm"]}|${stop.address.name} ${stop.address.extra}</option>
                </#if>
        </#if>

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
    <input type="hidden" value="${direction}" name="direction">
    <input type="hidden" value="${driver}" name="driver">
    <input type="hidden" value="${vehicle}" name="vehicle">
    <input type="submit" value="Confirmar paradas">
</@form>

<script src="${context_path}/js/destinationTable.js" type="text/javascript"></script>
<script src="${context_path}/js/clearDestinationsOnReload.js" type="text/javascript"></script>

