<@content for="title">Adicionar nova disponibilidade</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adicionando nova disponibilidade</h2>


<@form action="create" method="post">
    <table style="margin:30px">
        <tr>
            <td>Nome de Usuario:</td>
            <td><input type="text" name="user_name" value="${(flasher.params.user_name)!}"> *
                            <span class="error">${(flasher.errors.user_name)!}</span>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Adicionar novo usuario"></td>

        </tr>
    </table>
</@form>



