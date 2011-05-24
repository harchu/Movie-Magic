<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    
                xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:h="http://www.w3.org/1999/xhtml"
				xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
				xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#"
				xmlns:hr="http://www.w3.org/2000/08/w3c-synd/#"
				xmlns:pre="http://purl.org/rss/1.0/" version="1.0">
				
<xsl:template match="/">
	<html>
            <body><p>
				<h1>Titles(with links)</h1>
				<xsl:apply-templates select="//item/title"/>
            </p></body>
    </html>
</xsl:template>

<xsl:template match="title">
	<a href="{../link}"><xsl:value-of select="."/></a><br/>
</xsl:template>

</xsl:stylesheet>  