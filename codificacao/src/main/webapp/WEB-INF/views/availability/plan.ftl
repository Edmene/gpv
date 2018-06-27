<@content for="title">Lista de disponibilidade de planos</@content>


<div class="message"><@flash name="message"/></div>

<@link_to action="new_form" id="${plan}" class="bt-a">Adicionar nova disponibilidade</@link_to>

<table class="listing-table">
    <thead>
        <tr class="tr-even">
            <td>Plano</td>
            <td>Dia</td>
            <td>Turno</td>
            <td>Sentido</td>
            <td>Veiculo</td>
            <td>Capacidade</td>
            <td>Motorista</td>
            <td>Status</td>
            <td>Alterar</td>
        </tr>
    </thead>
    <tbody>
        <#list availabilities as availability>
            <tr <#if availability_index % 2 != 0> class="tr-even" </#if>>
                <td>
                    ${availability.plan_id}
                </td>
                <td>
                    ${days[availability.day]}</td>
                <td>
                    ${shifts[availability.shift]}</td>
                <td>
                    ${directions[availability.direction]}</td>
                <td>
                    ${availability.vehicle}</td>
                <td>
                    ${availability.vehicle_capacity}</td>
                <td>
                    ${availability.driver}</td>
                <td>
                    ${availability.status?then("Ativo", "Desativado")}</td>
                <td>
                    <@form action="alter_status" method="put">
                        <input hidden name="day" value="${availability.day}">
                        <input hidden name="shift" value="${availability.shift}">
                        <input hidden name="direction" value="${availability.direction}">
                        <input hidden name="plan_id" value="${availability.plan_id}">
                        <input hidden name="driver_id" value="${availability.driver_id}">
                        <input hidden name="vehicle_id" value="${availability.vehicle_id}">
                        <input hidden name="stop_id" value="${availability.stop_id}">
                        <button type="submit">Modificar</button>
                    </@form>
                </td>
            </tr>
        </#list>
    </tbody>
</table>




