<@content for="title">Lista de Enderecos</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form" id="${city.id}" class="bt-a">Adicionar novo endereco</@link_to>

<table class="listing-table">
    <tr>
        <td>Endereco</td>
        <td>Extra</td>
        <td>Cidade</td>
        <td>Edit</td>
        <td>Deletar</td>
    </tr>
<#list addresses as address>
    <tr>
        <td>
            ${address.name}
        </td>
        <td>
            <#if address.extra??>${address.extra}<#else>sem complemento</#if>
        </td>
        <td>
            ${city.name}</td>
        <td>
            <@form  id=address.id action="alter_form" method="put" html_id=address.id >
                <button type="submit">alterar</button>
            </@form>
        </td>
        <td>
            <@form  id=address.id action="delete" method="delete" html_id=address.id >
                <button type="submit">delete</button>
            </@form>
        </td>
    </tr>
</#list>
</table>