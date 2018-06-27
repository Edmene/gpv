<@content for="title">Lista de feriados</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form" class="bt-a">Adicionar novo feriado</@link_to>

<table class="listing-table">
    <thead>
        <tr class="tr-even">
            <td>Nome</td>
            <td>Dia</td>
            <td>Mes</td>
            <td>Edit</td>
            <td>Deletar</td>
        </tr>
    </thead>
    <tbody>
        <#list holidays as holiday>
            <tr <#if holiday_index % 2 != 0> class="tr-even" </#if>>
                <td>
                    ${holiday.name}
                </td>
                <td>
                    ${holiday.day}</td>
                <td>
                    ${holiday.month}</td>
                <td>
                    <@form  id=holiday.id action="alter_form" method="put" html_id=holiday.id >
                        <button type="submit">alterar</button>
                    </@form>
                </td>
                <td>
                    <@form  id=holiday.id action="delete" method="delete" html_id=holiday.id>
                        <button type="submit">delete</button>
                    </@form>
                </td>
            </tr>
        </#list>
    </tbody>
</table>




