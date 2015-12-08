<#if alert?exists>
<script >
    alert("${alert}");
    <#if redirect?exists>
        window.location.href = "${redirect}";
    <#else>
        window.location.href = window.location.href;
    </#if>
</script>
</#if>