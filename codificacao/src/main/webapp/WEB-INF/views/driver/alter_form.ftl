<@content for="title">Alterar motorista</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Alterando motorista</h2>


<@form action="update" method="post" name="update">
    <table style="margin:30px">
        <tr>
            <td>Nome:</td>
            <td><input type="text" name="name" value="${(driver.name)!}"> *
                <span class="error">${(flasher.errors.name)!}</span>
            </td>
        </tr>
        <tr>
            <td>Sobrenome:</td>
            <td><input type="text" name="surname" value="${(driver.surname)!}"> *
                <span class="error">${(flasher.errors.surname)!}</span>
            </td>
        </tr>
        <tr>
            <td>RG:</td>
            <td><input type="text" name="rg" value="${(driver.rg)!}"> *
                <span class="error">${(flasher.errors.rg)!}</span>
            </td>
        </tr>
        <tr>
            <td>CNH:</td>
            <td><input type="text" class="cnh" name="cnh" value="${(driver.cnh)!}"> *
                <span class="error">${(flasher.errors.cnh)!}</span>
            </td>
        </tr>
        <tr>
            <td><input type="hidden" name="id" value="${driver.id}"</td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Alterar motorista"></td>

        </tr>
    </table>
</@form>



