<@content for="title">Alterar destino</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Alterando destino</h2>


<@form action="update" method="post" name="update">
    <table style="margin:30px">
        <tr>
            <td>Nome:</td>
            <td><input type="text" name="name" value="${(destination.name)!}"> *
                <span class="error">${(flasher.errors.name)!}</span>
            </td>
        </tr>
        <tr>
            <td>Endereco:</td>
            <td>
                <select name="address_id">
                <#list addresses as address>
                    <#if destination.address_id == address.id>
                    <option value="${address.id}" selected="selected">${address.name}</option>
                    <#else>
                    <option value="${address.id}">${address.name}</option>
                    </#if>
                </#list>
                </select> *<span class="error">${(flasher.errors.address_id)!}</span>

            </td>
        </tr>
        <tr>
            <td><input type="hidden" name="id" value="${destination.id}"</td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Alterar destino"></td>

        </tr>
    </table>
</@form>



