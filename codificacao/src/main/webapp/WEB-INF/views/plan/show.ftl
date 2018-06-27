
<@content for="title">Informacoes plano: ${plan.id}</@content>



<@link_to class="bt-a">Retornar a listagem de planos</@link_to>

<div class="plans">
    <div>
        <div class="label-info">Disponibilidade</div>
        <div>
            <#if plan.availability_condition == "A">Passagem e Mensal
            <#elseif plan.availability_condition == "M">Mensal
            <#else>Passagem
            </#if>
        </div>
    </div>
    <#if plan.availability_condition == "A" || plan.availability_condition == "P">
        <div>
            <div class="label-info">Preco da Passagem</div>
            <div>${plan.ticket_price}</div>
        </div>
    </#if>
    <#if plan.availability_condition == "A" || plan.availability_condition == "M">
        <div>
            <div class="label-info">Valor diario</div>
            <div>${plan.daily_value}</div>
        </div>
    </#if>
    <div>
        <div class="label-info">Reservas Disponiveis</div>
        <div>${plan.available_reservations-passengers}</div>
    </div>
</div>

<@link_to controller="availability" action="plan" id=plan.id class="bt-a">Disponibilidades de um plano</@link_to>
<@link_to controller="reservation" action="list" id=plan.id class="bt-a">Passageiros do plano</@link_to>