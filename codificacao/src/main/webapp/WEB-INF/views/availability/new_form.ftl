<@content for="title">Adicionar nova disponibilidade</@content>
<script src="${context_path}/js/destinationTable.js" type="text/javascript"></script>

<span class="error_message"><@flash name="message"/></span>
<h2>Adicionando nova disponibilidade</h2>

<section id="availability-selection" class="availability">
    <#list days as day>
        <div class="day-div">
            <div class="day-shifts-div">
                <div class="shift-div">
                    <#list shifts as shift>
                        <div class="individual-check">
                            <div class="individual-shift-check" id="${day}${shift.name}">
                                <p>${shift.name}</p>
                                <input type="checkbox"
                                       <#if shift.hasStops == true>
                                       <#else>disabled="disabled"</#if>
                                       value="${shift.name}" name="${shift.name}${day}" onclick="allowSelection('${shift.name}-${day}')">
                            </div>
                            <div class="check-box">
                                <div class="individual-check">
                                    <p>Ida</p>
                                    <input type="checkbox">
                                </div>
                                <div class="individual-check">
                                    <p>Volta</p>
                                    <input type="checkbox">
                                </div>
                            </div>
                        </div>
                    </#list>
                </div>
                <select name="driver">
                        <#list drivers as driver>
                            <option value="${driver.id}">${driver.name + " " + driver.surname}</option>
                        </#list>
                </select>
                <select name="vehicle">
                        <#list vehicles as vehicle>
                            <option value="${vehicle.id}">${vehicle.license_plate}</option>
                        </#list>
                </select>
            </div>
            <div class="stops-box" hidden="hidden"></div>
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
                        <option value="${shift.name}">${shift.name}</option>
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



