[
<#list users as user>
    {
        "id": "${user.id}",
        "name": "${user.name}",
        "password": "${user.password}"
    }
    <#if user_has_next>,</#if>
</#list>
]



