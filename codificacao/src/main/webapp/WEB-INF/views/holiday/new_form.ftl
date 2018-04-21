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
            <td>Dia:</td>
            <td><input type="number" name="day" value="${(flasher.params.date)!}"> *
                <span class="error">${(flasher.errors.date)!}</span>
            </td>
        </tr>
        <tr>
            <td>Mes:</td>
            <td><input type="number" name="month" value="${(flasher.params.date)!}"> *
                <span class="error">${(flasher.errors.date)!}</span>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Adicionar novo feriado"></td>

        </tr>
    </table>
</@form>



