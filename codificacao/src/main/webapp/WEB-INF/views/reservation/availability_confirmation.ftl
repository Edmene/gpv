<@content for="title">Confirmar Reserva</@content>

<section class="plans">
    <div class="total-value">
        <strong>Valor Total: R$${totalValue}</strong>
    </div>
    <@form action="addReservations" method="post">
        <input type="hidden" name="json" value='${json}'>
        <input type="hidden" name="plan_id" value="${plan}">
        <input type="hidden" name="destination" value="${destination}">
        <input type="hidden" name="reservation_type" value="${reservation_type}">
        <#if reservation_type?contains("P")>
            <#list days as day>
                <#if listOfDates[day_index]??>
                    <div class="dayOfWeek-cell" id="${day}" <#if listOfDates[day_index]?size == 0>style="display: none"</#if>>
                        <div class="label-info">${day}</div>
                        <div class="days-selection">
                            <#list listOfDates[day_index] as date>
                                <input type="radio" name="${day}" value="${date.day}" required="required">
                                <label>${date.day}</label>
                            </#list>
                        </div>
                    </div>
                </#if>
            </#list>
        </#if>
        <button type="submit" id="log-bt">Confirmar</button>
    </@form>
</section>