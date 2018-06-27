<@content for="title">Lista de Veiculos</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form" class="bt-a">Adicionar novo veiculo</@link_to>

<table class="listing-table">
    <thead>
        <tr class="tr-even">
            <td>Placa</td>
            <td>Capacidade</td>
            <td>Ano</td>
            <td>Modelo</td>
            <td>Edit</td>
            <td>Deletar</td>
        </tr>
    </thead>
    <tbody>
<#list vehicles as vehicle>
    <tr <#if vehicle_index % 2 != 0> class="tr-even" </#if>>
        <td class="license">
            ${vehicle.license_plate}
        </td>
        <td>
            ${vehicle.capacity}</td>
        <td>
            ${vehicle.model}</td>
        <td>
            ${vehicle.year}</td>
        <td>
            <@form  id=vehicle.id action="alter_form" method="put" html_id=vehicle.id>
                <button type="submit">alterar</button>
            </@form>
        </td>
        <td>
            <@form  id=vehicle.id action="delete" method="delete" html_id=vehicle.id>
                <button type="submit">delete</button>
            </@form>
        </td>
    </tr>
</#list>
    </tbody>
</table>




