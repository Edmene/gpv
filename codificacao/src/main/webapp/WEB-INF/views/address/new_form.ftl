<@content for="title">Adicionar endereco</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adicionando novo endereco</h2>


<@form action="create" method="post" name="create">
    <table style="margin:30px">
        <tr>
            <td>Endereco:</td>
            <td><input type="text" name="name" value="${(flasher.params.name)!}"> *
                <span class="error">${(flasher.errors.name)!}</span>
            </td>
        </tr>
        <tr>
            <td>Extra:</td>
            <td><input type="text" name="extra" value="${(flasher.params.extra)!}"> *
                <span class="error">${(flasher.errors.extra)!}</span>
            </td>
        </tr>
        <input type="hidden" name="city_id" value="${city}">
        <tr>
            <td></td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Adicionar novo endereco"></td>

        </tr>
    </table>
</@form>



