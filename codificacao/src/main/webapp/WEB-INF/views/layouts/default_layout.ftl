<#setting url_escaping_charset='ISO-8859-1'>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <LINK href="${context_path}/css/main.css" rel="stylesheet" type="text/css"/>
    <script src="${context_path}/js/jquery-3.3.1.min.js" type="text/javascript"></script>
    <script src="${context_path}/js/bootstrap.bundle.min.js" type="text/javascript"></script>
    <script src="${context_path}/js/aw.js" type="text/javascript"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Sistema de gerenciamento de planos de viagem">
    <title>ActiveWeb - <@yield to="title"/></title>
</head>
<body>


<#include "header.ftl" >
    <section class="content">
    ${page_content}
    </section>
<#include "footer.ftl" >

</body>

</html>
