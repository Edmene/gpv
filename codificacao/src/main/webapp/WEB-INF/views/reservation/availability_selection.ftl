<script src="${context_path}/js/checkSelection.js" type="text/javascript"></script>
<@content for="title">Selecionar Reservas</@content>

<section id="plans">
    <#assign a = 0>
    <#list days as day>
        <div class="dayOfWeek-cell" id="${day}">
            <#list shifts as shift>
                <div class="shift-cell" id="${day}${shift}">
                    ${day} - ${shift}
                    <#list directions as direction>
                        <div class="direction-cell" id="${day}${shift}${direction}">
                            ${direction}
                            <#if availabilitiesSubSets[a]??>
                                <#list availabilitiesSubSets[a] as subSet>
                                    <#if subSet.direction??>
                                        <#if direction?index == subSet.direction>
                                            ${subSet.address_name} - ${subSet.time?string["HH:mm"]}
                                            <input type="radio" name="${day}${shift}${direction}"
                                                   value="${subSet.driver_id},${subSet.vehicle_id},${subSet.stop_id}">
                                        </#if>
                                    </#if>
                                </#list>
                            </#if>
                        </div>
                    </#list>
                </div>
            <br>
                <#assign a++>
            </#list>
        </div>
    </#list>
    <@form action="availability_confirmation" method="post">
        <select name="reservation_type">
            <#if plan.availability_condition == "A">
                <option value="M">Mensal</option>
                <option value="P">Passagem</option>
                <#else>
                <#if plan.availability_condition == "M">
                    <option value="M">Mensal</option>
                    <#else>
                        <option value="P">Passagem</option>
                </#if>
            </#if>
        </select>
        <input type="hidden" name="plan" value="${plan.id}">
        <input type="hidden" name="destination" value="${destination}">
        <input type="hidden" id="json" name="json" value="">
        <input type="checkbox" id="confirm-checkbox" onclick="checkSelectedOptions()">
        <button id="btn-confirmation" disabled="disabled">Confirmar Selecao</button>
    </@form>
</section>