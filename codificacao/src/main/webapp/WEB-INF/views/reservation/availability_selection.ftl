<section id="plans">
    <#assign a = 0>
    <#list days as day>
        <div class="dayOfWeek-cell" id="${day}">
            <#list shifts as shift>
                <div class="shift-cell" id="${shift}">
                    ${day} - ${shift}
                    <#list directions as direction>
                        <div class="direction-cell" id="${direction}">
                            ${direction}
                            <#if availabilitiesSubSets[a]??>
                                <#list availabilitiesSubSets[a] as subSet>
                                    <#if subSet.direction??>
                                        <#if direction?index == subSet.direction>
                                            ${subSet.address.name} - ${subSet.stop.time}
                                            <input type="radio" name="${direction}"
                                                   value="${subSet.driver_id},${subSet.vehicle_id},${subSet.stop_id}">
                                        </#if>
                                    </#if>
                                </#list>
                            </#if>
                        </div>
                    </#list>
                </div>
            <br>
                <#assign a++>
            </#list>
        </div>
    </#list>
</section>