<@content for="title">Adicionar cidade</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adicionando nova cidade</h2>


<@form action="update" method="post" name="update">
    <table style="margin:30px">
        <tr>
            <td>Nome:</td>
            <td><input type="text" name="name" value="${(city.name)!}"> *
                <span class="error">${(flasher.errors.name)!}</span>
            </td>
        </tr>
        <tr>
            <td>Estado:</td>
            <td>
                <select name="state_id">
                <#list states as state>
                    <#if city.state_id == state.id>
                    <option value="${state.id}" selected="selected">${state.acronym}</option>
                    <#else>
                    <option value="${state.id}">${state.acronym}</option>
                    </#if>
                </#list>
                </select> *<span class="error">${(flasher.errors.state_id)!}</span>

            </td>
        </tr>
        <tr>
            <td><input type="hidden" name="id" value="${city.id}"</td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Alterar cidade"></td>

        </tr>
    </table>
</@form>



