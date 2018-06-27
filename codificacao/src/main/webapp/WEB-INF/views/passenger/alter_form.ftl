<@content for="title">Adicionar novo usuario</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adding new user</h2>


<@form action="update" method="post">

    <div class="forms-div">
        <label>Nome de Usuario</label>
        <input type="text" required name="user_name" value="${(user.name)!}">
        <span class="error">${(flasher.errors.user_name)!}</span>

        <label>Senha</label>
        <input type="password" name="password" required>
        <span class="error">${(flasher.errors.password)!}</span>

        <input type="hidden" name="type" value="P">
        <input type="hidden" name="extra" value="extra">

        <label>Nome</label>
        <input type="text" name="name" value="${(passenger.name)!}" required>
        <span class="error">${(flasher.errors.name)!}</span>

        <label>Sobrenome</label>
        <input type="text" name="surname" value="${(passenger.surname)!}" required>
        <span class="error">${(flasher.errors.surname)!}</span>

        <label>CPF</label>
        <input type="text" class="cpf" name="cpf" value="${(passenger.cpf)!}" required>
        <span class="error">${(flasher.errors.cpf)!}</span>

        <label>RG</label>
        <input type="text" name="rg" value="${(passenger.rg)!}" required>
        <span class="error">${(flasher.errors.rg)!}</span>

        <label>Data de Nascimento</label>
        <input type="date" name="birth_date" value="${(passenger.birth_date?string["yyyy-MM-dd"])!}" required>
        <span class="error">${(flasher.errors.birth_date)!}</span>

        <label>Telefone</label>
        <input type="text" class="phone" name="telephone" value="${(passenger.telephone)!}" required>
        <span class="error">${(flasher.errors.telephone)!}</span>

        <label>E-mail</label>
        <input type="text" name="email" value="${(passenger.email)!}" required>
        <span class="error">${(flasher.errors.email)!}</span>

        <input type="hidden" name="id" value="${passenger.user_id}">

        <div class="forms-buttons">
            <@link_to class="bt-a">Cancelar</@link_to>
            <input class="bt-a" type="submit" value="Alterar usuario">
        </div>
    </div>

</@form>


