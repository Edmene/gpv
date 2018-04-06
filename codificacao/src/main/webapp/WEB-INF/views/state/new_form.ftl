<@content for="title">Adicionar estado</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adicionando novo estado</h2>


<@form action="create" method="post">
    <table style="margin:30px">
        <tr>
            <td>Nome:</td>
            <td><input type="text" name="name" value="${(flasher.params.name)!}"> *
                            <span class="error">${(flasher.errors.name)!}</span>
            </td>
        </tr>
        <tr>
            <td>Sigla:</td>
            <td><input type="text" name="acronym" value="${(flasher.params.acronym)!}"> *
                            <span class="error">${(flasher.errors.acronym)!}</span>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Adicionar novo estado"></td>

        </tr>
    </table>
</@form>



