<@content for="title">Lista de Paradas</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form" id="${address}">Adicionar nova parada</@link_to>

<table>
    <tr>
        <td>Hora</td>
        <td>Endereco</td>
        <td>Edit</td>
        <td>Deletar</td>
    </tr>
<#list stops as stop>
    <tr>
        <td>
            <@link_to action="show" id=stop.id>${stop.time?string["HH:mm"]}</@link_to>
        </td>
        <td>
            ${stop.address.name} ${stop.address.extra}</td>
        <td>
            <@form  id=stop.id action="alter_form" method="put" html_id=stop.id >
                <button type="submit">alterar</button>
            </@form>
        </td>
        <td>
            <@form  id=stop.id action="delete" method="delete" html_id=stop.id >
                <button type="submit">delete</button>
            </@form>
        </td>
    </tr>
</#list>
</table>