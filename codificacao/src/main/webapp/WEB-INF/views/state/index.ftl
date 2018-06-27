<@content for="title">Lista de Estados</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form" class="bt-a">Adicionar novo estado</@link_to>

<table class="listing-table">
    <thead>
        <tr class="tr-even">
            <td>Nome</td>
            <td>Sigla</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
    </thead>
    <tbody>
        <#list states as state>
            <tr <#if state_index % 2 != 0> class="tr-even" </#if>>
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
    </tbody>
</table>