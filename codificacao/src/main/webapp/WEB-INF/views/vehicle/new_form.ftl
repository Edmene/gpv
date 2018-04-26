<@content for="title">Adicionar novo veiculo</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adicionando novo veiculo</h2>


<@form action="create" method="post">
    <table style="margin:30px">
        <tr>
            <td>Placa:</td>
            <td><input type="text" name="license_plate" value="${(flasher.params.license_plate)!}"> *
                <span class="error">${(flasher.errors.license_plate)!}</span>
            </td>
        </tr>
        <tr>
            <td>Capacidade:</td>
            <td><input type="number" name="capacity"
                       min="1" max="80" value="${(flasher.params.capacity)!}"> *
                <span class="error">${(flasher.errors.capacity)!}</span>
            </td>
        </tr>
        <tr>
            <td>Modelo:</td>
            <td><input type="text" name="model" value="${(flasher.params.model)!}"> *
                <span class="error">${(flasher.errors.model)!}</span>
            </td>
        </tr>
        <tr>
            <td>Ano:</td>
            <td><input type="number" name="year"
                       min="1" max="80" value="${(flasher.params.year)!}"> *
                <span class="error">${(flasher.errors.year)!}</span>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Add new driver"></td>

        </tr>
    </table>
</@form>



