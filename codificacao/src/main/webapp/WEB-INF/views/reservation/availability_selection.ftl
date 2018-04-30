<section id="plans">
    <#list days as day>
            <#list shifts as shift>
                <div class="day-shift-cell">
                    ${day} - ${shift}
                    <#list directions as direction>
                        <div class="direction-cell">
                            ${direction}
                        </div>
                    </#list>
                </div>
            <br>
            </#list>
    </#list>
</section>