<@content for="title">Adicionar endereco</@content>

<span class="error_message"><@flash name="message"/></span>
<h1>Adicionando novo endereco</h1>

<@form action="create" method="post" name="create">

    <div class="forms-div">
        <label>Endereco</label>
        <input type="text" name="name" required value="${(flasher.params.name)!}">
        <span class="error">${(flasher.errors.name)!}</span>

        <label>Extra</label>
        <input type="text" name="extra" value="${(flasher.params.extra)!}">
        <span class="error">${(flasher.errors.extra)!}</span>

        <input type="hidden" name="city_id" value="${city}">

        <div class="forms-buttons">
            <@link_to class="bt-a">Cancelar</@link_to>
            <input class="bt-a" type="submit" value="Adicionar novo endereco">
        </div>
    </div>

</@form>



