<@content for="title">Adicionar novo veiculo</@content>

<span class="error_message"><@flash name="message"/></span>
<h1>Adicionando novo veiculo</h1>


<@form action="create" method="post">
    <div class="forms-div">
        <label>Placa</label>
        <input type="text" required class="license" name="license_plate" value="${(flasher.params.license_plate)!}">
        <span class="error">${(flasher.errors.license_plate)!}</span>

        <label>Capacidade</label>
        <input type="number" class="number-input" required name="capacity" min="1" max="80" value="${(flasher.params.capacity)!}">
        <span class="error">${(flasher.errors.capacity)!}</span>

        <label>Modelo</label>
        <input type="text" required name="model" value="${(flasher.params.model)!}">
        <span class="error">${(flasher.errors.model)!}</span>

        <label>Ano</label>
        <input type="number" class="number-input" required name="year" min="1900" max="5000" value="${(flasher.params.year)!}">
        <span class="error">${(flasher.errors.year)!}</span>

        <div class="forms-buttons">
        <@link_to class="bt-a">Cancelar</@link_to>
            <input class="bt-a" type="submit" value="Adicionar novo veiculo">
        </div>
    </div>
</@form>



