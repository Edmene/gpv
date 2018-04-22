<@content for="title">Alterar feriado</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Alterando feriado</h2>


<@form action="update" method="post">
    <table style="margin:30px">
        <tr>
            <td>Nome:</td>
            <td><input type="text" name="name" value="${(holiday.name)!}"> *
                <span class="error">${(flasher.errors.name)!}</span>
            </td>
        </tr>
        <tr>
            <td>Dia:</td>
            <td><input type="number" name="day" value="${(holiday.date)!}"> *
                <span class="error">${(flasher.errors.day)!}</span>
            </td>
        </tr>
        <tr>
            <td>Mes:</td>
            <td><input type="number" name="month" value="${(holiday.date)!}"> *
                <span class="error">${(flasher.errors.month)!}</span>
            </td>
        </tr>
        <tr>
            <td><input type="hidden" name="id" value="${holiday.id}"</td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Alterar feriado"></td>

        </tr>
    </table>
</@form>



