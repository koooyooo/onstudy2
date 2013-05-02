/******************************************************************************
 * Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/question/dao/xml/XmlAgent.java,v $
 * Version : $Revision: 1.1 $
 * Date    : $Date: 2007/04/19 07:38:08 $
 ******************************************************************************/
package org.onproject.onstudy.question.dao.xml;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.betwixt.io.BeanReader;
import org.apache.commons.betwixt.io.BeanWriter;
import org.apache.commons.betwixt.strategy.HyphenatedNameMapper;
import org.onproject.onstudy.exception.SystemException;
import org.xml.sax.SAXException;

/**
 * Xmlとオブジェクトの変換を司る代理人クラス<BR>
 * 
 * @author 恩田 好庸
 */
public class XmlAgent {
    
    /** 唯一のインスタンス */
    private static final XmlAgent instance = new XmlAgent();
    
    /**
     * コンストラクタ
     *
     */
    private XmlAgent() {
    }
    
    /**
     * インスタンス取得<BR>
     * 
     * @return 唯一のインスタンス
     */
    public static final XmlAgent getInstance() {
        return instance;
    }
    
    /**
     * オブジェクトをXMLに変換します。
     * 
     * @param targetObject
     * @throws XmlConvertException
     */
    public synchronized void convertToXml(OutputStream out, String charSet, Object targetObject) 
            throws SystemException {
        try {
            BeanWriter writer = new BeanWriter(out, charSet);
            writer.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);
            writer.getBindingConfiguration().setMapIDs(false);
            writer.enablePrettyPrint();
            writer.getXMLIntrospector().getConfiguration().setAttributeNameMapper(new HyphenatedNameMapper());
            writer.getXMLIntrospector().getConfiguration().setElementNameMapper(new HyphenatedNameMapper());
            //writer.getXMLIntrospector().getConfiguration().setElementNameMapper(new DecapitalizeNameMapper());
            writer.write(targetObject);
        } catch(UnsupportedEncodingException uee) {
            throw new SystemException(uee);   
        } catch(IntrospectionException ie) {
            throw new SystemException(ie);
        } catch(SAXException se) {
            throw new SystemException(se);
        } catch(IOException ioe) {
            throw new SystemException(ioe);
        }
    }
    
    /**
     * Xmlをオブジェクトに変換します。<BR>
     * 
     * @param in 入力ストリーム
     * @param clazz 指定クラス
     * @return Xmlより読み込まれたオブジェクト
     * @throws XmlConvertException
     */
    public synchronized Object convertToObject(InputStream in, Class clazz) throws SystemException {
        try {
            BeanReader reader = new BeanReader();
            reader.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);
            reader.getBindingConfiguration().setMapIDs(false);
            reader.getXMLIntrospector().getConfiguration().setAttributeNameMapper(new HyphenatedNameMapper());
            reader.getXMLIntrospector().getConfiguration().setElementNameMapper(new HyphenatedNameMapper());
            reader.registerBeanClass(clazz);
            return reader.parse(in);
            
        } catch(SAXException saxe) {
            throw new SystemException(saxe);
        } catch(IOException ioe) {
            throw new SystemException(ioe);
        } catch(IntrospectionException ie) {
            throw new SystemException(ie);
        }
    }
    
}
