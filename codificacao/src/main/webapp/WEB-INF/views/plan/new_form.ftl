<@content for="title">Adicionar plano</@content>

<span class="error_message"><@flash name="message"/></span>
<h1>Adicionando novo plano</h1>


<@form action="create" method="post" name="create">

    <div class="forms-div">
        <label>Disponivel por</label>
        <select name="availability_condition" required>
            <option value="M">Mensal</option>
            <option value="A">Mensal e passagem</option>
            <option value="P">Passagem</option>
        </select>
        <span class="error">${(flasher.errors.availability_condition)!}</span>

        <label>Valor diario</label>
        <input type="text" name="daily_value" value="<#if ((flasher.params.daily_value)??)>${(flasher.params.daily_value)!}
            <#else>0</#if>" required>
        <span class="error">${(flasher.errors.daily_value)!}</span>

        <label>Preco da passagem</label>
        <input type="text" name="ticket_price" value="<#if ((flasher.params.ticket_price)??)>${(flasher.params.ticket_price)!}
            <#else>0</#if>" required>
        <span class="error">${(flasher.errors.ticket_price)!}</span>

        <label>Numero de vagas</label>
        <input type="number" class="number-input" name="available_reservations" required min="1" max="100" value="${(flasher.params.available_reservations)!}">
        <span class="error">${(flasher.errors.available_reservations)!}</span>

        <div class="forms-buttons">
            <@link_to class="bt-a">Cancelar</@link_to>
            <input class="bt-a" type="submit" value="Adicionar novo plano">
        </div>
    </div>

</@form>



