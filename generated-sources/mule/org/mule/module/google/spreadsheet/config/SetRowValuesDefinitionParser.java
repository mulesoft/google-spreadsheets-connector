
package org.mule.module.google.spreadsheet.config;

import javax.annotation.Generated;
import org.mule.config.MuleManifest;
import org.mule.module.google.spreadsheet.model.holders.CellExpressionHolder;
import org.mule.module.google.spreadsheet.model.holders.RowExpressionHolder;
import org.mule.module.google.spreadsheet.processors.SetRowValuesMessageProcessor;
import org.mule.security.oauth.config.AbstractDevkitBasedDefinitionParser;
import org.mule.security.oauth.config.AbstractDevkitBasedDefinitionParser.ParseDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.BeanDefinitionParsingException;
import org.springframework.beans.factory.parsing.Location;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

@Generated(value = "Mule DevKit Version 3.5.0-M4", date = "2014-03-26T12:37:34-05:00", comments = "Build M4.1875.17b58a3")
public class SetRowValuesDefinitionParser
    extends AbstractDevkitBasedDefinitionParser
{

    private static Logger logger = LoggerFactory.getLogger(SetRowValuesDefinitionParser.class);

    private BeanDefinitionBuilder getBeanDefinitionBuilder(ParserContext parserContext) {
        try {
            return BeanDefinitionBuilder.rootBeanDefinition(SetRowValuesMessageProcessor.class.getName());
        } catch (NoClassDefFoundError noClassDefFoundError) {
            String muleVersion = "";
            try {
                muleVersion = MuleManifest.getProductVersion();
            } catch (Exception _x) {
                logger.error("Problem while reading mule version");
            }
            logger.error(("Cannot launch the mule app, the @Processor [set-row-values] within the connector [google-spreadsheets] is not supported in mule "+ muleVersion));
            throw new BeanDefinitionParsingException(new Problem(("Cannot launch the mule app, the @Processor [set-row-values] within the connector [google-spreadsheets] is not supported in mule "+ muleVersion), new Location(parserContext.getReaderContext().getResource()), null, noClassDefFoundError));
        }
    }

    public BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = getBeanDefinitionBuilder(parserContext);
        builder.addConstructorArgValue("setRowValues");
        builder.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        parseConfigRef(element, builder);
        parseListWithDefaultAndSetProperty(element, builder, "rows", "rows", "row", "#[payload]", new ParseDelegate<BeanDefinition>() {


            public BeanDefinition parse(Element element) {
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(RowExpressionHolder.class);
                parseProperty(builder, element, "rowNumber", "rowNumber");
                parseListAndSetProperty(element, builder, "cells", "cells", "cell", new ParseDelegate<BeanDefinition>() {


                    public BeanDefinition parse(Element element) {
                        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(CellExpressionHolder.class);
                        parseProperty(builder, element, "rowNumber", "rowNumber");
                        parseProperty(builder, element, "columnNumber", "columnNumber");
                        parseProperty(builder, element, "valueOrFormula", "valueOrFormula");
                        parseProperty(builder, element, "evaluatedValue", "evaluatedValue");
                        return builder.getBeanDefinition();
                    }

                }
                );
                return builder.getBeanDefinition();
            }

        }
        );
        parseProperty(builder, element, "spreadsheet", "spreadsheet");
        parseProperty(builder, element, "worksheet", "worksheet");
        parseProperty(builder, element, "spreadsheetIndex", "spreadsheetIndex");
        parseProperty(builder, element, "worksheetIndex", "worksheetIndex");
        parseProperty(builder, element, "purge", "purge");
        parseProperty(builder, element, "accessTokenId");
        BeanDefinition definition = builder.getBeanDefinition();
        setNoRecurseOnDefinition(definition);
        attachProcessorDefinition(parserContext, definition);
        return definition;
    }

}
