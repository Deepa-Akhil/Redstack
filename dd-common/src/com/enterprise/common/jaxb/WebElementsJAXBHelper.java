package com.enterprise.common.jaxb;

import org.springframework.stereotype.Component;

import com.enterprise.common.util.Constants;

@Component
public class WebElementsJAXBHelper extends AbstractJAXBHelper {
	private static final String[] schemaPaths = new String[] {
        "com/enterprise/common/GlobalDictionary.xsd",
        "com/enterprise/web/elements/WebElementComplexTypes.xsd"
    };
	
	@Override
	protected String[] getSchemaPaths() {
		return schemaPaths;
	}
	
	public InputElement validate(InputElement element, String title, String error) {
		try {
			super.validatedElementToXML(element);
			element = setElementAttributes(element,title,error,true);
		} catch (Exception e) {
			System.out.println("Validation failed on element [" + element.getClass().getSimpleName() + "], message: " + e.getMessage() + ", cause: " + e.getCause());
			element = this.setElementAttributes(element, title, error, false);
		}
		return element;
	}
	
	public InputElement setElementAttributes(InputElement element, String title, String error, boolean valid) {
		element.setValid(valid);
		if (valid) {
			element.setStyle(Constants.DEFAULT_STYLE);
			element.setStyleClassAlt(Constants.EMPTY_STR);
			element.setTitle(Constants.EMPTY_STR);
		} else {
			element.setTitle(title);
			element.setError(error);
			element.setStyle(Constants.ERROR_STYLE);
			element.setStyleClassAlt(Constants.ERROR_CLASS);
		}
		return element;
	}
}