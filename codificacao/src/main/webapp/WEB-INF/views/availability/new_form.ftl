<@content for="title">Adicionar nova disponibilidade</@content>
<script src="${context_path}/js/availability.js" type="text/javascript"></script>

<span class="error_message"><@flash name="message"/></span>
<h2>Adicionando nova disponibilidade</h2>

<main id="availability-selection" class="availability">
    <section id="base-city">
        <label>Selecao de estado base</label>
        <select id="states" oninput="updateCities(this)">
            <option value="0">Selecione uma opcao</option>
            <#list states as state>
                <option value="${state.id}" >${state.name}</option>
            </#list>
        </select>
        <label>Selecao de Cidade base</label>
        <select id="cities">
            <option value="0">Selecione uma cidade</option>
        </select>
    </section>
    <#list days as day>
        <section class="day-div" id="${day}">
            <strong class="day-name">${day}</strong>
            <div class="day-shifts-div">
                <div class="shift-div">
                    <#list shifts as shift>
                        <div class="individual-check" id="${day}-${shift.name}">
                            <div class="individual-shift-check">
                                <label>${shift.name}</label>
                                <input type="checkbox"
                                       <#if shift.hasStops == true>
                                       <#else>disabled="disabled"</#if>
                                       value="${shift.name}" name="${shift.name}${day}" onchange="allowSelection('${day}-${shift.name}')">
                            </div>
                        </div>
                    </#list>
                </div>
                <select class="driver-select">
                        <#list drivers as driver>
                            <option value="${driver.id}">${driver.name + " " + driver.surname}</option>
                        </#list>
                </select>
                <select class="vehicle-select">
                        <#list vehicles as vehicle>
                            <option value="${vehicle.id}">${vehicle.license_plate}</option>
                        </#list>
                </select>
                <button class="show-stop" onclick="showStops('${day}')" id="button-${day}">Mostrar Paradas</button>
            </div>
            <div class="stops-box" id="stopsOf${day}">
                <div class="table-button-container">
                    <strong>Paradas Ida</strong>
                    <select name="stopsBase${day}" id="stopsBase${day}"></select>
                    <button id="Base${day}" onclick="tableInteraction(this)">Adicionar parada</button>
                    <table class="table-base">
                        <thead>
                            <tr>
                                <td>Destino</td>
                                <td>Remover</td>
                            </tr>
                        </thead>
                    </table>
                </div>

                <div class="table-button-container">
                    <strong>Paradas Volta</strong>
                    <select name="stopsTarget${day}" id="stopsTarget${day}"></select>
                    <button id="Target${day}" onclick="tableInteraction(this)">Adicionar parada</button>
                    <table class="table-destination">
                        <thead>
                        <tr>
                            <td>Destino</td>
                            <td>Remover</td>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </section>
    </#list>
    <div class="day-shifts-div" id="confirmation-row">
        <div>
            <input type="checkbox" id="confirmation-check" onchange="enableSendButton()">
            <label>Confirmar</label>
        </div>
        <button type="submit" onclick="formJSON()" id="send-button" disabled="disabled">Enviar</button>
    </div>
</main>




