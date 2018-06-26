<@content for="title">Adicionar destino</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adicionando novo destino</h2>


<@form action="create" method="post" name="create">

    <div class="forms-div">
        <label>Nome</label>
        <input type="text" name="name" required value="${(flasher.params.name)!}">
        <span class="error">${(flasher.errors.name)!}</span>

        <input type="hidden" name="address_id" value="${address}">

        <div class="forms-buttons">
            <@link_to class="bt-a">Cancelar</@link_to>
            <input class="bt-a" type="submit" value="Adicionar novo destino">
        </div>
    </div>

</@form>



