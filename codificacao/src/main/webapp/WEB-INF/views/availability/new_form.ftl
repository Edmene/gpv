<@content for="title">Adicionar nova disponibilidade</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adicionando nova disponibilidade</h2>

<section id="availability-selection">
    <#list days as day>
        <div class="day-div" id="${day}">
            <#list shifts as shift>
                <input type="checkbox" value="${shift}" name="${shift}${day}" onclick="allowSelection('${shift}-${day}')">
            </#list>
            <div class="check-box"></div>
        </div>
    </#list>
</section>


<@form action="add_stop" method="post">
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
            <td>Sentido:</td>
            <td>
                <select name="direction">
                    <#list directions as direction>
                        <option value="${direction}">${direction}</option>
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
            <td><input type="hidden" value="${plan}" name="plan"></td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Proximo Passo"></td>

        </tr>
    </table>
</@form>



