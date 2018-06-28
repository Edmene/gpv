<#setting url_escaping_charset='ISO-8859-1'>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Sistema de gerenciamento de planos de viagem">
    <title>ActiveWeb - <@yield to="title"/></title>
    <LINK href="${context_path}/css/main.css" rel="stylesheet" type="text/css"/>
    <#if session.id??>
        <script src="${context_path}/js/nav.js" type="text/javascript"></script>
    </#if>
    <script src="${context_path}/js/currentUrl.js" type="text/javascript"></script>
    <script src="${context_path}/js/jquery-3.3.1.min.js" type="text/javascript"></script>
    <script src="${context_path}/js/aw.js" type="text/javascript"></script>
    <script src="${context_path}/js/jquery.mask.min.js" type="text/javascript"></script>
    <script src="${context_path}/js/validators.js" type="text/javascript"></script>
    <#if reservation??>
        <script src="${context_path}/js/reservation.js" type="text/javascript"></script>
    </#if>
    <#if selection??>
        <script src="${context_path}/js/checkSelection.js" type="text/javascript"></script>
    </#if>
    <#if destination??>
        <script src="${context_path}/js/destinationTable.js" type="text/javascript"></script>
        <#if add??>
        <script src="${context_path}/js/clearDestinationsOnReload.js" type="text/javascript"></script>
        </#if>
    </#if>
    <#if availability??>
        <script src="${context_path}/js/availability.js" type="text/javascript"></script>
    </#if>
</head>
<body>


<#include "header.ftl" >
    <section class="content">
        ${page_content}
    </section>

</body>

</html>
