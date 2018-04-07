[
<#list users as user>
    {
        "id": "${user.id}",
        "name": "${user.name}",
        "type": "${user.type}"
    }
    <#if user_has_next>,</#if>
</#list>
]



