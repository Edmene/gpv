<@content for="title">Alterar destino</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Alterando destino</h2>


<@form action="update" method="post" name="update">

    <div class="forms-div">
        <label>Nome</label>
        <input type="text" name="name" required value="${(destination.name)!}">
        <span class="error">${(flasher.errors.name)!}</span>

        <input type="hidden" name="address_id" value="${destination.address_id}">
        <input type="hidden" name="id" value="${destination.id}">

        <div class="forms-buttons">
            <@link_to class="bt-a">Cancelar</@link_to>
            <input class="bt-a" type="submit" value="Alterar destino">
        </div>
    </div>

</@form>



