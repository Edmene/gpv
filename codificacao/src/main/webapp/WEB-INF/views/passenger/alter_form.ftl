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
            <td>Senha:</td>
            <td><input type="password" name="password" value=""> *
                            <span class="error">${(flasher.errors.password)!}</span>
            </td>
            <input type="hidden" name="type" value="P">
            <input type="hidden" name="extra" value="extra">
        </tr>
        <tr>
            <td>Nome:</td>
            <td>
                <input type="text" name="name" value="${(passenger.name)!}"> *
                <span class="error">${(flasher.errors.name)!}</span>
            </td>
        </tr>
        <tr>
            <td>Sobrenome:</td>
            <td>
                <input type="text" name="surname" value="${(passenger.surname)!}"> *
                <span class="error">${(flasher.errors.surname)!}</span>
            </td>
        </tr>
        <tr>
            <td>CPF</td>
            <td>
                <input type="text" name="cpf" value="${(passenger.cpf)!}"> *
                <span class="error">${(flasher.errors.cpf)!}</span>
            </td>
        </tr>
        <tr>
            <td>RG</td>
            <td>
                <input type="text" name="rg" value="${(passenger.rg)!}"> *
                <span class="error">${(flasher.errors.rg)!}</span>
            </td>
        </tr>
        <tr>
            <td>Data de nascimento</td>
            <td>
                <input type="date" name="birth_date" value="${(birth)!}"> *
                <span class="error">${(flasher.errors.birth_date)!}</span>
            </td>
        </tr>
        <tr>
            <td>Telefone</td>
            <td>
                <input type="text" name="telephone" value="${(passenger.telephone)!}"> *
                <span class="error">${(flasher.errors.telephone)!}</span>
            </td>
        </tr>
        <tr>
            <td>Email</td>
            <td>
                <input type="text" name="email" value="${(passenger.email)!}"> *
                <span class="error">${(flasher.errors.email)!}</span>
            </td>
        </tr>
        <tr>
            <td><input type="hidden" name="id" value="${passenger.user_id}"</td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Alterar usuario"></td>

        </tr>
    </table>
</@form>


