/**
 * @author luxr123
 * @email luxr123@sjtu.edu.cn
 */
package cn.edu.sjtu.ab;
/**
 * ab DAO
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class AddressBookDAO {

	public static final String filename = "ab_info.xml";
	private List<AddressBookInfo> infoList;
	private Pattern p;
	private List<AddressBookInfo> result;

	public List<AddressBookInfo> getInfoList() {
		return infoList;
	}

	/**
	 * initializes the ab list
	 */
	public AddressBookDAO() {
		infoList = new ArrayList<AddressBookInfo>();
		synchronized (infoList) {
			init();
		}
	}

	/**
	 * load the address list form ab_info.xml
	 */
	public void init() {
		File file = new File(filename);
		if (file.exists()) {
			try {
				infoList.clear();
				infoList = XmlUtils.getSaxDocument(filename);
			} catch (ParserConfigurationException e) {
				System.err.println("a DocumentBuilder cannot be created which satisfies the configuration requested.");
			} catch (SAXException e) {
				System.err.println("The File \"ab_info.xml\" is not formatted right");
			} catch (IOException e) {
				System.err.println("sorry,some IO Exception occurs.");
			}
		}
	}

	/**
	 * export the infolist to ab_info.xml
	 */
	public void export2XML() {
		synchronized (infoList) {
			exportXMLFile();
		}
	}

	private void exportXMLFile() {
		Document doc = null;
		FileOutputStream outStream = null;
		OutputStreamWriter outWriter = null;
		try {
			doc = XmlUtils.getDocument();
			Element root = doc.createElement("abinfos");
			doc.appendChild(root);
			for (int i = 0; i < infoList.size(); i++) {
				AddressBookInfo info = infoList.get(i);
				Element abinfo_tag = doc.createElement("abinfo");
				Element name_tag = doc.createElement("name");
				Element mobile_tag = doc.createElement("mobile");
				Element address_tag = doc.createElement("address");

				name_tag.setTextContent(info.getName());
				mobile_tag.setTextContent(info.getMobile());
				address_tag.setTextContent(info.getAddress());
				abinfo_tag.appendChild(name_tag);
				abinfo_tag.appendChild(mobile_tag);
				abinfo_tag.appendChild(address_tag);
				root.appendChild(abinfo_tag);
				// update the ab_info.xml
				XmlUtils.write2Xml(doc, filename);
			}
			outStream = new FileOutputStream(filename);
			outWriter = new OutputStreamWriter(outStream);
			XmlUtils.write2Xml(outStream, outWriter, doc, filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("File cannot Found.");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			System.out.println("a DocumentBuilder cannot be created which satisfies the configuration requested.");
		} catch (TransformerException e) {
			e.printStackTrace();
			System.err.println("it is not possible to create a Transformer instance.");
		} finally {
			try {
				outWriter.close();
			} catch (IOException e) {
			}
			try {
				outStream.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * search the entries who matches the given regex
	 * 
	 * @param regex
	 * @return the entries who matches the given regex
	 */
	public List<AddressBookInfo> searchByType(String searchType, String regex) {
		try {
			p = Pattern.compile(regex);
		} catch (PatternSyntaxException e) {
			System.out.println("sorry: " + regex + " is an invalid regular-expression pattern.");
			return null;
		}
		result = new ArrayList<AddressBookInfo>();
		synchronized (infoList) {
			for (AddressBookInfo info : infoList) {
				if ("name".equalsIgnoreCase(searchType) && p.matcher(info.getName()).matches()) {
					result.add(info);
					continue;
				} else if ("mobile".equalsIgnoreCase(searchType) && p.matcher(info.getMobile()).matches()) {
					result.add(info);
					continue;
				} else if ("address".equalsIgnoreCase(searchType) & p.matcher(info.getAddress()).matches()) {
					result.add(info);
					continue;
				}
			}
		}
		XmlUtils.printSearchResult(result);
		return result;
	}

	/**
	 * add an entry into ab list
	 * 
	 * @param name
	 * @param mobile
	 * @param address
	 */
	public void add(String name, String mobile, String address) {
		synchronized (infoList) {
			infoList.add(new AddressBookInfo(name, mobile, address));
		}
		System.out.println("ab entry added");
	}

	/**
	 * delete all the entries who matches the given regex
	 * 
	 * @param regex
	 * @return the count of deleted entries
	 */
	public int deleteByType(String searchType, String regex) {
		try {
			p = Pattern.compile(regex);
		} catch (PatternSyntaxException e) {
			System.out.println("sorry: \"" + regex + "\" is an invalid regular-expression pattern.");
			return -1;
		}
		int count = 0;
		synchronized (infoList) {
			for (int i = 0; i < infoList.size(); i++) {
				AddressBookInfo info = infoList.get(i);
				if ("name".equalsIgnoreCase(searchType) && p.matcher(info.getName()).matches()) {
					infoList.remove(i--);
					count++;
					continue;
				} else if ("mobile".equalsIgnoreCase(searchType) && p.matcher(info.getMobile()).matches()) {
					infoList.remove(i--);
					count++;
					continue;
				} else if ("address".equalsIgnoreCase(searchType) && p.matcher(info.getAddress()).matches()) {
					infoList.remove(i--);
					count++;
					continue;
				}
			}
		}
		return count;
	}
}