<@content for="title">Selecionar Estado</@content>


<div class="message"><@flash name="message"/></div>

<section class="list-items">
    <#list states as state>
        <@link_to action="cities" id="${state.id}" class="bt-a">${state.name}</@link_to>
    </#list>
</section>