<@content for="title">Alterar parada</@content>

<span class="error_message"><@flash name="message"/></span>
<h1>Alterando parada</h1>


<@form action="update" method="post" name="update">

    <div class="forms-div">
        <label>Hora</label>
        <input type="text" class="hour" required pattern="([0-1][0-9]:[0-5][0-9])|(2[0-3]:[0-5][0-9])" placeholder="23:59" name="time" value="${(stop.time?string["HH:mm"])!}">
        <span class="error">${(flasher.errors.time)!}</span>

        <input type="hidden" name="address_id" value="${stop.address_id}">
        <input type="hidden" name="id" value="${stop.id}">

        <div class="forms-buttons">
            <@link_to class="bt-a">Cancelar</@link_to>
            <input class="bt-a" type="submit" value="Alterar parada">
        </div>
    </div>

</@form>



