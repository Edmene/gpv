<@content for="title">Alterar plano</@content>

<span class="error_message"><@flash name="message"/></span>
<h1>Alterando plano</h1>


<@form action="update" method="post" name="update">

    <div class="forms-div">
        <label>Disponivel por</label>
        <select name="availability_condition" required>
            <option value="M" <#if plan.availability_condition == "M">selected="selected"</#if>>Mensal</option>
            <option value="A" <#if plan.availability_condition == "A">selected="selected"</#if>>Mensal e passagem</option>
            <option value="P" <#if plan.availability_condition == "P">selected="selected"</#if>>Passagem</option>
        </select>
        <span class="error">${(flasher.errors.availability_condition)!}</span>

        <label>Valor diario</label>
        <input type="text" name="daily_value" value="<#if ((plan.daily_value)??)>${(plan.daily_value)!}<#else>0</#if>" required>
        <span class="error">${(flasher.errors.daily_value)!}</span>

        <label>Preco da passagem</label>
        <input type="text" name="ticket_price" value="<#if ((plan.ticket_price)??)>${(plan.ticket_price)!}<#else>0</#if>" required>
        <span class="error">${(flasher.errors.ticket_price)!}</span>

        <label>Numero de vagas</label>
        <input type="number" class="number-input" name="available_reservations" required min="1" max="100" value="${(plan.available_reservations)!}">
        <span class="error">${(flasher.errors.available_reservations)!}</span>

        <input type="hidden" name="id" value="${plan.id}">

        <div class="forms-buttons">
            <@link_to class="bt-a">Cancelar</@link_to>
            <input class="bt-a" type="submit" value="Adicionar novo plano">
        </div>
    </div>
</@form>



