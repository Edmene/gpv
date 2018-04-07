<@content for="title">Adicionar destino</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adicionando novo destino</h2>


<@form action="create" method="post" name="create">
    <table style="margin:30px">
        <tr>
            <td>Nome:</td>
            <td><input type="text" name="name" value="${(flasher.params.name)!}"> *
                <span class="error">${(flasher.errors.name)!}</span>
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



