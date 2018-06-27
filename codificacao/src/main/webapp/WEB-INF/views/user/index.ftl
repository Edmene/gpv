<@content for="title">Lista deUsuarios</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form" class="bt-a">Adicionar novo usuário</@link_to>

<table class="listing-table">
    <thead>
        <tr class="tr-even">
            <td>Nome</td>
            <td>Tipo</td>
            <td>Editar</td>
            <td>Deletar</td>
        </tr>
    </thead>
    <tbody>
        <#list users as user>
            <tr <#if user_index % 2 != 0> class="tr-even" </#if>>
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




