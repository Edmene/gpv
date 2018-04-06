<@content for="title">Lista de Enderecos</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form">Adicionar novo endereco</@link_to>

<table>
    <tr>
        <td>Endereco</td>
        <td>Extra</td>
        <td>Cidade</td>
        <td>Edit</td>
    </tr>
<#list addresses as address>
    <tr>
        <td>
            <@link_to action="show" id=address.id>${address.name}</@link_to>
        </td>
        <td>
            <#if address.extra??>${address.extra}<#else>sem complemento
            </#if>
        </td>
        <td>
            ${address.city_id}</td>
        <td>
            <@form  id=address.id action="delete" method="delete" html_id=address.id >
                <button type="submit">delete</button>
            </@form>
        </td>
    </tr>
</#list>
</table>