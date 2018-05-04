<@content for="title">Lista de Destinos</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form">Adicionar novo destino</@link_to>

<table>
    <tr>
        <td>Nome</td>
        <td>Endereco</td>
        <td>Edit</td>
        <td>Deletar</td>
    </tr>
<#list destinations as destination>
    <tr>
        <td>
            <@link_to action="show" id=destination.id>${destination.name}</@link_to>
        </td>
        <td>${destination.address.name} ${destination.address.extra}</td>
        <td>
            <@form  id=destination.id action="alter_form" method="put" html_id=destination.id >
                <button type="submit">alterar</button>
            </@form>
        </td>
        <td>
            <@form  id=destination.id action="delete" method="delete" html_id=destination.id >
                <button type="submit">delete</button>
            </@form>
        </td>
    </tr>
</#list>
</table>