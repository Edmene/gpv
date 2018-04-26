<@content for="title">Adicionar parada</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adicionando nova parada</h2>


<@form action="create" method="post" name="create">
    <table style="margin:30px">
        <tr>
            <td>Hora:</td>
            <td><input type="text" pattern="([0-1][0-9]:[0-5][0-9])|(2[0-3]:[0-5][0-9])" placeholder="23:59" name="time" value="${(flasher.params.time)!}"> *
                <span class="error">${(flasher.errors.time)!}</span>
            </td>
        </tr>
        <tr>
            <td>Endereco:</td>
            <td>
                <select name="address_id">
                <#list addresses as address>
                    <option value="${address.id}">${address.name}</option>
                </#list>
                </select> *<span class="error">${(flasher.errors.address_id)!}</span>

            </td>
        </tr>
        <tr>
            <td></td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Adicionar nova cidade"></td>

        </tr>
    </table>
</@form>

