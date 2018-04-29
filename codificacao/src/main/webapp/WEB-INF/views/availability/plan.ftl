<@content for="title">Lista de disponibilidade de planos</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form" id="${plan}">Adicionar nova disponibilidade</@link_to>

<table>
    <tr>
        <td>Plano</td>
        <td>Dia</td>
        <td>Turno</td>
        <td>Sentido</td>
        <td>Deletar</td>
    </tr>
<#list availabilities as availability>
    <tr>
        <td>
            <@link_to action="show" id=availability.plan_id>${availability.plan_id}</@link_to>
        </td>
        <td>
            ${days[availability.day]}</td>
        <td>
            ${shifts[availability.shift]}</td>
        <td>
            ${directions[availability.shift]}</td>
        <td>
            <@form action="delete" method="delete">
                <input hidden name="day" value="${availability.day}">
                <input hidden name="shift" value="${availability.shift}">
                <input hidden name="direction" value="${availability.direction}">
                <input hidden name="plan_id" value="${availability.plan_id}">
                <input hidden name="driver_id" value="${availability.driver_id}">
                <input hidden name="vehicle_id" value="${availability.vehicle_id}">
                <input hidden name="stop_id" value="${availability.stop_id}">
                <button type="submit">Excluir</button>
            </@form>
        </td>
    </tr>
</#list>
</table>




