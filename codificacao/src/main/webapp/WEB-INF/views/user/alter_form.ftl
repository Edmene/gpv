<@content for="title">Alterar usuario</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Alterando usuario</h2>


<@form action="update" method="put">
    <table style="margin:30px">
        <tr>
            <td>Nome:</td>
            <td><input type="text" name="name" value="${(user.name)!}"> *
                            <span class="error">${(flasher.errors.name)!}</span>
            </td>
        </tr>
        <tr>
            <td>Senha:</td>
            <td><input type="password" name="password" value=""> *
                            <span class="error"></span>
            </td>
            <input type="hidden" name="extra" value="extra">
            <input type="hidden" name="type" value="${user.type}">
        </tr>
        <tr>
            <td><input type="hidden" name="id" value="${user.id}"</td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Alterar usuario"></td>

        </tr>
    </table>
</@form>



