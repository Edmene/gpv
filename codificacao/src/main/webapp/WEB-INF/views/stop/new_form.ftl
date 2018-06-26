<@content for="title">Adicionar parada</@content>

<span class="error_message"><@flash name="message"/></span>
<h1>Adicionando nova parada</h1>


<@form action="create" method="post" name="create">

    <div class="forms-div">
        <label>Hora</label>
        <input type="text" class="hour" required pattern="([0-1][0-9]:[0-5][0-9])|(2[0-3]:[0-5][0-9])" placeholder="23:59" name="time" value="${(flasher.params.time)!}">
        <span class="error">${(flasher.errors.time)!}</span>

        <input type="hidden" name="address_id" value="${address}">

        <div class="forms-buttons">
            <@link_to class="bt-a">Cancelar</@link_to>
            <input class="bt-a" type="submit" value="Adicionar nova parada">
        </div>
    </div>

</@form>


