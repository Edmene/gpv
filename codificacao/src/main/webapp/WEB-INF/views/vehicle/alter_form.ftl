<@content for="title">Adicionar novo veiculo</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adicionando novo veiculo</h2>


<@form action="update" method="post">
    <table style="margin:30px">
        <tr>
            <td>Placa:</td>
            <td><input type="text" name="license_plate" value="${(vehicle.license_plate)!}"> *
                <span class="error">${(flasher.errors.license_plate)!}</span>
            </td>
        </tr>
        <tr>
            <td>Capacidade:</td>
            <td><input type="text" name="capacity" value="${(vehicle.capacity)!}"> *
                <span class="error">${(flasher.errors.capacity)!}</span>
            </td>
        </tr>
        <tr>
            <td>Modelo:</td>
            <td><input type="text" name="model" value="${(vehicle.model)!}"> *
                <span class="error">${(flasher.errors.model)!}</span>
            </td>
        </tr>
        <tr>
            <td>Ano:</td>
            <td><input type="text" name="year" value="${(vehicle.year)!}"> *
                <span class="error">${(flasher.errors.year)!}</span>
            </td>
        </tr>
        <tr>
            <td><input type="hidden" name="id" value="${vehicle.id}"</td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Add new driver"></td>

        </tr>
    </table>
</@form>



