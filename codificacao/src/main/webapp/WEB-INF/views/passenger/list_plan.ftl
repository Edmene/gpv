<#list plans as plan>
    <table>
        <thead>
            <tr>Planos com seus destinos</tr>
            <tr>
                <td>Plano</td>
                <td>Destino</td>
                <td>Endereco</td>
                <td>Extra</td>
                <td>Cidade</td>
                <td>Estado</td>
                <td>Status</td>
                <td>Alterar</td>
            </tr>
        </thead>
        <tbody>
        <tr>
            <td>${plan.plan_id}</td>
            <td>${plan.destination}</td>
            <td>${plan.address}</td>
            <td>${plan.extra}</td>
            <td>${plan.city}</td>
            <td>${plan.state}</td>
            <td>${plan.status?then("Ativo","Inativo")}</td>
            <td>
                <@form action="change_plan" method="put">
                    <input hidden name="plan_id" value="${plan.plan_id}">
                    <input hidden name="passenger_id" value="${plan.passenger_id}">
                    <input hidden name="destination_id" value="${plan.destination_id}">
                    <button type="submit">${plan.status?then("Ativar","Desativar")}</button>
                </@form>
            </td>
        </tr>
        </tbody>
    </table>

</#list>