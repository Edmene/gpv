<@content for="title">Add new user</@content>

<span class="error_message"><@flash name="message"/></span>
<h1>Adding new user</h1>


<@form action="create" method="post">
    <div class="forms-div">
        <label>Nome:</label>
        <input type="text" name="name" value="${(flasher.params.name)!}" required>
        <span class="error">${(flasher.errors.name)!}</span>
        <label>Senha:</label>
        <input type="password" name="password" value="" required>
        <span class="error">${(flasher.errors.password)!}</span>
        <input type="hidden" name="extra" value="extra">
        <input type="hidden" name="type" value="A">
        <div class="forms-buttons">
            <@link_to class="bt-a">Cancel</@link_to> <input class="bt-a" type="submit" value="Adicionar novo usuario">
        </div>
    </div>
</@form>



