<@content for="title">Selecionar cidade</@content>


<div class="message"><@flash name="message"/></div>


<#list cities as city>
    <@link_to action="addresses" id="${city.id}">${city.name}</@link_to>
</#list>