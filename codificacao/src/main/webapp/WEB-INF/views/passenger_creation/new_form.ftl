<@content for="title">Adicionar novo usuario</@content>

<span class="error_message"><@flash name="message"/></span>
<h1>Adicionando novo usuario</h1>


<@form action="create" method="post">


    <div class="forms-div">
        <label>Nome de Usuario</label>
        <input type="text" required name="user_name" onchange="testUser(this)" <#if flasher??>
                       value="${(flasher.params.user_name)!}"</#if>>
        <span class="error">${(flasher.errors.user_name)!}</span>

        <label>Senha</label>
        <input type="password" name="password" required>
        <span class="error">${(flasher.errors.password)!}</span>

        <input type="hidden" name="type" value="P">
        <input type="hidden" name="extra" value="extra">

        <label>Nome</label>
        <input type="text" name="name" value="${(flasher.params.name)!}" required>
        <span class="error">${(flasher.errors.name)!}</span>

        <label>Sobrenome</label>
        <input type="text" name="surname" value="${(flasher.params.surname)!}" required>
        <span class="error">${(flasher.errors.surname)!}</span>

        <label>CPF</label>
        <input type="text" class="cpf" name="cpf" value="${(flasher.params.cpf)!}" required>
        <span class="error">${(flasher.errors.cpf)!}</span>

        <label>RG</label>
        <input type="text" name="rg" value="${(flasher.params.rg)!}" required>
        <span class="error">${(flasher.errors.rg)!}</span>

        <label>Data de Nascimento</label>
        <input type="date" name="birth_date" value="${(flasher.params.birth_date)!}" required>
        <span class="error">${(flasher.errors.birth_date)!}</span>

        <label>Telefone</label>
        <input type="text" class="phone" name="telephone" value="${(flasher.params.telephone)!}" required>
        <span class="error">${(flasher.errors.telephone)!}</span>

        <label>E-mail</label>
        <input type="text" name="email" value="${(flasher.params.email)!}" required>
        <span class="error">${(flasher.errors.email)!}</span>

        <div class="forms-buttons">
            <@link_to class="bt-a">Cancelar</@link_to>
            <input class="bt-a" type="submit" value="Adicionar novo usuario">
        </div>
    </div>

</@form>



