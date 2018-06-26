<@content for="title">Selecionar Endereco</@content>


<div class="message"><@flash name="message"/></div>

<#list addresses as address>
    <@link_to action="stops" id="${address.id}">${address.name}</@link_to>
</#list>