<#list plans as plan>
    <@form action="delete_plan" method="delete">
                <input hidden name="plan_id" value="${plan.plan_id}">
                <input hidden name="passenger_id" value="${plan.passenger_id}">
                <input hidden name="destination_id" value="${plan.destination_id}">
                <button type="submit">Desativar ${plan.plan_id} ${plan.destination}</button>
    </@form>
</#list>