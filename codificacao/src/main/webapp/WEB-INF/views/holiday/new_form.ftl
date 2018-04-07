<@content for="title">Adicionar novo feriado</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adicionando novo feriado</h2>


<@form action="create" method="post">
    <table style="margin:30px">
        <tr>
            <td>Nome:</td>
            <td><input type="text" name="name" value="${(flasher.params.name)!}"> *
                <span class="error">${(flasher.errors.name)!}</span>
            </td>
        </tr>
        <tr>
            <td>Data:</td>
            <td><input type="date" name="date" value="${(flasher.params.date)!}"> *
                <span class="error">${(flasher.errors.date)!}</span>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Adicionar novo feriado"></td>

        </tr>
    </table>
</@form>



