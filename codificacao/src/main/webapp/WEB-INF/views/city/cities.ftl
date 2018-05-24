<@content for="title">Lista de Cidades</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form" id="${state.id}">Adicionar nova cidade</@link_to>

<table>
    <tr>
        <td>Nome</td>
        <td>Estado</td>
        <td>Edit</td>
        <td>Deletar</td>
    </tr>
<#list cities as city>
    <tr>
        <td>
            <@link_to action="show" id=city.id>${city.name}</@link_to>
        </td>
        <td>
            ${state.name}</td>
        <td>
            <@form  id=city.id action="alter_form" method="put" html_id=city.id >
                <button type="submit">alterar</button>
            </@form>
        </td>
        <td>
            <@form  id=city.id action="delete" method="delete" html_id=city.id >
                <button type="submit">delete</button>
            </@form>
        </td>
    </tr>
</#list>
</table>