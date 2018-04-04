<@content for="title">Adicionar endereco</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adicionando novo endereco</h2>


<@form action="create" method="post" name="create">
    <table style="margin:30px">
        <tr>
            <td>Endereco:</td>
            <td><input type="text" name="name" value="${(flasher.params.name)!}"> *
                <span class="error">${(flasher.errors.name)!}</span>
            </td>
        </tr>
        <tr>
            <td>Extra:</td>
            <td><input type="text" name="extra" value="${(flasher.params.name)!}"> *
                <span class="error">${(flasher.errors.name)!}</span>
            </td>
        </tr>
        <tr>
            <td>Cidade:</td>
            <td>
                <select name="city_id">
                <#list cities as city>
                    <option value="${city.id}">${city.name}</option>
                </#list>
                </select> *<span class="error">${(flasher.errors.state_id)!}</span>

            </td>
        </tr>
        <tr>
            <td></td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Adicionar novo endereco"></td>

        </tr>
    </table>
</@form>



