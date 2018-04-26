<@content for="title">Alterar plano</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Alterando plano</h2>


<@form action="update" method="post" name="update">
    <table style="margin:30px">
        <tr>
            <td>Disponivel por:</td>
            <td>
                <select name="availability_condition">
                    <option value="M" <#if plan.availability_condition == "M">selected="selected"</#if>>Mensal</option>
                    <option value="A" <#if plan.availability_condition == "A">selected="selected"</#if>>Mensal e passagem</option>
                    <option value="P" <#if plan.availability_condition == "P">selected="selected"</#if>>Passagem</option>
                </select> *<span class="error">${(flasher.errors.availability_condition)!}</span>

            </td>
        </tr>
        <tr>
            <td>Preco da passagem:</td>
            <td><input type="text" name="ticket_price" value="<#if ((plan.ticket_price)??)>${(plan.ticket_price)!}<#else>0</#if>">
                <span class="error">${(flasher.errors.ticket_price)!}</span>
            </td>
        </tr>
        <tr>
            <td>Valor diario:</td>
            <td><input type="text" name="daily_value" value="<#if ((plan.daily_value)??)>${(plan.daily_value)!}<#else>0</#if>">
                <span class="error">${(flasher.errors.daily_value)!}</span>
            </td>
        </tr>
        <tr>
            <td>Numero de vagas:</td>
            <td><input type="number" name="available_reservations" required="required"
                       min="1" max="100" value="${(plan.available_reservations)!}"> *
                <span class="error">${(flasher.errors.available_reservations)!}</span>
            </td>
        </tr>
        <tr>
            <td><input type="hidden" name="id" value="${plan.id}"</td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Alterar plano"></td>

        </tr>
    </table>
</@form>



