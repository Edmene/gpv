<@content for="title">Lista de planos - passageiro ${plans[0].passenger_id}</@content>

<#if session.accessLevel == "P">
<@link_to controller="reservation" class="bt-a">Nova reserva</@link_to>
</#if>
<span class="message"><@flash name="message"/></span>
    <table class="listing-table">
        <thead>
            <tr class="tr-even" >
                <td colspan="8">Planos com seus destinos</td>
            </tr>
            <tr class="tr-even">
                <td>Plano</td>
                <td>Destino</td>
                <td>Endereco</td>
                <td>Extra</td>
                <td>Cidade</td>
                <td>Estado</td>
                <td>Status</td>
                <td>Alterar</td>
            </tr>
        </thead>
        <tbody>
            <#list plans as plan>
                <tr <#if plan_index % 2 != 0> class="tr-even" </#if>>
                    <td>
                        <@form controller="reservation" action="reservation_list" method="get">
                            <input hidden name="passenger_id" value="${plan.passenger_id}">
                            <input hidden name="plan_id" value="${plan.plan_id}">
                            <button type="submit">${plan.plan_id}</button>
                        </@form>
                    </td>
                    <td>${plan.destination}</td>
                    <td>${plan.address}</td>
                    <td>${plan.extra}</td>
                    <td>${plan.city}</td>
                    <td>${plan.state}</td>
                    <td>${plan.status?then("Ativo","Inativo")}</td>
                    <td>
                        <@form action="change_plan" method="put">
                            <input hidden name="plan_id" value="${plan.plan_id}">
                            <input hidden name="passenger_id" value="${plan.passenger_id}">
                            <input hidden name="destination_id" value="${plan.destination_id}">
                            <button type="submit">${plan.status?then("Desativar","Ativar")}</button>
                        </@form>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>

