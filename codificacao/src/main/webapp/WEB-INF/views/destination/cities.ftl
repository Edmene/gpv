<@content for="title">Selecionar cidade</@content>


<div class="message"><@flash name="message"/></div>

<section class="list-items">
    <#list cities as city>
        <@link_to action="addresses" id="${city.id}" class="bt-a">${city.name}</@link_to>
    </#list>
</section>