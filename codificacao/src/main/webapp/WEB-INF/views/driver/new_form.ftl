<@content for="title">Add new driver</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adding new driver</h2>


<@form action="create" method="post">
    <table style="margin:30px">
        <tr>
            <td>Nome:</td>
            <td><input type="text" name="name" value="${(flasher.params.name)!}"> *
                <span class="error">${(flasher.errors.name)!}</span>
            </td>
        </tr>
        <tr>
            <td>Sobrenome:</td>
            <td><input type="text" name="surname" value="${(flasher.params.surname)!}"> *
                <span class="error">${(flasher.errors.surname)!}</span>
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
            <td><input type="text" class="cnh" name="cnh" value="${(flasher.params.cnh)!}"> *
                <span class="error">${(flasher.errors.cnh)!}</span>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Add new driver"></td>

        </tr>
    </table>
</@form>



