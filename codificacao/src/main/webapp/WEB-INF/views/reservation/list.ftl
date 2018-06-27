
<@content for="title">Lista de reservas do plano ${reservations[0].plan_id}</@content>

<span class="message"><@flash name="message"/></span>
    <div>
        <label>Dia</label>
        <select id="day-filter">
            <option value="0">Sem filtro</option>
            <#list days as day>
                <option value="${day_index}">${day}</option>
            </#list>
        </select>

        <label>Turno</label>
        <select id="shift-filter">
            <option value="0">Sem filtro</option>
            <#list shifts as shift>
                <option value="${shift_index}">${shift}</option>
            </#list>
        </select>
        <button onclick="updatePassengers()">Filtrar</button>
    </div>
    <table id="passengers-table" class="listing-table">
        <thead>
        <tr class="tr-even">
            <td colspan="3">Reservas do plano</td>
        </tr>
        <tr class="tr-even">
            <td>Pessoa</td>
            <td>Detalhes do plano</td>
            <td>Planos do Passageiro</td>
        </tr>
        </thead>
        <tbody>
            <#list reservations as reservation>
                <tr <#if reservation_index % 2 != 0> class="tr-even" </#if>>
                    <td>${reservation.passenger}</td>
                    <td>
                        <@form action="reservation_list" method="get" class="passenger_reservations">
                            <input hidden name="passenger_id" value="${reservation.passenger_id}">
                            <input hidden name="plan_id" value="${reservation.plan_id}">
                            <button type="submit">Mostrar</button>
                        </@form>
                    </td>
                    <td>
                        <@link_to controller="passenger" action="list_plan" id=reservation.passenger_id class="passenger_plans">Listar</@link_to>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>