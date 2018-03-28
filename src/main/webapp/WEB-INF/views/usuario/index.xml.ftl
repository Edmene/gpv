<books>
<#list usuarios as usuario>
    <book>
        <id>${usuario.id}</id>
        <title>${usuario.nome}</title>
        <author>${usuario.senha}</author>
    </book>
</#list>
</books>



