<@content for="title">Add new user</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adding new user</h2>


<@form action="create" method="post">
    <table style="margin:30px">
        <tr>
            <td>Nome:</td>
            <td><input type="text" name="nome" value="${(flasher.params.nome)!}"> *
                            <span class="error">${(flasher.errors.nome)!}</span>
            </td>
        </tr>
        <tr>
            <td>Senha:</td>
            <td><input type="text" name="senha" value="${(flasher.params.senha)!}"> *
                            <span class="error">${(flasher.errors.senha)!}</span>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Add new user"></td>

        </tr>
    </table>
</@form>



