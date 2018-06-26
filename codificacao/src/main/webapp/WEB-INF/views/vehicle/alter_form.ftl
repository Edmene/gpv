<@content for="title">Alterar veiculo</@content>

<span class="error_message"><@flash name="message"/></span>
<h1>Alterando veiculo</h1>


<@form action="update" method="post">
    <div class="forms-div">
        <label>Placa</label>
        <input type="text" required class="license" name="license_plate" value="${(vehicle.license_plate)!}">
        <span class="error">${(flasher.errors.license_plate)!}</span>

        <label>Capacidade</label>
        <input type="number" class="number-input" required name="capacity" min="1" max="80" value="${(vehicle.capacity)!}">
        <span class="error">${(flasher.errors.capacity)!}</span>

        <label>Modelo</label>
        <input type="text" required name="model" value="${(vehicle.model)!}">
        <span class="error">${(flasher.errors.model)!}</span>

        <label>Ano</label>
        <input type="number" class="number-input" required name="year" min="1900" max="5000" value="${(vehicle.year)!}">
        <span class="error">${(flasher.errors.year)!}</span>

        <div class="forms-buttons">
            <@link_to class="bt-a">Cancelar</@link_to>
            <input class="bt-a" type="submit" value="Alterar veiculo">
        </div>
    </div>
</@form>



