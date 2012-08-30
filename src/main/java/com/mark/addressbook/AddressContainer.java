package com.mark.addressbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class AddressContainer {
    public static final String fileName = "Address.xml";
    private List<Address> addressList;

    /**
     * return addressList
     * 
     * @return
     */
    public List<Address> getAddressList() {
        return addressList;
    }

    /**
     * set addressList
     * 
     * @param addressList
     */
    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    /**
     * Initializes the address list
     */
    public AddressContainer() {
        addressList = new LinkedList<Address>();
        synchronized (addressList) {
            load();
        }
    }

    /**
     * load the address list form Address.xml
     * 
     */
    public void load() {
        File file = new File(fileName);
        if (file.exists()) {
            try {
                readXML(file);
            } catch (Exception e) {
                System.err.println("load XML failed.");
            }
        }
    }

    /**
     * export the address list to Address.xml
     * 
     * @throws IOException
     */
    public void export() throws IOException {
        File file = new File(fileName);
        if (file.exists()) {
            file.createNewFile();
        }
        synchronized (addressList) {
            exportXMLFile(file);
        }
    }

    private void exportXMLFile(File file) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = dbf.newDocumentBuilder();
        } catch (Exception e) {

        }
        Document doc = builder.newDocument();
        Element root = doc.createElement("AddressList");
        doc.appendChild(root);

        for (int i = 0; i < addressList.size(); i++) {
            Address a = addressList.get(i);
            Element e = doc.createElement("Address");
            root.appendChild(e);

            Element eName = doc.createElement("name");
            e.appendChild(eName);
            Text tName = doc.createTextNode(String.valueOf(a.getName()));
            eName.appendChild(tName);

            Element eMobile = doc.createElement("mobile");
            e.appendChild(eMobile);
            Text tMobile = doc.createTextNode(String.valueOf(a.getMobile()));
            eMobile.appendChild(tMobile);

            Element eAddress = doc.createElement("address");
            e.appendChild(eAddress);
            Text tAddress = doc.createTextNode(String.valueOf(a.getAddress()));
            eAddress.appendChild(tAddress);
        }

        FileOutputStream outStream = null;
        OutputStreamWriter outWriter = null;
        try {
            outStream = new FileOutputStream(file);
            outWriter = new OutputStreamWriter(outStream);
            callWriteXmlFile(doc, outWriter, "UTF-8");
        } catch (FileNotFoundException e) {
            System.err.println("File cannot Found.");
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

    private void readXML(File file) throws ParserConfigurationException,
            SAXException, IOException {
        addressList.clear();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document doc = builder.parse(file);
        Element root = doc.getDocumentElement();
        NodeList entrys = root.getElementsByTagName("Address");
        int length = entrys.getLength();
        for (int i = 0; i < length; i++) {
            Element element = (Element) entrys.item(i);
            NodeList nameList = element.getElementsByTagName("name");
            NodeList mobileList = element.getElementsByTagName("mobile");
            NodeList addList = element.getElementsByTagName("address");
            Address address = new Address(nameList.item(0).getTextContent(),
                    mobileList.item(0).getTextContent(), addList.item(0)
                            .getTextContent());
            addressList.add(address);
        }
    }

    private void callWriteXmlFile(Document doc, Writer w, String encoding) {
        try {
            // Prepare the DOM document for writing
            Source source = new DOMSource(doc);
            // Prepare the output file
            Result result = new StreamResult(w);
            // Write the DOM document to the file
            Transformer xformer = TransformerFactory.newInstance()
                    .newTransformer();
            xformer.setOutputProperty(OutputKeys.ENCODING, encoding);
            xformer.setOutputProperty(OutputKeys.INDENT, "yes");
            xformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            System.err
                    .println("the implementation is not available or cannot be instantiated.");
        } catch (TransformerException e) {
            System.err
                    .println("it is not possible to create a Transformer instance.");
        }
    }

    /**
     * add an entry into address List
     * 
     * @param name
     * @param mobile
     * @param address
     */
    public void add(String name, String mobile, String address) {
        synchronized (addressList) {
            addressList.add(new Address(name, mobile, address));
        }
        System.out.println("address entry added");
    }

    /**
     * delete all the entries whose name matches the given regex
     * @param regex
     * @return the count of deleted entries
     */
    public int deleteByName(String regex) {
        int count = 0;
        synchronized (addressList) {
            for (int i = 0; i < addressList.size(); i++) {
                Address a = addressList.get(i);
                if (Pattern.matches(regex, a.getName())) {
                    addressList.remove(i--);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * delete all the entries whose mobile matches the given regex
     * @param regex
     * @return the count of deleted entries
     */
    public int deleteByMobile(String regex) {
        int count = 0;
        synchronized (addressList) {
            for (int i = 0; i < addressList.size(); i++) {
                Address a = addressList.get(i);
                if (Pattern.matches(regex, a.getMobile())) {
                    addressList.remove(i--);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * delete all the entries whose address matches the given regex
     * @param regex
     * @return the count of deleted entries
     */
    public int deleteByAddress(String regex) {
        int count = 0;
        synchronized (addressList) {
            for (int i = 0; i < addressList.size(); i++) {
                Address a = addressList.get(i);
                if (Pattern.matches(regex, a.getAddress())) {
                    addressList.remove(i--);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * return the entries whose name matches the given regex
     * 
     * @param regex
     * @return the entries whose name matches the given regex
     */
    public List<Address> searchByName(String regex) {
        List<Address> ret = new ArrayList<Address>();
        synchronized (addressList) {
            for (Address a : addressList) {
                if (Pattern.matches(regex, a.getName())) {
                    ret.add(a);
                }
            }
        }
        print(ret);
        return ret;
    }

    /**
     * return the entries whose mobile matches the given regex
     * 
     * @param regex
     * @return the entries whose mobile matches the given regex
     */
    public List<Address> searchByMobile(String regex) {
        List<Address> ret = new ArrayList<Address>();
        synchronized (addressList) {
            for (Address a : addressList) {
                if (Pattern.matches(regex, a.getMobile())) {
                    ret.add(a);
                }
            }
        }
        print(ret);
        return ret;
    }

    /**
     * return the entries whose address matches the given regex
     * 
     * @param regex
     * @return the entries whose address matches the given regex
     */
    public List<Address> searchByAddress(String regex) {
        List<Address> ret = new ArrayList<Address>();
        synchronized (addressList) {
            for (Address a : addressList) {
                if (Pattern.matches(regex, a.getAddress())) {
                    ret.add(a);
                }
            }
        }
        print(ret);
        return ret;
    }

    /**
     * print the searched result well-formated.
     * 
     * @param ret
     */
    public static void print(List<Address> ret) {
        System.out.println(ret.size() + " address entries found");
        if (ret.size() > 0) {
            List<StringBuilder> nameList = new ArrayList<StringBuilder>(ret
                    .size());
            List<String> mobileList = new ArrayList<String>(ret.size());
            List<StringBuilder> addList = new ArrayList<StringBuilder>(ret
                    .size());
            int nameWidth = 5;
            int addressWidth = 7;
            int mobileWidth = 12;
            for (int i = 0; i < ret.size(); i++) {
                Address a = ret.get(i);
                nameList.add(new StringBuilder(a.getName()));
                if (a.getName().length() > nameWidth) {
                    nameWidth = a.getName().length();
                }
                mobileList.add(a.getMobile() + " ");
                addList.add(new StringBuilder(a.getAddress()));

                if (a.getAddress().length() > addressWidth) {
                    addressWidth = a.getName().length();
                }
            }
            nameWidth++;

            for (StringBuilder sb : nameList) {
                fill(sb, nameWidth);
            }
            for (StringBuilder sb : addList) {
                fill(sb, addressWidth);
            }

            String name = fill("Name", nameWidth);
            String address = fill("Address", addressWidth);
            String mobile = fill("Mobile", mobileWidth);
            System.out.println(name + mobile + address);
            for (int i = 0; i < ret.size(); i++) {
                System.out.println(nameList.get(i) + mobileList.get(i)
                        + addList.get(i));
            }

        }
    }

    private static void fill(StringBuilder sb, int length) {
        int size = sb.length();
        for (int i = 0; i < length - size; i++) {
            sb.append(" ");
        }
    }

    private static String fill(String s, int length) {
        int size = s.length();
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < length - size; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
