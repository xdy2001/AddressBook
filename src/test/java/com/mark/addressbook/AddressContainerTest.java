package com.mark.addressbook;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mark.addressbook.Address;
import com.mark.addressbook.AddressContainer;

public class AddressContainerTest {

    private AddressContainer container = null;

    @Before
    public void setUp() throws Exception {
        container = new AddressContainer();
        List<Address> list = container.getAddressList();
        list.clear();
        for (int i = 0; i < 10; i++) {
            list.add(new Address("yuan jiang" + i, "1381707031" + i, "shanghai"
                    + i));
        }
    }

    @After
    public void tearDown() throws Exception {
        container.getAddressList().clear();
    }

    @Test
    public void testAdd() {
        Address expected = new Address("testAdd", "123456789123", "testAdd");
        container.add("testAdd", "123456789123", "testAdd");
        Address actual = container.getAddressList().get(10);
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchByNameWithRegex1() {
        String regex = ".*";
        int expected = 10;
        int actual = container.searchByName(regex).size();
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchByNameWithRegex2() {
        String regex = ".*1$";
        int expected = 1;
        int actual = container.searchByName(regex).size();
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchByNameWithString() {
        String regex = "yuan jiang3";
        int expected = 1;
        int actual = container.searchByName(regex).size();
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchByMobileWithRegex1() {
        String regex = ".*";
        int expected = 10;
        int actual = container.searchByMobile(regex).size();
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchByMobileWithRegex2() {
        String regex = ".*1$";
        int expected = 1;
        int actual = container.searchByMobile(regex).size();
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchByMobileWithString() {
        String regex = "13817070313";
        int expected = 1;
        int actual = container.searchByMobile(regex).size();
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchByAddressWithRegex1() {
        String regex = ".*";
        int expected = 10;
        int actual = container.searchByAddress(regex).size();
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchByAddressWithRegex2() {
        String regex = ".*1$";
        int expected = 1;
        int actual = container.searchByAddress(regex).size();
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchByAddressWithString() {
        String regex = "shanghai4";
        int expected = 1;
        int actual = container.searchByAddress(regex).size();
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteByName() {
        String regex = "yuan jiang4";
        int expected = 9;
        container.deleteByName(regex);
        int actual = container.getAddressList().size();
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteByMobile() {
        String regex = "13817070313";
        int expected = 9;
        container.deleteByMobile(regex);
        int actual = container.getAddressList().size();
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteByAddress() {
        String regex = "shanghai4";
        int expected = 9;
        container.deleteByAddress(regex);
        int actual = container.getAddressList().size();
        assertEquals(expected, actual);
    }

    @Test
    public void testLoadAndExport() {
        List<Address> before = new LinkedList<Address>(container
                .getAddressList());
        try {
            container.export();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
