<@content for="title">Adicionar cidade</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adicionando nova cidade</h2>


<@form action="create" method="post" name="create">
    <table style="margin:30px">
        <tr>
            <td>Nome:</td>
            <td><input type="text" name="name" value="${(flasher.params.name)!}"> *
                <span class="error">${(flasher.errors.name)!}</span>
            </td>
        </tr>
        <input type="hidden" name="state_id" value="${state}">
        <tr>
            <td></td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Adicionar nova cidade"></td>

        </tr>
    </table>
</@form>



