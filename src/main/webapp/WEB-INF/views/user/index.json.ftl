[
<#list users as user>
    {
        "id": "${user.id}",
        "nome": "${user.nome}",
        "senha": "${user.senha}"
    }
    <#if user_has_next>,</#if>
</#list>
]



