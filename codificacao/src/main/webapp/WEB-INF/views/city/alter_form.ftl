<@content for="title">Adicionar cidade</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adicionando nova cidade</h2>


<@form action="update" method="post" name="update">
    <table style="margin:30px">
        <tr>
            <td>Nome:</td>
            <td><input type="text" name="name" value="${(city.name)!}"> *
                <span class="error">${(flasher.errors.name)!}</span>
            </td>
        </tr>
        <input type="hidden" name="state_id" value="${city.state_id}">
        <tr>
            <td><input type="hidden" name="id" value="${city.id}"</td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Alterar cidade"></td>

        </tr>
    </table>
</@form>



