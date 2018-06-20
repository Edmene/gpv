<#list plans as plan>
    <@link_to action="reservation_list" id=plan.id>
                <div class="destination">
                    ${plan.destination.name}
                </div>
    </@link_to>
</#list>