
<@content for="title">Plano: ${plan.id}</@content>

<#list destinations as destination>
Teste: ${destination.id}
</#list>
<table>
    <tr>
        <td>Destino</td>
    </tr>
    <tr>
        <td id="destination_name"></td>
    </tr>
</table>

<@link_to>Retornar a listagem de planos</@link_to>
