<@content for="title">Alterar endereco</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Alterando endereco</h2>


<@form action="update" method="post" name="update">

    <div class="forms-div">
        <label>Endereco</label>
        <input type="text" name="name" required value="${(address.name)!}">
        <span class="error">${(flasher.errors.name)!}</span>

        <label>Extra</label>
        <input type="text" name="extra" value="${(address.extra)!}">
        <span class="error">${(flasher.errors.extra)!}</span>

        <input type="hidden" name="city_id" value="${address.city_id}">
        <input type="hidden" name="id" value="${address.id}">

        <div class="forms-buttons">
            <@link_to class="bt-a">Cancelar</@link_to>
            <input class="bt-a" type="submit" value="Alterar endereco">
        </div>
    </div>
</@form>



