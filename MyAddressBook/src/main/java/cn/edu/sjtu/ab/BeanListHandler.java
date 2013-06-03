/**
 * @author luxr123
 * @email luxr123@sjtu.edu.cn
 */
package cn.edu.sjtu.ab;

/**
 * put the xml file into the bean object and 
 * a plurality of bean stored in the list
 */
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BeanListHandler extends DefaultHandler {

	private AddressBookInfo info;
	private String currentTag;
	private List<AddressBookInfo> list = new ArrayList<AddressBookInfo>();

	public List<AddressBookInfo> getInfos() {
		return list;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		currentTag = qName;
		if ("abinfo".equals(currentTag)) {
			info = new AddressBookInfo();
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if ("name".equals(currentTag)) {
			info.setName(new String(ch, start, length));
		}
		if ("mobile".equals(currentTag)) {
			info.setMobile(new String(ch, start, length));
		}
		if ("address".equals(currentTag)) {
			info.setAddress(new String(ch, start, length));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("abinfo".equals(qName)) {
			list.add(info);
			info = null;
		}
		currentTag = null;
	}
}
