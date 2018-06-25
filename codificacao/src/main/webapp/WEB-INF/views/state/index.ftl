<@content for="title">Lista de Estados</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form" class="bt-a">Adicionar novo estado</@link_to>

<table class="listing-table">
    <tr>
        <td>Nome</td>
        <td>Sigla</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
<#list states as state>
    <tr>
        <td>
            ${state.name}
        </td>
        <td>
            ${state.acronym}</td>
        <td>
            <@form  id=state.id action="alter_form" method="put" html_id=state.id >
                <button type="submit">alterar</button>
            </@form>
        </td>
        <td>
            <@form  id=state.id action="delete" method="delete" html_id=state.id >
                <button type="submit">delete</button>
            </@form>
        </td>
    </tr>
</#list>
</table>