<@content for="title">Lista de Cidades</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form" id="${state.id}" class="bt-a">Adicionar nova cidade</@link_to>

<table class="listing-table">
    <thead>
        <tr class="tr-even">
            <td>Nome</td>
            <td>Estado</td>
            <td>Edit</td>
            <td>Deletar</td>
        </tr>
    </thead>
    <tbody>
        <#list cities as city>
            <tr <#if city_index % 2 != 0> class="tr-even" </#if>>
                <td>
                    ${city.name}
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
    </tbody>
</table>