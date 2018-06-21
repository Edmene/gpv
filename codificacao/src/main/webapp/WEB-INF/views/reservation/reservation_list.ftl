<span class="message"><@flash name="message"/></span>
    <table>
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
                        <input hidden name="plan_id" value="${reservation.plan_id}">
                        <input hidden name="passenger_id" value="${reservation.passenger_id}">
                        <button type="submit">${reservation.status?then("Desativar","Ativar")}</button>
                    </@form>
                </td>
            </tr>
            </#list>
        </tbody>
    </table>

