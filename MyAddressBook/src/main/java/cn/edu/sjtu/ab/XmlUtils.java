/**
 * @author luxr123
 * @email luxr123@sjtu.edu.cn
 */
package cn.edu.sjtu.ab;
/**
 * Xml Utils
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public final class XmlUtils {

	/**
	 * get proxy Document
	 * 
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static Document getDocument() throws ParserConfigurationException {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
		return documentBuilder.newDocument();
	}

	/**
	 * SAXParser Document
	 * 
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws IOException
	 */
	public static List<AddressBookInfo> getSaxDocument(String filename) throws ParserConfigurationException, SAXException, IOException {
		// create parser factory
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// get the parser
		SAXParser parser = factory.newSAXParser();
		// get reader
		XMLReader reader = parser.getXMLReader();
		// set content processor
		BeanListHandler handler = new BeanListHandler();
		reader.setContentHandler(handler);
		// read xml document
		reader.parse(filename);

		List<AddressBookInfo> list = handler.getInfos();
		return list;
	}

	public static void write2Xml(Document document, String filename) throws FileNotFoundException, TransformerException {

		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		transformer.transform(new DOMSource(document), new StreamResult(new FileOutputStream(filename)));
	}

	public static void write2Xml(FileOutputStream outStream, OutputStreamWriter outWriter, Document document, String filename)
			throws FileNotFoundException, TransformerFactoryConfigurationError, TransformerException {
		outStream = new FileOutputStream(filename);
		outWriter = new OutputStreamWriter(outStream);
		callWrite2Xml(document, outWriter, "UTF-8");
	}

	public static void callWrite2Xml(Document document, Writer writer, String encoding) throws TransformerFactoryConfigurationError,
			TransformerException {
		// Prepare the DOM document for writing
		Source source = new DOMSource(document);
		// Prepare the output file
		Result result = new StreamResult(writer);
		// Write the DOM document to the file
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, encoding);
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(source, result);
	}

	/**
	 * show all available commands
	 * 
	 */
	public static void showCommands() {
		System.out.println("command list:");
		System.out.println("add|search|delete|!help|!quit");
		System.out.println("add: add ab entry into address.xml");
		System.out.println("search: get one or more ab entries by conditions");
		System.out.println("delete: delete ab entries by conditions");
		System.out.println("!help: show help");
		System.out.println("!quit: exit");
	}

	/**
	 * print the searched result well-formated.
	 * 
	 * @param result
	 */
	public static void printSearchResult(List<AddressBookInfo> result) {
		System.out.println(result.size() + " abInfo entries found");
		if (result.size() > 0) {
			List<StringBuilder> nameList = new ArrayList<StringBuilder>(result.size());
			List<String> mobileList = new ArrayList<String>(result.size());
			List<StringBuilder> addList = new ArrayList<StringBuilder>(result.size());
			int nameWidth = 5;
			int addressWidth = 7;
			int mobileWidth = 12;
			for (int i = 0; i < result.size(); i++) {
				AddressBookInfo abInfo = result.get(i);
				nameList.add(new StringBuilder(abInfo.getName()));
				if (abInfo.getName().length() > nameWidth) {
					nameWidth = abInfo.getName().length();
				}
				mobileList.add(abInfo.getMobile() + " ");
				addList.add(new StringBuilder(abInfo.getAddress()));

				if (abInfo.getAddress().length() > addressWidth) {
					addressWidth = abInfo.getName().length();
				}
			}
			nameWidth++;
			for (StringBuilder sb : nameList) {
				chmodSB(sb, nameWidth);
			}
			for (StringBuilder sb : addList) {
				chmodSB(sb, addressWidth);
			}

			String name = chmodSB("name", nameWidth);
			String mobile = chmodSB("mobile", mobileWidth);
			String address = chmodSB("address", addressWidth);
			System.out.println(name + mobile + address);
			for (int i = 0; i < result.size(); i++) {
				System.out.println(nameList.get(i) + mobileList.get(i) + addList.get(i));
			}

		}
	}

	private static void chmodSB(StringBuilder sb, int length) {
		int size = sb.length();
		for (int i = 0; i < length - size; i++) {
			sb.append(" ");
		}
	}

	private static String chmodSB(String args, int length) {
		int size = args.length();
		StringBuilder sb = new StringBuilder(args);
		for (int i = 0; i < length - size; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}

	public static boolean checkSearchType(String searchType) {
		if ("name".equalsIgnoreCase(searchType) || "mobile".equalsIgnoreCase(searchType) || "address".equalsIgnoreCase(searchType)) {
			return true;
		}
		System.out.println("sorry: \"" + searchType + "\" is an invalid command.");
		return false;
	}
}
