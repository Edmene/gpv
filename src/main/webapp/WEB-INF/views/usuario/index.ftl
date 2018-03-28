<@content for="title">Usuarios List</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form">Add new book</@link_to>

<table>
    <tr>
        <td>Nome</td>
        <td>Senha</td>
        <td>Edit</td>
    </tr>
<#list usuarios as usuario>
    <tr>
        <td>
            <@link_to action="show" id=usuario.id>${usuario.nome}</@link_to>
        </td>
        <td>
            ${usuario.senha}</td>
        <td>
            <@form  id=usuario.id action="delete" method="delete" html_id=usuario.id >
                <button type="submit">delete</button>
            </@form>
        </td>
    </tr>
</#list>
</table>




