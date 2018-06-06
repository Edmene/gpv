<@content for="title">Add new user</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adding new user</h2>


<@form action="create" method="post">
    <table style="margin:30px">
        <tr>
            <td>Nome de Usuario:</td>
            <td><input type="text" required name="user_name" onchange="testUser(this)" <#if flasher??>
                       value="${(flasher.params.user_name)!}"</#if>>
                            <span class="error">${(flasher.errors.user_name)!}</span>
            </td>
        </tr>
        <tr>
            <td>Senha:</td>
            <td><input type="password" name="password" value="${(flasher.params.password)!}"> *
                            <span class="error">${(flasher.errors.password)!}</span>
            </td>
            <input type="hidden" name="type" value="P">
            <input type="hidden" name="extra" value="extra">
        </tr>
        <tr>
            <td>Nome:</td>
            <td>
                <input type="text" name="name" value="${(flasher.params.name)!}"> *
                <span class="error">${(flasher.errors.name)!}</span>
            </td>
        </tr>
        <tr>
            <td>Sobrenome:</td>
            <td>
                <input type="text" name="surname" value="${(flasher.params.surname)!}"> *
                <span class="error">${(flasher.errors.surname)!}</span>
            </td>
        </tr>
        <tr>
            <td>CPF</td>
            <td>
                <input type="text" name="cpf" value="${(flasher.params.cpf)!}"> *
                <span class="error">${(flasher.errors.cpf)!}</span>
            </td>
        </tr>
        <tr>
            <td>RG</td>
            <td>
                <input type="text" name="rg" value="${(flasher.params.rg)!}"> *
                <span class="error">${(flasher.errors.rg)!}</span>
            </td>
        </tr>
        <tr>
            <td>Data de nascimento</td>
            <td>
                <input type="date" name="birth_date" value="${(flasher.params.birth_date)!}"> *
                <span class="error">${(flasher.errors.birth_date)!}</span>
            </td>
        </tr>
        <tr>
            <td>Telefone</td>
            <td>
                <input type="text" name="telephone" value="${(flasher.params.telephone)!}"> *
                <span class="error">${(flasher.errors.telephone)!}</span>
            </td>
        </tr>
        <tr>
            <td>Email</td>
            <td>
                <input type="text" name="email" value="${(flasher.params.email)!}"> *
                <span class="error">${(flasher.errors.email)!}</span>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><@link_to>Cancel</@link_to> | <button type="submit" id="confirm-button">Adicionar novo usuario</button></td>

        </tr>
    </table>


</@form>



