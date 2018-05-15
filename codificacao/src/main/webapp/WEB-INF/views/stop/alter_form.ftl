<@content for="title">Alterar parada</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Alterando parada</h2>


<@form action="update" method="post" name="update">
    <table style="margin:30px">
        <tr>
            <td>Hora:</td>
            <td><input type="text" pattern="([0-1][0-9]:[0-5][0-9])|(2[0-3]:[0-5][0-9])" placeholder="23:59" name="time" value="${(stop.time?string["HH:mm"])!}"> *
                <span class="error">${(flasher.errors.time)!}</span>
            </td>
        </tr>
        <tr>
            <td>Endereco:</td>
            <td>
                <select name="address_id">
                <#list addresses as address>
                    <#if stop.address_id == address.id>
                    <option value="${address.id}" selected="selected">${address.name}</option>
                    <#else>
                    <option value="${address.id}">${address.name}</option>
                    </#if>
                </#list>
                </select> *<span class="error">${(flasher.errors.address_id)!}</span>

            </td>
        </tr>
        <tr>
            <td><input type="hidden" name="id" value="${stop.id}"</td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Alterar destino"></td>

        </tr>
    </table>
</@form>



