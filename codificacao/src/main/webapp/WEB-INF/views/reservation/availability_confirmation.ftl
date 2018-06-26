<@content for="title">Confirmar Reserva</@content>

<section id="plans">
    <@form action="addReservations" method="post">
        <input type="hidden" name="json" value='${json}'>
        <input type="hidden" name="plan_id" value="${plan}">
        <input type="hidden" name="destination" value="${destination}">
        <input type="hidden" name="reservation_type" value="${reservation_type}">
        <#if reservation_type?contains("P")>
            <#assign a = 0>
            <#list days as day>
                <#if listOfDates[a]??>
                    <div class="dayOfWeek-cell" id="${day}">
                        ${day}
                        <#list listOfDates[a] as date>
                            ${date.day} <input type="radio" name="${day}" value="${date.day}" required="required">
                        </#list>
                    </div>
                </#if>
                <#assign a++>
            </#list>
        </#if>
        <button type="submit">Confirmar</button>
    </@form>
    <div class="total-value">
    ${totalValue}
    </div>
</section>