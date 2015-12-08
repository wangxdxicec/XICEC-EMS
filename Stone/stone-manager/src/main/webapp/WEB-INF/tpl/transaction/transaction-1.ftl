<#if transaction.boothNumber?exists>
${transaction.boothNumber}
</#if>

<#if transaction.company?exists>
${transaction.company}
</#if>
<#if transaction.companye?exists>
${transaction.companye}
</#if>

<#if transaction.address?exists && transaction.addressEn?exists>
地址/Add: ${transaction.address}
          ${transaction.addressEn}
<#elseif transaction.address?exists>
地址/Add: ${transaction.address}
<#elseif transaction.addressEn?exists>
地址/Add: ${transaction.addressEn}
</#if>
<#if transaction.phone?exists>
电话/Tel: ${transaction.phone}
</#if>
<#if transaction.fax?exists>
传真/Fax: ${transaction.fax}
</#if>
<#if transaction.website?exists>
网址/Web: ${transaction.website}
</#if>
<#if transaction.email?exists>
电子邮箱/E-mail: ${transaction.email}
</#if>
<#if transaction.mainProduct?exists || transaction.mainProductEn?exists>

主营产品/Special Products:
</#if>
<#if transaction.mainProduct?exists>
${transaction.mainProduct}
</#if>
<#if transaction.mainProductEn?exists>
${transaction.mainProductEn}
</#if>
<#if transaction.product?exists || transaction.productEn?exists>

产品类别/Product Range:
</#if>
<#if transaction.product?exists>
${transaction.product}<#if transaction.productOther?exists>，其他：${transaction.productOther}</#if>
</#if>
<#if transaction.productEn?exists>
${transaction.productEn}<#if transaction.productOther?exists>, Others: ${transaction.productOther}</#if>
</#if>