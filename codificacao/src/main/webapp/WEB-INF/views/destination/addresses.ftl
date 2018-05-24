<@content for="title">Lista de Enderecos</@content>


<div class="message"><@flash name="message"/></div>

<#list addresses as address>
    <@link_to action="destinations" id="${address.id}">${address.name}</@link_to>
</#list>