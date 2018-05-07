<section id="plans">
    <@form action="addReservations" method="post">
        <#assign a = 0>
        <#list days as day>
            <#if listOfDates[a]??>
                <div class="dayOfWeek-cell" id="${day}">
                    ${day}
                    <#list listOfDates[a] as date>
                        ${date.day} <input type="radio" name="${day}" value="${date.day}">
                    </#list>
                </div>
            </#if>
            <#assign a++>
        </#list>
        <input type="hidden" name="json" value="${json}">
        <input type="hidden" name="plan" value="${plan}">
        <input type="hidden" name="destination" value="${destination}">
        <input type="hidden" name="reservation_type" value="${reservation_type}">
    </@form>
    <div class="total-value">
        ${totalValue}
    </div>
</section>