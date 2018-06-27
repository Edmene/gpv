<@content for="title">Lista de Planos</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form" class="bt-a">Adicionar novo plano</@link_to>

<table class="listing-table">
    <thead>
        <tr class="tr-even">
            <td>Disponivel por</td>
            <td>Preco de passagem</td>
            <td>Preco diario</td>
            <td>Numero de vagas</td>
            <td>Destinos</td>
            <td>Remover Destinos</td>
            <td>Alterar</td>
            <td>Deletar</td>
        </tr>
    </thead>
    <tbody>
        <#list plans as plan>
            <tr <#if plan_index % 2 != 0> class="tr-even" </#if>>
                <td>
                    <@link_to action="show" id=plan.id>${plan.availability_condition}</@link_to>
                </td>
                <td>
                    ${plan.ticket_price}</td>
                <td>
                    ${plan.daily_value}</td>
                <td>
                    ${plan.available_reservations}</td>
                <td>
                    <@form id=plan.id action="add_destination" method="put" html_id=plan.id>
                        <button type="submit">Adicionar Destinos</button>
                    </@form>
                </td>
                <td>
                    <@form id=plan.id action="rm_destination" method="put" html_id=plan.id>
                        <button type="submit">Remover Destinos</button>
                    </@form>
                </td>
                <td>
                    <@form  id=plan.id action="alter_form" method="put" html_id=plan.id >
                        <button type="submit">alterar</button>
                    </@form>
                </td>
                <td>
                    <@form  id=plan.id action="delete" method="delete" html_id=plan.id >
                        <button type="submit">delete</button>
                    </@form>
                </td>

            </tr>
        </#list>
    </tbody>
</table>