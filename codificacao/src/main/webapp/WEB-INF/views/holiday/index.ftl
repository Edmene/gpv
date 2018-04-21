<@content for="title">Motorista List</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form">Adicionar novo feriado</@link_to>

<table>
    <tr>
        <td>Nome</td>
        <td>Dia</td>
        <td>Mes</td>
        <td>Edit</td>
    </tr>
<#list holidays as holiday>
    <tr>
        <td>
            <@link_to action="show" id=holiday.id>${holiday.name}</@link_to>
        </td>
        <td>
            ${holiday.day}</td>
        <td>
            ${holiday.month}</td>
        <td>
            <@form  id=holiday.id action="delete" method="delete" html_id=holiday.id>
                <button type="submit">delete</button>
            </@form>
        </td>
    </tr>
</#list>
</table>




