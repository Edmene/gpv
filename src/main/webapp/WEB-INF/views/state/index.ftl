<@content for="title">Lista de Estados</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form">Adicionar novo estado</@link_to>

<table>
    <tr>
        <td>Nome</td>
        <td>Sigla</td>
        <td>Edit</td>
    </tr>
<#list states as state>
    <tr>
        <td>
            <@link_to action="show" id=state.id>${state.name}</@link_to>
        </td>
        <td>
            ${state.acronym}</td>
        <td>
            <@form  id=state.id action="delete" method="delete" html_id=state.id >
                <button type="submit">delete</button>
            </@form>
        </td>
    </tr>
</#list>
</table>