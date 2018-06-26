<@content for="title">Lista de Enderecos</@content>


<div class="message"><@flash name="message"/></div>

<section class="list-items">
    <#list addresses as address>
        <@link_to action="destinations" id="${address.id}" class="bt-a">${address.name}</@link_to>
    </#list>
</section>