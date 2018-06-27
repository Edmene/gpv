<@content for="title">Lista de Paradas</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form" id="${address}" class="bt-a">Adicionar nova parada</@link_to>

<table class="listing-table">
    <thead>
        <tr class="tr-even">
            <td>Hora</td>
            <td>Endereco</td>
            <td>Edit</td>
            <td>Deletar</td>
        </tr>
    </thead>
    <tbody>
        <#list stops as stop>
            <tr <#if stop_index % 2 != 0> class="tr-even" </#if>>
                <td>
                    ${stop.time?string["HH:mm"]}
                </td>
                <td>
                    ${stop.address.name} ${stop.address.extra}</td>
                <td>
                    <@form  id=stop.id action="alter_form" method="put" html_id=stop.id >
                        <button type="submit">alterar</button>
                    </@form>
                </td>
                <td>
                    <@form  id=stop.id action="delete" method="delete" html_id=stop.id >
                        <button type="submit">delete</button>
                    </@form>
                </td>
            </tr>
        </#list>
    </tbody>
</table>