<@content for="title">Login</@content>

<span class="error_message"><@flash name="message"/></span>


<@form action="login" method="post">
    <table style="margin:30px">
        <tr>
            <td>Usuario:</td>
            <td><input type="text" name="user" placeholder="Digite o nome do usuario">
            </td>
        </tr>
        <tr>
            <td>Senha:</td>
            <td><input type="password" name="password" placeholder="Digite a senha do usuario">
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Logar"></td>

        </tr>
    </table>
</@form>