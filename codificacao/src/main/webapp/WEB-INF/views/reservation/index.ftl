<section id="destinations">
        <#list destinations as destination>
            <@link_to action="planSelection" id=destination.id class="destination_link">
                <div class="destination">
                    ${destination.name}
                </div>
            </@link_to>
        </#list>
</section>