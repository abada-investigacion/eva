<#import "/spring.ftl" as spring />
<title><@spring.message code="title" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<!--CSS Ext4 -->
<link rel="stylesheet" type="text/css" href="<@spring.url relativeUrl="/ext4/resources/css/ext-all.css"/>" />
<link rel="stylesheet" type="text/css" href="<@spring.url relativeUrl="/abada/css/abada.css"/>" />
<!--Ext4 base -->
<!--script src="<@spring.url relativeUrl="/ext4/bootstrap.js"/>" type="text/javascript"></script-->
<script src="<@spring.url relativeUrl="/ext4/builds/ext-core-debug.js" />" type="text/javascript"></script>

<script type="text/javascript">
    Ext.Loader.setConfig({
        enabled: true,
        paths:{
            'Ext': '<@spring.url relativeUrl="/ext4/src" />',
            'Eva': '<@spring.url relativeUrl="/" />',
            'Abada': '<@spring.url relativeUrl="/abada" />'
        }
    });

    Eva={};    
    Eva.height=600;
</script>
<!--locales-->
<!--script src="<@spring.url relativeUrl="/ext4/locale/ext-lang-es.js"/>" type="text/javascript"></script-->