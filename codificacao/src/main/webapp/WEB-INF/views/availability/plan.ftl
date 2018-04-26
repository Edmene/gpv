<@content for="title">Lista de disponibilidade de planos</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form" id="${plan}">Adicionar nova disponibilidade</@link_to>

<table>
    <tr>
        <td>Plano</td>
        <td>Dia</td>
        <td>Turno</td>
        <td>Editar</td>
        <td>Deletar</td>
    </tr>
<#list availabilities as availability>
    <tr>
        <td>
            <@link_to action="show" id=availability.plan_id>${availability.plan_id}</@link_to>
        </td>
        <td>
            ${days[availability.day]}</td>
        <td>
            ${shifts[availability.shift]}</td>
        <td>
            <@form  id=availability.user_id action="alter_form" method="put" html_id=availability.user_id >
                <button type="submit">alterar</button>
            </@form>
        </td>
        <td>
            <@form  id=availability.user_id action="delete" method="delete" html_id=availability.user_id >
                <button type="submit">Excluir</button>
            </@form>
        </td>
    </tr>
</#list>
</table>
<--TODO a proper alteration of availability and deletion --/>




