<@content for="title">Add new motorista</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adding new motorista</h2>


<@form action="create" method="post">
    <table style="margin:30px">
        <tr>
            <td>Nome:</td>
            <td><input type="text" name="nome" value="${(flasher.params.nome)!}"> *
                <span class="error">${(flasher.errors.nome)!}</span>
            </td>
        </tr>
        <tr>
            <td>Sobrenome:</td>
            <td><input type="text" name="sobrenome" value="${(flasher.params.sobrenome)!}"> *
                <span class="error">${(flasher.errors.sobrenome)!}</span>
            </td>
        </tr>
        <tr>
            <td>RG:</td>
            <td><input type="text" name="rg" value="${(flasher.params.rg)!}"> *
                <span class="error">${(flasher.errors.rg)!}</span>
            </td>
        </tr>
        <tr>
            <td>CNH:</td>
            <td><input type="text" name="cnh" value="${(flasher.params.cnh)!}"> *
                <span class="error">${(flasher.errors.cnh)!}</span>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Add new motorista"></td>

        </tr>
    </table>
</@form>



