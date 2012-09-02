package com.mark.addressbook;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

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

    public void initXML() {
        File file = new File("Address.xml");
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Cannot create Address.xml.");
        }
    }

    /**
     * case 1 test add and mobile is 11 digits
     */
    @Test
    public void testRun1() {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        sb.append("add\n");
        sb.append("yuan jiang0\n");
        sb.append("13817070310\n");
        sb.append("shanghai0\n");
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
        Address expected = new Address("yuan jiang0", "13817070310", "shanghai0");
        assertEquals(expected, container.getAddressList().get(0));
    }

    /**
     * case 2 test add and mobile is not 11 digits
     */
    @Test
    public void testRun2() {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        sb.append("add\n");
        sb.append("yuan jiang0\n");
        sb.append("1381707031\n");
        sb.append("13817070310\n");
        sb.append("shanghai0\n");
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
        Address expected = new Address("yuan jiang0", "13817070310", "shanghai0");
        assertEquals(expected, container.getAddressList().get(0));
    }

    /**
     * case 3 test search by name
     */
    @Test
    public void testRun3() {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        sb.append("add\n");
        sb.append("yuan jiang\n");
        sb.append("13817070313\n");
        sb.append("shanghai\n");
        sb.append("search\n");
        sb.append("name\n");
        sb.append("yuan jiang\n");
        sb.append("!quit\n");
        String commands = sb.toString();
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(commands.getBytes("UTF-8"));
            System.setIn(bis);
            new Console().run();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
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

    /**
     * case 4 test search by mobile
     */
    @Test
    public void testRun4() {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        sb.append("add\n");
        sb.append("yuan jiang\n");
        sb.append("13817070313\n");
        sb.append("shanghai\n");
        sb.append("search\n");
        sb.append("mobile\n");
        sb.append("13817070313\n");
        sb.append("!quit\n");
        String commands = sb.toString();
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(commands.getBytes("UTF-8"));
            System.setIn(bis);
            new Console().run();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
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

    /**
     * case 5 test search by address
     */
    @Test
    public void testRun5() {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        sb.append("add\n");
        sb.append("yuan jiang\n");
        sb.append("13817070313\n");
        sb.append("shanghai\n");
        sb.append("search\n");
        sb.append("address\n");
        sb.append("shanghai\n");
        sb.append("!quit\n");
        String commands = sb.toString();
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(commands.getBytes("UTF-8"));
            System.setIn(bis);
            new Console().run();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
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

    /**
     * case 6 test delete by name
     */
    @Test
    public void testRun6() {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        sb.append("add\n");
        sb.append("yuan jiang\n");
        sb.append("13817070313\n");
        sb.append("shanghai\n");
        sb.append("delete\n");
        sb.append("name\n");
        sb.append("yuan jiang\n");
        sb.append("!quit\n");
        String commands = sb.toString();
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(commands.getBytes("UTF-8"));
            System.setIn(bis);
            new Console().run();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
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

    /**
     * case 7 test delete by mobile
     */
    @Test
    public void testRun7() {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        sb.append("add\n");
        sb.append("yuan jiang\n");
        sb.append("13817070313\n");
        sb.append("shanghai\n");
        sb.append("delete\n");
        sb.append("mobile\n");
        sb.append("13817070313\n");
        sb.append("!quit\n");
        String commands = sb.toString();
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(commands.getBytes("UTF-8"));
            System.setIn(bis);
            new Console().run();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
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

    /**
     * case 8 test delete by address
     */
    @Test
    public void testRun8() {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        sb.append("add\n");
        sb.append("yuan jiang\n");
        sb.append("13817070313\n");
        sb.append("shanghai\n");
        sb.append("delete\n");
        sb.append("address\n");
        sb.append("shanghai\n");
        sb.append("!quit\n");
        String commands = sb.toString();
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(commands.getBytes("UTF-8"));
            System.setIn(bis);
            new Console().run();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
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

    /**
     * case 9 test delete by name and regex is invaild
     */
    @Test
    public void testRun9() {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        sb.append("add\n");
        sb.append("yuan jiang\n");
        sb.append("13817070313\n");
        sb.append("shanghai\n");
        sb.append("delete\n");
        sb.append("name\n");
        sb.append("*\n");
        sb.append("!quit\n");
        String commands = sb.toString();
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(commands.getBytes("UTF-8"));
            System.setIn(bis);
            new Console().run();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
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

    /**
     * case 10 test delete by mobile and regex is invaild
     */
    @Test
    public void testRun10() {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        sb.append("add\n");
        sb.append("yuan jiang\n");
        sb.append("13817070313\n");
        sb.append("shanghai\n");
        sb.append("delete\n");
        sb.append("mobile\n");
        sb.append("*\n");
        sb.append("!quit\n");
        String commands = sb.toString();
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(commands.getBytes("UTF-8"));
            System.setIn(bis);
            new Console().run();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
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

    /**
     * case 11 test delete by address and regex is invaild
     */
    @Test
    public void testRun11() {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        sb.append("add\n");
        sb.append("yuan jiang\n");
        sb.append("13817070313\n");
        sb.append("shanghai\n");
        sb.append("delete\n");
        sb.append("address\n");
        sb.append("*\n");
        sb.append("!quit\n");
        String commands = sb.toString();
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(commands.getBytes("UTF-8"));
            System.setIn(bis);
            new Console().run();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
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

    /**
     * case 12 test help
     */
    @Test
    public void testRun12() {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        sb.append("!help\n");
        sb.append("!quit\n");
        String commands = sb.toString();
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(commands.getBytes("UTF-8"));
            System.setIn(bis);
            new Console().run();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
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
    
    /**
     * case 13 
     * test error command
     */
    @Test
    public void testRun13() {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        sb.append("error\n");
        sb.append("add\n");
        sb.append("error\n");
        sb.append("name\n");
        sb.append("yuan jiang\n");
        sb.append("13817070313\n");
        sb.append("shanghai\n");
        sb.append("search\n");
        sb.append("error\n");
        sb.append("name\n");
        sb.append("yuan jiang\n");
        sb.append("delete\n");
        sb.append("error\n");
        sb.append("name\n");
        sb.append("yuan jiang\n");
        sb.append("!quit\n");
        String commands = sb.toString();
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(commands.getBytes("UTF-8"));
            System.setIn(bis);
            new Console().run();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
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
    
    /**
     * case 14
     * test search by name and regex is invaild
     */
    @Test
    public void testRun14() {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        sb.append("add\n");
        sb.append("yuan jiang\n");
        sb.append("13817070313\n");
        sb.append("shanghai\n");
        sb.append("search\n");
        sb.append("name\n");
        sb.append("*\n");
        sb.append("!quit\n");
        String commands = sb.toString();
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(commands.getBytes("UTF-8"));
            System.setIn(bis);
            new Console().run();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
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

    /**
     * case 15
     *  test search by mobile and regex is invaild
     */
    @Test
    public void testRun15() {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        sb.append("add\n");
        sb.append("yuan jiang\n");
        sb.append("13817070313\n");
        sb.append("shanghai\n");
        sb.append("search\n");
        sb.append("mobile\n");
        sb.append("*\n");
        sb.append("!quit\n");
        String commands = sb.toString();
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(commands.getBytes("UTF-8"));
            System.setIn(bis);
            new Console().run();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
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

    /**
     * case 16
     *  test search by address and regex is invaild
     */
    @Test
    public void testRun16() {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        sb.append("add\n");
        sb.append("yuan jiang\n");
        sb.append("13817070313\n");
        sb.append("shanghai\n");
        sb.append("search\n");
        sb.append("address\n");
        sb.append("*\n");
        sb.append("!quit\n");
        String commands = sb.toString();
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(commands.getBytes("UTF-8"));
            System.setIn(bis);
            new Console().run();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
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
