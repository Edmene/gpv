<@content for="title">Adicionar nova disponibilidade</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adicionando nova disponibilidade</h2>
<script src="${context_path}/js/formTurns.js" type="text/javascript"></script>

<@form action="create" method="post">
    Manha<input id="M" type="checkbox" onclick="checkTurns()">
    Tarde<input id="T" type="checkbox" onclick="checkTurns()">
    Noite<input id="N" type="checkbox" onclick="checkTurns()">
    <table style="margin:30px" >
        <tr>
            <td>Dia:</td>
            <td>
                <select name="day">
                    <#list days as day>
                        <option value="${day}">${day}</option>
                    </#list>
                </select>
            </td>
        </tr>
        <tr>
            <td>Turno:</td>
            <td>
                <select name="shift">
                    <#list shifts as shift>
                        <option value="${shift}">${shift}</option>
                    </#list>
                </select>
            </td>
        </tr>
        <tr>
            <td>Motorista:</td>
            <td>
                <select name="driver">
                    <#list drivers as driver>
                        <option value="${driver.id}">${driver.name + " " + driver.surname}</option>
                    </#list>
                </select>
            </td>
        </tr>
        <tr>
            <td>Veiculo:</td>
            <td>
                <select name="vehicle">
                    <#list vehicles as vehicle>
                        <option value="${vehicle.id}">${vehicle.license_plate}</option>
                    </#list>
                </select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Proximo Passo"></td>

        </tr>
    </table>
</@form>



