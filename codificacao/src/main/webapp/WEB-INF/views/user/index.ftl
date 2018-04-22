<@content for="title">Usuarios List</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form">Add new book</@link_to>

<table>
    <tr>
        <td>Nome</td>
        <td>Tipo</td>
        <td>Editar</td>
        <td>Deletar</td>
    </tr>
<#list users as user>
    <tr>
        <td>
            <@link_to action="show" id=user.id>${user.name}</@link_to>
        </td>
        <td>
            ${user.type}</td>
        <td>
            <@form  id=user.id action="alter_form" method="put" html_id=user.id >
                <button type="submit">alterar</button>
            </@form>
        </td>
        <td>
            <@form  id=user.id action="delete" method="delete" html_id=user.id >
                <button type="submit">Excluir</button>
            </@form>
        </td>
    </tr>
</#list>
</table>




