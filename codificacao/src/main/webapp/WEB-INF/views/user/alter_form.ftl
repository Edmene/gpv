<@content for="title">Alterar usuário</@content>

<span class="error_message"><@flash name="message"/></span>
<h1>Alterando usuário</h1>


<@form action="update" method="put">
    <div class="forms-div">
        <label>Nome</label>
        <input type="text" name="name" value="${(user.name)!}" required>
        <span class="error">${(flasher.errors.name)!}</span>

        <label>Senha</label>
        <input type="password" name="password" value="" required>
        <span class="error">${(flasher.errors.password)!}</span>

        <input type="hidden" name="extra" value="extra">
        <input type="hidden" name="type" value="${user.type}">
        <input type="hidden" name="id" value="${user.id}">

        <div class="forms-buttons">
            <@link_to class="bt-a">Cancelar</@link_to>
            <input class="bt-a" type="submit" value="Alterar usuario">
        </div>
    </div>
</@form>



