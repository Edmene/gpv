<@content for="title">Lista de Planos</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form">Adicionar novo plano</@link_to>

<table>
    <tr>
        <td>Disponivel por</td>
        <td>Preco de passagem</td>
        <td>Preco diario</td>
        <td>Numero de vagas</td>
        <td>Edit</td>
    </tr>
<#list plans as plan>
    <tr>
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
            <@form  id=plan.id action="delete" method="delete" html_id=plan.id >
                <button type="submit">delete</button>
            </@form>
        </td>
    </tr>
</#list>
</table>