<@content for="title">Lista de Destinos</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form" id="${address}" class="bt-a">Adicionar novo destino</@link_to>

<table class="listing-table">
    <thead>
        <tr class="tr-even">
            <td>Nome</td>
            <td>Endereco</td>
            <td>Edit</td>
            <td>Deletar</td>
        </tr>
    </thead>
    <tbody>
        <#list destinations as destination>
            <tr <#if destination_index % 2 != 0> class="tr-even" </#if>>
                <td>
                    ${destination.name}
                </td>
                <td>${destination.address.name} ${destination.address.extra}</td>
                <td>
                    <@form  id=destination.id action="alter_form" method="put" html_id=destination.id >
                        <button type="submit">alterar</button>
                    </@form>
                </td>
                <td>
                    <@form  id=destination.id action="delete" method="delete" html_id=destination.id >
                        <button type="submit">delete</button>
                    </@form>
                </td>
            </tr>
        </#list>
    </tbody>
</table>