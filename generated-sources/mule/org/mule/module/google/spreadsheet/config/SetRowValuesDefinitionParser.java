
package org.mule.module.google.spreadsheet.config;

import javax.annotation.Generated;
import org.mule.module.google.spreadsheet.config.AbstractDefinitionParser.ParseDelegate;
import org.mule.module.google.spreadsheet.model.holders.CellExpressionHolder;
import org.mule.module.google.spreadsheet.model.holders.RowExpressionHolder;
import org.mule.module.google.spreadsheet.processors.SetRowValuesMessageProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

@Generated(value = "Mule DevKit Version 3.4.3", date = "2014-03-26T12:36:12-05:00", comments = "Build 3.4.3.1620.30ea288")
public class SetRowValuesDefinitionParser
    extends AbstractDefinitionParser
{


    public BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(SetRowValuesMessageProcessor.class.getName());
        builder.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        parseConfigRef(element, builder);
        parseListWithDefaultAndSetProperty(element, builder, "rows", "rows", "row", "#[payload:]", new ParseDelegate<BeanDefinition>() {


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
