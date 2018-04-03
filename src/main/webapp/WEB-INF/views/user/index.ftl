<@content for="title">Usuarios List</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form">Add new book</@link_to>

<table>
    <tr>
        <td>Nome</td>
        <td>Senha</td>
        <td>Editar</td>
    </tr>
<#list users as user>
    <tr>
        <td>
            <@link_to action="show" id=user.id>${user.name}</@link_to>
        </td>
        <td>
            ${user.password}</td>
        <td>
            <@form  id=user.id action="delete" method="delete" html_id=user.id >
                <button type="submit">Excluir</button>
            </@form>
        </td>
    </tr>
</#list>
</table>




