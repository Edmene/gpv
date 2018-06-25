<@content for="title">Lista de Veiculos</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form" class="bt-a">Adicionar novo veiculo</@link_to>

<table class="listing-table">
    <tr>
        <td>Placa</td>
        <td>Capacidade</td>
        <td>Ano</td>
        <td>Modelo</td>
        <td>Edit</td>
        <td>Deletar</td>
    </tr>
<#list vehicles as vehicle>
    <tr>
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
</table>




