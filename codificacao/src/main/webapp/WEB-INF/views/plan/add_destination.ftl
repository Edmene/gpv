
<@content for="title">Adicionar destinos do plano: ${plan.id}</@content>

<@link_to class="bt-a">Retornar a listagem de planos</@link_to>

<div id="destination-add">
    <div id="select-flow">
        <select id="select_state" oninput="selectFlow(this, 0)">
            <option value="0">Selecione um estado</option>
            <#list states as state>
                <option value="${state.id}" label="${state.name}">${state.name}</option>
            </#list>
        </select>

        <select id="select_city" oninput="selectFlow(this)">
            <option value="0">Selecione uma opcao</option>
        </select>

        <select id="select_item">
            <option value="0">Selecione uma opcao</option>
        </select>

        <button id="select_add" class="bt-a" onclick="addTable(this)">Adicionar destino</button>
    </div>




    <table id="table">
        <tr>
            <td>Destino</td>
            <td>Remover</td>
        </tr>
    </table>

    <@form action="addDestinations" method="post" name="addDestinations">
        <input id="item" type="hidden" value="" name="items">
        <input type="hidden" value="${plan.id}" name="plan">
        <button type="submit" class="bt-a">Confirmar destinos</button>
    </@form>
</div>

