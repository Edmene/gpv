<@content for="title">Adicionar plano</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adicionando novo plano</h2>


<@form action="create" method="post" name="create">
    <table style="margin:30px">
        <tr>
            <td>Disponivel por:</td>
            <td>
                <select name="availability_condition">
                    <option value="M">Mensal</option>
                    <option value="A">Mensal e passagem</option>
                    <option value="P">Passagem</option>
                </select> *<span class="error">${(flasher.errors.availability_condition)!}</span>

            </td>
        </tr>
        <tr>
            <td>Preco da passagem:</td>
            <td><input type="text" name="ticket_price" value="<#if ((flasher.params.ticket_price)??)>${(flasher.params.ticket_price)!}
            <#else>0</#if>">
                <span class="error">${(flasher.errors.ticket_price)!}</span>
            </td>
        </tr>
        <tr>
            <td>Valor diario:</td>
            <td><input type="text" name="daily_value" value="<#if ((flasher.params.daily_value)??)>${(flasher.params.daily_value)!}
            <#else>0</#if>">
                <span class="error">${(flasher.errors.daily_value)!}</span>
            </td>
        </tr>
        <tr>
            <td>Numero de vagas:</td>
            <td><input type="text" name="available_reservations" required="required" value="${(flasher.params.available_reservations)!}"> *
                <span class="error">${(flasher.errors.available_reservations)!}</span>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Adicionar novo plano"></td>

        </tr>
    </table>
</@form>



