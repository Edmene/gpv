<@content for="title">Selecionar plano</@content>

<section id="plans">
        <#list destinationPlanMapList as destinationPlanMap>
            <@form action="availability_selection" method="post" class="destination_link">
                <div class="plan">
                    <button type="submit" class="bt-a">${destinationPlanMap.plan.id}</button>
                    <input type="hidden" value="${destinationPlanMap.plan.id}" name="plan">
                    <input type="hidden" value="${destination}" name="destination">
                    <#if destinationPlanMap.plan.availability_condition == "A">
                        <strong>Custo diario: ${destinationPlanMap.plan.daily_value}</strong>
                        <strong>Preco da passagem: ${destinationPlanMap.plan.ticket_price}</strong>
                    <#elseif destinationPlanMap.plan.availability_condition == "M">
                        <strong>Custo diario: ${destinationPlanMap.plan.daily_value}</strong>
                    <#else>
                        <strong>Preco da passagem: ${destinationPlanMap.plan.ticket_value}</strong>
                    </#if>
                </div>
            </@form>
        </#list>
</section>