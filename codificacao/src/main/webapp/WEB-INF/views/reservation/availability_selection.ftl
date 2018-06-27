<script src="${context_path}/js/checkSelection.js" type="text/javascript"></script>
<@content for="title">Selecionar Reservas</@content>

<section id="plans">
    <#list days as day>
        <div class="dayOfWeek-cell" id="${day}"
            <#if availabilitiesSubSets[day_index][0]?size +
            availabilitiesSubSets[day_index][1]?size +
            availabilitiesSubSets[day_index][2]?size == 0>
                style="display: none"
            </#if>>
            <div class="day-cell-label">${day}</div>
            <#list shifts as shift>
                <div class="shift-cell" id="${day}${shift}"
                    <#if availabilitiesSubSets[day_index][shift_index]?size == 0>
                        style="display: none"
                    </#if>>
                    <div class="shift-cell-label">${shift}</div>
                    <#list directions as direction>
                        <div class="direction-cell" id="${day}${shift}${direction}">
                            <div>${direction}</div>
                            <#if availabilitiesSubSets[day_index][shift_index]??>
                                <div class="stops-cell">
                                <#list availabilitiesSubSets[day_index][shift_index] as subSet>
                                    <#if subSet.direction??>
                                        <#if direction?index == subSet.direction>
                                            <div class="stop-cell">
                                            <input type="radio" name="${day}${shift}${direction}"
                                                   value="${subSet.driver_id},${subSet.vehicle_id},${subSet.stop_id}">
                                                <label>${subSet.address_name} - ${subSet.time?string["HH:mm"]}</label>
                                            </div>
                                        </#if>
                                    </#if>
                                </#list>
                                </div>
                            </#if>
                        </div>
                    </#list>
                </div>
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