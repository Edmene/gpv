<@content for="title">Lista de reservas do plano ${reservations[0].plan_id} - passageiro ${reservations[0].passenger_id}</@content>

<span class="message"><@flash name="message"/></span>
    <table class="listing-table">
        <thead>
        <tr>Reservas do plano</tr>
        <tr>
            <td>Dia</td>
            <td>Turno</td>
            <td>Direcao</td>
            <td>Hora</td>
            <td>Endereco</td>
            <td>Extra</td>
            <td>Tipo</td>
            <td>Status</td>
            <td>Data de alteracao de status</td>
            <td>Alterar</td>
        </tr>
        </thead>
        <tbody>
            <#list reservations as reservation>
            <tr>
                <td>${days[reservation.day]}</td>
                <td>${shifts[reservation.shift]}</td>
                <td>${directions[reservation.direction]}</td>
                <td>${reservation.time}</td>
                <td>${reservation.address}</td>
                <td>${reservation.extra}</td>
                <td>${reservation.status?then("Ativo","Inativo")}</td>
                <td>
                    <#if reservation.reservation_type == "M">Mensal<#else>Passagem</#if>
                </td>
                <td>
                    <#if reservation.alteration_date??>${reservation.alteration_date}<#else></#if>
                </td>
                <td>
                    <@form action="change_reservation" method="put">
                        <input hidden name="passenger_id" value="${reservation.passenger_id}">
                        <input hidden name="day" value="${reservation.day}">
                        <input hidden name="shift" value="${reservation.shift}">
                        <input hidden name="direction" value="${reservation.direction}">
                        <input hidden name="stop_id" value="${reservation.stop_id}">
                        <input hidden name="plan_id" value="${reservation.plan_id}">
                        <input hidden name="driver_id" value="${reservation.driver_id}">
                        <input hidden name="vehicle_id" value="${reservation.vehicle_id}">
                        <button type="submit"><#if reservation.status>
                            <#if reservation.alteration_date??>Cancelar Desativacao<#else>Desativar</#if>
                            <#else>Ativar</#if></button>
                    </@form>
                </td>
            </tr>
            </#list>
        </tbody>
    </table>
    <table class="listing-table">
        <thead>
            <tr>
                <td>Valores Totais</td>
            </tr>
            <tr>
                <td>Passagem Ativas</td>
                <td>Passagem Inativas</td>
                <td>Mensal</td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>${totalTicketActive}</td>
                <td>${totalTicketInactive}</td>
                <td>${totalMonthly}</td>
            </tr>
        </tbody>
    </table>

