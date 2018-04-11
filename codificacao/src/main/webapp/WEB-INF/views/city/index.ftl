<@content for="title">Lista de Cidades</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form">Adicionar nova cidade</@link_to>

<table>
    <tr>
        <td>Nome</td>
        <td>Estado</td>
        <td>Edit</td>
    </tr>
<#list cities as city>
    <tr>
        <td>
            <@link_to action="show" id=city.id>${city.name}</@link_to>
        </td>
        <td>
            ${city.state_id}</td>
        <td>
            <@form  id=city.id action="delete" method="delete" html_id=city.id >
                <button type="submit">delete</button>
            </@form>
        </td>
    </tr>
</#list>
</table>