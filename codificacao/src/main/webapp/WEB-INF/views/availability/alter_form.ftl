<@content for="title">Add new user</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adding new user</h2>


<@form action="update" method="post">
    <table style="margin:30px">
        <tr>
            <td>Nome de Usuario:</td>
            <td><input type="text" name="user_name" value="${(user.name)!}"> *
                            <span class="error">${(flasher.errors.user_name)!}</span>
            </td>
        </tr>
        <tr>
            <td><input type="hidden" name="id" value="${passenger.user_id}"</td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Alterar usuario"></td>

        </tr>
    </table>
</@form>


