<@content for="title">Adicionar cidade</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adicionando nova cidade</h2>


<@form action="update" method="post" name="update">

    <div class="forms-div">
        <label>Nome</label>
        <input type="text" name="name" required value="${(city.name)!}">
        <span class="error">${(flasher.errors.name)!}</span>

        <input type="hidden" name="state_id" value="${city.state_id}">
        <input type="hidden" name="id" value="${city.id}">

        <div class="forms-buttons">
            <@link_to class="bt-a">Cancelar</@link_to>
            <input class="bt-a" type="submit" value="Alterar cidade">
        </div>
    </div>

</@form>



