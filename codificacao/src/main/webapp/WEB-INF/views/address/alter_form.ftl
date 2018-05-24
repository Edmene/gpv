<@content for="title">Alterar endereco</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Alterando endereco</h2>


<@form action="update" method="post" name="update">
    <table style="margin:30px">
        <tr>
            <td>Endereco:</td>
            <td><input type="text" name="name" value="${(address.name)!}"> *
                <span class="error">${(flasher.errors.name)!}</span>
            </td>
        </tr>
        <tr>
            <td>Extra:</td>
            <td><input type="text" name="extra" value="${(address.extra)!}"> *
                <span class="error">${(flasher.errors.extra)!}</span>
            </td>
        </tr>
        <input type="hidden" name="city_id" value="${address.city_id}">
        <tr>
            <td><input type="hidden" name="id" value="${address.id}"</td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Alterar endereco"></td>

        </tr>
    </table>
</@form>



