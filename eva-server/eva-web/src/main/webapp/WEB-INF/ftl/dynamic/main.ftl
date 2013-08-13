<html>
<head>
    <#include "/dynamic/head_initial.ftl" parse=true />    
    <#if css??>
        <#list css as c>            
    <link rel="stylesheet" type="text/css" href="<@spring.url relativeUrl="${c}"/>" />
        </#list>
    </#if>

    <script src="<@spring.url relativeUrl="/abada/ext-abada-utils.js"/>" type="text/javascript"></script>

    <script src="<@spring.url relativeUrl="/js/main.js"/>" type="text/javascript"></script>    

    <#if js??>
        <#list js as j>
    <script src="<@spring.url relativeUrl="${j}"/>" type="text/javascript"></script>
        </#list>
    </#if>
</head>
<body>
</body>
</html>