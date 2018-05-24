<@content for="title">Selecionar Estado</@content>


<div class="message"><@flash name="message"/></div>


<#list states as state>
    <@link_to action="cities" id="${state.id}">${state.name}</@link_to>
</#list>