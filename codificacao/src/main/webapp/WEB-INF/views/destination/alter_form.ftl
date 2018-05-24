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
        <input type="hidden" name="address_id" value="${destination.address_id}">
        <tr>
            <td><input type="hidden" name="id" value="${destination.id}"</td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Alterar destino"></td>

        </tr>
    </table>
</@form>



