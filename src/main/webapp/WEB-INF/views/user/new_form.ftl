<@content for="title">Add new user</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adding new user</h2>


<@form action="create" method="post">
    <table style="margin:30px">
        <tr>
            <td>Nome:</td>
            <td><input type="text" name="name" value="${(flasher.params.name)!}"> *
                            <span class="error">${(flasher.errors.name)!}</span>
            </td>
        </tr>
        <tr>
            <td>Senha:</td>
            <td><input type="text" name="password" value="${(flasher.params.password)!}"> *
                            <span class="error">${(flasher.errors.password)!}</span>
            </td>
            <input type="hidden" name="extra" value="extra">
        </tr>
        <tr>
            <td></td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Add new user"></td>

        </tr>
    </table>
</@form>



