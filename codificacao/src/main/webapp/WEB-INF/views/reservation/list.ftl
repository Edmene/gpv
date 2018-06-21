<@content for="title">Lista de reservas do plano ${reservations[0].plan_id}</@content>

<span class="message"><@flash name="message"/></span>
    <table>
        <thead>
        <tr>Reservas do plano</tr>
        <tr>
            <td>Pessoa</td>
            <td>Detalhes do plano</td>
            <td>Planos do Passageiro</td>
        </tr>
        </thead>
        <tbody>
            <#list reservations as reservation>
            <tr>
                <td>${reservation.passenger}</td>
                <td>
                    <@form action="reservation_list" method="get">
                        <input hidden name="passenger_id" value="${reservation.passenger_id}">
                        <input hidden name="plan_id" value="${reservation.plan_id}">
                        <button type="submit">Mostrar</button>
                    </@form>
                </td>
                <td>
                    <@link_to controller="passenger" action="list_plan" id=reservation.passenger_id>Listar</@link_to>
                </td>
            </tr>
            </#list>
        </tbody>
    </table>