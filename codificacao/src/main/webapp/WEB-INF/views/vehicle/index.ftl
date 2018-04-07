<@content for="title">Lista de Veiculos</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form">Adicionar novo veiculo</@link_to>

<table>
    <tr>
        <td>Placa</td>
        <td>Capacidade</td>
        <td>Ano</td>
        <td>Modelo</td>
        <td>Edit</td>
    </tr>
<#list vehicles as vehicle>
    <tr>
        <td>
            <@link_to action="show" id=vehicle.id>${vehicle.license_plate}</@link_to>
        </td>
        <td>
            ${vehicle.capacity}</td>
        <td>
            ${vehicle.model}</td>
        <td>
            ${vehicle.year}</td>
        <td>
            <@form  id=vehicle.id action="delete" method="delete" html_id=vehicle.id>
                <button type="submit">delete</button>
            </@form>
        </td>
    </tr>
</#list>
</table>




