<section id="plans">
    <#assign a = 0>
    <#list days as day>
            <#list shifts as shift>
                <div class="day-shift-cell">
                    ${day} - ${shift}
                    <#list directions as direction>
                        <div class="direction-cell">
                            ${direction}
                            <#if availabilitiesSubSets[a]??>
                                <#list availabilitiesSubSets[a] as subSet>
                                    <#if direction?index == subSet.direction>
                                        <li>Teste</li>
                                    </#if>
                                </#list>
                            </#if>
                        </div>
                    </#list>
                </div>
            <br>
                <#assign a++>
            </#list>
    </#list>
</section>