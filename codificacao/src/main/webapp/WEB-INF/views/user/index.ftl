<@content for="title">Usuarios List</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form" class="bt-a">Adicionar novo usuário</@link_to>

<table class="listing-table">
    <thead>
        <tr>
            <td>Nome</td>
            <td>Tipo</td>
            <td>Editar</td>
            <td>Deletar</td>
        </tr>
    </thead>
    <tbody>
<#list users as user>
    <tr>
        <td>
            ${user.name}
        </td>
        <td>
            ${user.type}</td>
        <td>
            <@form  id=user.id action="alter_form" method="get" html_id=user.id >
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
    </tbody>
</table>




