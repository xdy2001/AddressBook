package com.mark.addressbook;

import static org.junit.Assert.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ConsoleTest {

    @Before
    public void setUp() throws Exception {
        File file = new File("Address.xml");
        if (file.exists()) {
            file.delete();
        }
    }
    
    @Test
    public void testRunCase4() {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        sb.append("!help\n");
        sb.append("add\n");
        sb.append("yuan jiang\n");
        sb.append("13817070313\n");
        sb.append("shanghai\n");
        sb.append("search\n");
        sb.append("name\n");
        sb.append("yuan.*\n");
        sb.append("!quit\n");
        String commands = sb.toString();
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(commands.getBytes("UTF-8"));
            System.setIn(bis);
            new Console().run();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.setIn(in);
        }
        AddressContainer container = new AddressContainer();
        Address expected = new Address("yuan jiang", "13817070313", "shanghai");
        assertEquals(expected,container.getAddressList().get(0));
    }

    @Test
    public void testRunCase1() {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        sb.append("!help\n");
        sb.append("add\n");
        sb.append("yuan jiang\n");
        sb.append("13817070313\n");
        sb.append("shanghai\n");
        sb.append("search\n");
        sb.append("name\n");
        sb.append("yuan.*\n");
        sb.append("delete\n");
        sb.append("name\n");
        sb.append("yuan.*\n");
        sb.append("!quit\n");
        String commands = sb.toString();
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(commands.getBytes("UTF-8"));
            System.setIn(bis);
            new Console().run();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.setIn(in);
        }
    }

    @Test
    public void testRunCase2() {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        sb.append("!help\n");
        sb.append("add\n");
        sb.append("yuan jiang\n");
        sb.append("13817070313\n");
        sb.append("shanghai\n");
        sb.append("search\n");
        sb.append("mobile\n");
        sb.append("13.*\n");
        sb.append("delete\n");
        sb.append("mobile\n");
        sb.append("13.*\n");
        sb.append("!quit\n");
        String commands = sb.toString();
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(commands.getBytes("UTF-8"));
            System.setIn(bis);
            new Console().run();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.setIn(in);
        }

    }

    @Test
    public void testRunCase3() {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        sb.append("!help\n");
        sb.append("add\n");
        sb.append("yuan jiang\n");
        sb.append("13817070313\n");
        sb.append("shanghai\n");
        sb.append("search\n");
        sb.append("address\n");
        sb.append(".*\n");
        sb.append("delete\n");
        sb.append("address\n");
        sb.append(".*\n");
        sb.append("!quit\n");
        String commands = sb.toString();
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(commands.getBytes("UTF-8"));
            System.setIn(bis);
            new Console().run();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.setIn(in);
        }
    }
    
}
