package com.enterprise.common.jaxb;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.enterprise.common.xsd.Element;

public abstract class AbstractJAXBHelper {
	protected abstract String[] getSchemaPaths();
	
	public String validatedElementToXML(Element element) {
    	String requestXml = null;
    	try {
			Schema schema = null;
			List<Source> schemaPathList = new ArrayList<Source>();
			String[] schemaPaths = getSchemaPaths();
			for (String schemaPath : schemaPaths) {
				InputStream schemaPathIS = this.getClass().getClassLoader().getResourceAsStream(schemaPath);
				SAXSource source = new SAXSource(new InputSource(schemaPathIS));
				schemaPathList.add(source);
			}
			if (schemaPathList.size() > 0) {
				SchemaFactory sf = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
				schema = sf.newSchema(schemaPathList.toArray(new Source[schemaPathList.size()]));
			}
			requestXml = marshalXml(element, schema);
    	} catch (SAXException e) {
    		e.printStackTrace();
    	} catch (JAXBException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return requestXml;
	}
	
    public String validatedElementToXML(InputElement element) throws SAXException, JAXBException, IOException {
    	String requestXml = null;
		Schema schema = null;
		List<Source> schemaPathList = new ArrayList<Source>();
		String[] schemaPaths = getSchemaPaths();
		for (String schemaPath : schemaPaths) {
			InputStream schemaPathIS = this.getClass().getClassLoader().getResourceAsStream(schemaPath);
			SAXSource source = new SAXSource(new InputSource(schemaPathIS));
			schemaPathList.add(source);
		}
		if (schemaPathList.size() > 0) {
			SchemaFactory sf = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
			schema = sf.newSchema(schemaPathList.toArray(new Source[schemaPathList.size()]));
		}
		requestXml = marshalXml(element, schema);
    	return requestXml;
	}
    
    public String unvalidatedElementToXML(InputElement element) throws Exception {
    	String requestXml = marshalXml(element, null);
		return requestXml;
    }
    
    private String marshalXml(InputElement element, Schema schema) throws JAXBException, SAXException, IOException {
        javax.xml.bind.JAXBContext jaxbContext = javax.xml.bind.JAXBContext.newInstance(element.getClass());
        javax.xml.bind.Marshaller marshaller = jaxbContext.createMarshaller();
        if (schema != null) {
            marshaller.setSchema(schema);
        }
        StringWriter string = new StringWriter();
        marshaller.marshal(element, string);      
        String str = string.toString();
        return str;
    }
    
    private String marshalXml(Element element, Schema schema) throws JAXBException, SAXException, IOException {
        javax.xml.bind.JAXBContext jaxbContext = javax.xml.bind.JAXBContext.newInstance(element.getClass());
        javax.xml.bind.Marshaller marshaller = jaxbContext.createMarshaller();
        if (schema != null) {
            marshaller.setSchema(schema);
        }
        StringWriter string = new StringWriter();
        marshaller.marshal(element, string);      
        String str = string.toString();
        return str;
    }
}