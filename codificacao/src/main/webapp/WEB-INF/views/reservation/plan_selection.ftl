<section id="plans">
        <#list destinationPlanMapList as destinationPlanMap>
            <@link_to action="availability_selection" id=plan.id class="destination_link">
                <div class="plan">
                    ${destinationPlanMap.plan.id}
                    Mostrar informacoes do plano
                </div>
            </@link_to>
        </#list>
</section>