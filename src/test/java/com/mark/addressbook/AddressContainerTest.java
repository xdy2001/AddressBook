package com.mark.addressbook;

import static org.junit.Assert.assertEquals;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddressContainerTest {

    private AddressContainer container = null;

    @Before
    public void setUp() throws Exception {
        File file = new File("Address.xml");
        if (file.exists()) {
            file.delete();
        }
        container = new AddressContainer();
        List<Address> list = container.getAddressList();
        list.clear();
    }

    @After
    public void tearDown() throws Exception {
        container.getAddressList().clear();
        File file = new File("Address.xml");
        if (file.exists()) {
            file.delete();
        }
    }

    public void init() {
        List<Address> list = container.getAddressList();
        for (int i = 0; i < 10; i++) {
            list.add(new Address("yuan jiang" + i, "1381707031" + i, "shanghai"
                    + i));
        }
    }

    public void initFile() {
        File file = new File("Address.xml");
        if (file.exists()) {
            file.delete();
        }
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuffer sb = new StringBuffer();
        sb
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n");
        sb.append("<AddressList>\n");
        sb.append("<Address>\n");
        sb.append("<name>yuan jiang0</name>\n");
        sb.append("<mobile>13817070310</mobile>\n");
        sb.append("<address>shanghai0</address>\n");
        sb.append("</Address>\n");
        sb.append("<Address>\n");
        sb.append("<name>yuan jiang1</name>\n");
        sb.append("<mobile>13817070311</mobile>\n");
        sb.append("<address>shanghai1</address>\n");
        sb.append("</Address>\n");
        sb.append("<Address>\n");
        sb.append("<name>yuan jiang2</name>\n");
        sb.append("<mobile>13817070312</mobile>\n");
        sb.append("<address>shanghai2</address>\n");
        sb.append("</Address>\n");
        sb.append("<Address>\n");
        sb.append("<name>yuan jiang3</name>\n");
        sb.append("<mobile>13817070313</mobile>\n");
        sb.append("<address>shanghai3</address>\n");
        sb.append("</Address>\n");
        sb.append("<Address>\n");
        sb.append("<name>yuan jiang4</name>\n");
        sb.append("<mobile>13817070314</mobile>\n");
        sb.append("<address>shanghai4</address>\n");
        sb.append("</Address>\n");
        sb.append("<Address>\n");
        sb.append("<name>yuan jiang5</name>\n");
        sb.append("<mobile>13817070315</mobile>\n");
        sb.append("<address>shanghai5</address>\n");
        sb.append("</Address>\n");
        sb.append("<Address>\n");
        sb.append("<name>yuan jiang6</name>\n");
        sb.append("<mobile>13817070316</mobile>\n");
        sb.append("<address>shanghai6</address>\n");
        sb.append("</Address>\n");
        sb.append("<Address>\n");
        sb.append("<name>yuan jiang7</name>\n");
        sb.append("<mobile>13817070317</mobile>\n");
        sb.append("<address>shanghai7</address>\n");
        sb.append("</Address>\n");
        sb.append("<Address>\n");
        sb.append("<name>yuan jiang8</name>\n");
        sb.append("<mobile>13817070318</mobile>\n");
        sb.append("<address>shanghai8</address>\n");
        sb.append("</Address>\n");
        sb.append("<Address>\n");
        sb.append("<name>yuan jiang9</name>\n");
        sb.append("<mobile>13817070319</mobile>\n");
        sb.append("<address>shanghai9</address>\n");
        sb.append("</Address>\n");
        sb.append("</AddressList>\n");
        String xml = sb.toString();
        try {
            bos.write(xml.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testAdd() {
        init();
        Address expected = new Address("testAdd", "123456789123", "testAdd");
        container.add("testAdd", "123456789123", "testAdd");
        Address actual = container.getAddressList().get(10);
        assertEquals(expected, actual);
    }

    /**
     * searchByName case 1 address list is empty.
     */
    @Test
    public void testSearchByName1() {
        String regex = ".*";
        int expected = 0;
        int actual = container.searchByName(regex).size();
        assertEquals(expected, actual);
    }

    /**
     * searchByName case 2 search by regex
     */
    @Test
    public void testSearchByName2() {
        init();
        String regex = ".*";
        int expected = 10;
        int actual = container.searchByName(regex).size();
        assertEquals(expected, actual);
    }

    /**
     * searchByName case 3 search entry by no-regex and just have one matched in
     * address entries.
     */
    @Test
    public void testSearchByName3() {
        init();
        String regex = "yuan jiang3";
        int expected = 1;
        int actual = container.searchByName(regex).size();
        assertEquals(expected, actual);
    }

    /**
     * searchByName case 4 search entry by no-regex and have more than one
     * matched in address entries.
     */
    @Test
    public void testSearchByName4() {
        init();
        container.getAddressList().add(
                new Address("yuan jiang3", "13817070310", "shanghai"));
        String regex = "yuan jiang3";
        int expected = 2;
        int actual = container.searchByName(regex).size();
        assertEquals(expected, actual);
    }

    /**
     * searchByName case 5 search the entries which do not exist
     */
    @Test
    public void testSearchByName5() {
        init();
        String regex = "yuan";
        int expected = 0;
        int actual = container.searchByName(regex).size();
        assertEquals(expected, actual);
    }

    /**
     * searchByName case 6 search by invalid regex
     */
    @Test
    public void testSearchByName6() {
        init();
        String regex = "*";
        List<Address> expected = null;
        List<Address> actual = container.searchByName(regex);
        assertEquals(expected, actual);
    }

    /**
     * searchByMobile case 1 address list is empty.
     */
    @Test
    public void testSearchByMobile1() {
        String regex = ".*";
        int expected = 0;
        int actual = container.searchByMobile(regex).size();
        assertEquals(expected, actual);
    }

    /**
     * searchByMobile case 2 search by regex
     */
    @Test
    public void testSearchByMobile2() {
        init();
        String regex = ".*";
        int expected = 10;
        int actual = container.searchByMobile(regex).size();
        assertEquals(expected, actual);
    }

    /**
     * searchByMobile case 3 search entry by no-regex and just have one matched
     * in address entries.
     */
    @Test
    public void testSearchByMobile3() {
        init();
        String regex = "13817070313";
        int expected = 1;
        int actual = container.searchByMobile(regex).size();
        assertEquals(expected, actual);
    }

    /**
     * searchByMobile case 4 search entry by no-regex and have more than one
     * matched in address entries.
     */
    @Test
    public void testSearchByMobile4() {
        init();
        container.getAddressList().add(
                new Address("yuan jiang", "13817070313", "shanghai"));
        String regex = "13817070313";
        int expected = 2;
        int actual = container.searchByMobile(regex).size();
        assertEquals(expected, actual);
    }

    /**
     * searchByMobile case 5 search entries which do not exist
     */
    @Test
    public void testSearchByMobile5() {
        init();
        String regex = "10086100861";
        int expected = 0;
        int actual = container.searchByMobile(regex).size();
        assertEquals(expected, actual);
    }

    /**
     * searchByMobile case 6 search by invaild regex
     */
    @Test
    public void testSearchByMobile6() {
        init();
        String regex = "*";
        List<Address> expected = null;
        List<Address> actual = container.searchByMobile(regex);
        assertEquals(expected, actual);
    }

    /**
     * searchByAddress case 1 address list is empty.
     */
    @Test
    public void testSearchByAddress1() {
        String regex = ".*";
        int expected = 0;
        int actual = container.searchByAddress(regex).size();
        assertEquals(expected, actual);
    }

    /**
     * searchByAddress case 2 search by regex
     */
    @Test
    public void testSearchByAddress2() {
        init();
        String regex = ".*";
        int expected = 10;
        int actual = container.searchByAddress(regex).size();
        assertEquals(expected, actual);
    }

    /**
     * searchByAddress case 3 search entry by no-regex and just have one matched
     * in address entries.
     */
    @Test
    public void testSearchByAddress3() {
        init();
        String regex = "shanghai3";
        int expected = 1;
        int actual = container.searchByAddress(regex).size();
        assertEquals(expected, actual);
    }

    /**
     * searchByAddress case 4 search entry by no-regex and have more than one
     * matched in address entries.
     */
    @Test
    public void testSearchByAddress4() {
        init();
        container.getAddressList().add(
                new Address("yuan jiang", "23817070310", "shanghai3"));
        String regex = "shanghai3";
        int expected = 2;
        int actual = container.searchByAddress(regex).size();
        assertEquals(expected, actual);
    }

    /**
     * searchByAddress case 5 search entries which do not exist.
     */
    @Test
    public void testSearchByAddress5() {
        init();
        String regex = "taiwan";
        int expected = 0;
        int actual = container.searchByAddress(regex).size();
        assertEquals(expected, actual);
    }

    /**
     * searchByAddress case 6 search by invaild regex
     */
    @Test
    public void testSearchByAddress6() {
        init();
        String regex = "*";
        List<Address> expected = null;
        List<Address> actual = container.searchByAddress(regex);
        assertEquals(expected, actual);
    }

    /**
     * deleteByName case 1 delete by no-regex
     */
    @Test
    public void testDeleteByName1() {
        init();
        String regex = "yuan jiang4";
        int expected = 1;
        int actual = container.deleteByName(regex);
        assertEquals(expected, actual);
    }

    /**
     * deleteByName case 2 delete more than one entries
     */
    @Test
    public void testDeleteByName2() {
        init();
        String regex = ".*";
        int expected = 10;
        int actual = container.deleteByName(regex);
        assertEquals(expected, actual);
    }

    /**
     * deleteByName case 3 address entries's count is 0
     */
    @Test
    public void testDeleteByName3() {
        String regex = ".*";
        int expected = 0;
        int actual = container.deleteByName(regex);
        assertEquals(expected, actual);
    }

    /**
     * deleteByName case 4 delete entries which do not exist
     */
    @Test
    public void testDeleteByName4() {
        String regex = "aaa";
        int expected = 0;
        int actual = container.deleteByName(regex);
        assertEquals(expected, actual);
    }

    /**
     * deleteByName case 5 delete by invaild regex
     */
    @Test
    public void testDeleteByName5() {
        String regex = "*";
        int expected = -1;
        int actual = container.deleteByName(regex);
        assertEquals(expected, actual);
    }

    /**
     * deleteByMobile case 1 delete by no-regex
     */
    @Test
    public void testDeleteByMobile1() {
        init();
        String regex = "13817070313";
        int expected = 1;
        int actual = container.deleteByMobile(regex);
        assertEquals(expected, actual);
    }

    /**
     * deleteByMobile case 2 delete more than one entries
     */
    @Test
    public void testDeleteByMobile2() {
        init();
        String regex = ".*";
        int expected = 10;
        int actual = container.deleteByMobile(regex);
        assertEquals(expected, actual);
    }

    /**
     * deleteByMobile case 3 address entries's count is 0
     */
    @Test
    public void testDeleteByMobile3() {
        String regex = ".*";
        int expected = 0;
        int actual = container.deleteByMobile(regex);
        assertEquals(expected, actual);
    }

    /**
     * deleteByMobile case 4 delete entries which do not exist
     */
    @Test
    public void testDeleteByMobile4() {
        init();
        String regex = "10086100861";
        int expected = 0;
        int actual = container.deleteByMobile(regex);
        assertEquals(expected, actual);
    }

    /**
     * deleteByMobile case 5 delete by invaild regex
     */
    @Test
    public void testDeleteByMobile5() {
        String regex = "*";
        int expected = -1;
        int actual = container.deleteByMobile(regex);
        assertEquals(expected, actual);
    }

    /**
     * deleteByAddress case 1 delete by no-regex
     */
    @Test
    public void testDeleteByAddress1() {
        init();
        String regex = "shanghai1";
        int expected = 1;
        int actual = container.deleteByAddress(regex);
        assertEquals(expected, actual);
    }

    /**
     * deleteByAddress case 2 delete more than one entries
     */
    @Test
    public void testDeleteByAddress2() {
        init();
        String regex = ".*";
        int expected = 10;
        int actual = container.deleteByAddress(regex);
        assertEquals(expected, actual);
    }

    /**
     * deleteByAddress case 3 address entries's count is 0
     */
    @Test
    public void testDeleteByAddress3() {
        String regex = ".*";
        int expected = 0;
        int actual = container.deleteByAddress(regex);
        assertEquals(expected, actual);
    }

    /**
     * deleteByAddress case 4 delete entries which do not exist
     */
    @Test
    public void testDeleteByAddress4() {
        init();
        String regex = "aaa";
        int expected = 0;
        int actual = container.deleteByAddress(regex);
        assertEquals(expected, actual);
    }

    /**
     * deleteByAddress case 5 delete by invaild regex
     */
    @Test
    public void testDeleteByAddress5() {
        String regex = "*";
        int expected = -1;
        int actual = container.deleteByAddress(regex);
        assertEquals(expected, actual);
    }

    @Test
    public void testLoadAndExport() {
        init();
        List<Address> before = new LinkedList<Address>(container
                .getAddressList());
        container.export();
        try {
            container.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Address> after = new LinkedList<Address>(container
                .getAddressList());

        assertEquals(before.size(), after.size());

        int expected = 10;
        int actual = 0;
        for (int i = 0; i < before.size(); i++) {
            if (before.get(i).equals(after.get(i))) {
                actual++;
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testReadXML() {
        initFile();
        File file = new File("Address.xml");
        try {
            Method method = AddressContainer.class.getDeclaredMethod("readXML",
                    new Class[] { File.class });
            method.setAccessible(true);
            method.invoke(container, new Object[] { file });
        } catch (Exception ex) {
            System.err.println("Error in testReadXML : ");
            ex.printStackTrace();
        }

        List<Address> expectedList = new ArrayList<Address>();
        for (int i = 0; i < 10; i++) {
            expectedList.add(new Address("yuan jiang" + i, "1381707031" + i,
                    "shanghai" + i));
        }

        List<Address> actualList = container.getAddressList();
        int expected = 10;
        int actual = 0;
        for (int i = 0; i < expectedList.size(); i++) {
            if (expectedList.get(i).equals(actualList.get(i))) {
                actual++;
            }
        }
        assertEquals(expected, actual);

    }


    @Test
    public void testExport() {
        init();
        container.export();
        File file = new File("Address.xml");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String expected = getXML();
        String actual = "";
        while (scanner.hasNext()) {
            actual += scanner.nextLine()+"\n";
        }
        scanner.close();
        assertEquals(expected, actual);
    }

    public String getXML() {
        StringBuffer sb = new StringBuffer();
        sb
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n");
        sb.append("<AddressList>\n");
        sb.append("<Address>\n");
        sb.append("<name>yuan jiang0</name>\n");
        sb.append("<mobile>13817070310</mobile>\n");
        sb.append("<address>shanghai0</address>\n");
        sb.append("</Address>\n");
        sb.append("<Address>\n");
        sb.append("<name>yuan jiang1</name>\n");
        sb.append("<mobile>13817070311</mobile>\n");
        sb.append("<address>shanghai1</address>\n");
        sb.append("</Address>\n");
        sb.append("<Address>\n");
        sb.append("<name>yuan jiang2</name>\n");
        sb.append("<mobile>13817070312</mobile>\n");
        sb.append("<address>shanghai2</address>\n");
        sb.append("</Address>\n");
        sb.append("<Address>\n");
        sb.append("<name>yuan jiang3</name>\n");
        sb.append("<mobile>13817070313</mobile>\n");
        sb.append("<address>shanghai3</address>\n");
        sb.append("</Address>\n");
        sb.append("<Address>\n");
        sb.append("<name>yuan jiang4</name>\n");
        sb.append("<mobile>13817070314</mobile>\n");
        sb.append("<address>shanghai4</address>\n");
        sb.append("</Address>\n");
        sb.append("<Address>\n");
        sb.append("<name>yuan jiang5</name>\n");
        sb.append("<mobile>13817070315</mobile>\n");
        sb.append("<address>shanghai5</address>\n");
        sb.append("</Address>\n");
        sb.append("<Address>\n");
        sb.append("<name>yuan jiang6</name>\n");
        sb.append("<mobile>13817070316</mobile>\n");
        sb.append("<address>shanghai6</address>\n");
        sb.append("</Address>\n");
        sb.append("<Address>\n");
        sb.append("<name>yuan jiang7</name>\n");
        sb.append("<mobile>13817070317</mobile>\n");
        sb.append("<address>shanghai7</address>\n");
        sb.append("</Address>\n");
        sb.append("<Address>\n");
        sb.append("<name>yuan jiang8</name>\n");
        sb.append("<mobile>13817070318</mobile>\n");
        sb.append("<address>shanghai8</address>\n");
        sb.append("</Address>\n");
        sb.append("<Address>\n");
        sb.append("<name>yuan jiang9</name>\n");
        sb.append("<mobile>13817070319</mobile>\n");
        sb.append("<address>shanghai9</address>\n");
        sb.append("</Address>\n");
        sb.append("</AddressList>\n");
        return sb.toString();
    }
}
