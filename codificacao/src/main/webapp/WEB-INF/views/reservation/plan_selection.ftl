<@content for="title">Selecionar plano</@content>

<section id="plans">
        <#list destinationPlanMapList as destinationPlanMap>
            <@form action="availability_selection" method="post" class="destination_link">
                <div class="plan">
                    <button type="submit">${destinationPlanMap.plan.id}</button>
                    <input type="hidden" value="${destinationPlanMap.plan.id}" name="plan">
                    <input type="hidden" value="${destination}" name="destination">
                    Mostrar informacoes do plano
                </div>
            </@form>
        </#list>
</section>