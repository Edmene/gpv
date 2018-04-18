<@content for="title">Alterar estado</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Alterando estado ${state.name}</h2>


<@form action="update" method="post">
    <table style="margin:30px">
        <tr>
            <td>Nome:</td>
            <td><input type="text" name="name" value="${state.name}"> *
                <span class="error">${(flasher.errors.name)!}</span>
            </td>
        </tr>
        <tr>
            <td>Sigla:</td>
            <td><input type="text" name="acronym" value="${(state.acronym)!}"> *
                <span class="error">${(flasher.errors.acronym)!}</span>
            </td>
        </tr>
        <tr>
            <td><input type="hidden" name="id" value="${state.id}"</td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Alterar estado"></td>

        </tr>
    </table>
</@form>



