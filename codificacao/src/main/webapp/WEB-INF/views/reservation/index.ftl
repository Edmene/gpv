<@content for="title">Selecionar destino</@content>

<section class="list-items">
        <#list destinations as destination>
            <@link_to action="plan_selection" id=destination.id class="bt-a">
                <div class="destination">
                    ${destination.name}
                </div>
            </@link_to>
        </#list>
</section>