<books>
<#list usuarios as user>
    <book>
        <id>${user.id}</id>
        <title>${user.nome}</title>
        <author>${user.senha}</author>
    </book>
</#list>
</books>



