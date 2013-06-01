/**
 * @author luxr123
 * @email luxr123@sjtu.edu.cn
 */
package cn.edu.sjtu.ab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddressBookDAOTest {

	private AddressBookDAO dao = null;

	@Before
	public void setUp() throws Exception {
		File file = new File("ab_info.xml");
		if (file.exists()) {
			file.delete();
		}
		dao = new AddressBookDAO();
		List<AddressBookInfo> list = dao.getInfoList();
		list.clear();
	}

	@After
	public void tearDown() throws Exception {
		dao.getInfoList().clear();
		File file = new File("ab_info.xml");
		if (file.exists()) {
			file.delete();
		}
	}

	public void init() {
		List<AddressBookInfo> list = dao.getInfoList();
		for (int i = 0; i < 10; i++) {
			list.add(new AddressBookInfo("LuXiaorui" + i, "1500018293" + i, "SJTU" + i));
		}
	}

	@Test
	public void testAdd() {
		init();
		AddressBookInfo expected = new AddressBookInfo("testAddname", "123456789000", "testAddaddress");
		dao.add("testAddname", "123456789000", "testAddaddress");
		AddressBookInfo actual = dao.getInfoList().get(10);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * searchByName case 1 address list is empty.
	 */
	@Test
	public void testSearchByName1() {
		String regex = ".*";
		int expected = 0;
		int actual = dao.searchByType("name", regex).size();
		Assert.assertEquals(expected, actual);
	}

	/**
	 * searchByName case 2 search by regex
	 */
	@Test
	public void testSearchByName2() {
		init();
		String regex = ".*";
		int expected = 10;
		int actual = dao.searchByType("name", regex).size();
		Assert.assertEquals(expected, actual);
	}

	/**
	 * searchByName case 3 search entry by no-regex and just have one matched in address entries.
	 */
	@Test
	public void testSearchByName3() {
		init();
		String regex = "LuXiaorui5";
		int expected = 1;
		int actual = dao.searchByType("name", regex).size();
		Assert.assertEquals(expected, actual);
	}

	/**
	 * searchByName case 4 search entry by no-regex and have more than one matched in address entries.
	 */
	@Test
	public void testSearchByName4() {
		init();
		dao.getInfoList().add(new AddressBookInfo("LuXiaorui5", "15000182934", "SJTU"));
		String regex = "LuXiaorui5";
		int expected = 2;
		int actual = dao.searchByType("name", regex).size();
		Assert.assertEquals(expected, actual);
	}

	/**
	 * searchByName case 5 search the entries which do not exist
	 */
	@Test
	public void testSearchByName5() {
		init();
		String regex = "lu";
		int expected = 0;
		int actual = dao.searchByType("name", regex).size();
		Assert.assertEquals(expected, actual);
	}

	/**
	 * searchByName case 6 search by invalid regex
	 */
	@Test
	public void testSearchByName6() {
		init();
		String regex = "*";
		List<AddressBookInfo> expected = null;
		List<AddressBookInfo> actual = dao.searchByType("name", regex);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * searchByMobile case 1 address list is empty.
	 */
	@Test
	public void testSearchByMobile1() {
		String regex = ".*";
		int expected = 0;
		int actual = dao.searchByType("name", regex).size();
		Assert.assertEquals(expected, actual);
	}

	/**
	 * searchByMobile case 2 search by regex
	 */
	@Test
	public void testSearchByMobile2() {
		init();
		String regex = ".*";
		int expected = 10;
		int actual = dao.searchByType("mobile", regex).size();
		Assert.assertEquals(expected, actual);
	}

	/**
	 * searchByMobile case 3 search entry by no-regex and just have one matched in address entries.
	 */
	@Test
	public void testSearchByMobile3() {
		init();
		String regex = "13817070313";
		int expected = 0;
		int actual = dao.searchByType("mobile", regex).size();
		Assert.assertEquals(expected, actual);
	}

	/**
	 * searchByMobile case 4 search entry by no-regex and have more than one matched in address entries.
	 */
	@Test
	public void testSearchByMobile4() {
		init();
		dao.getInfoList().add(new AddressBookInfo("LuXiaoRui", "15000182930", "SJTU"));
		String regex = "15000182930";
		int expected = 2;
		int actual = dao.searchByType("mobile", regex).size();
		Assert.assertEquals(expected, actual);
	}

	/**
	 * searchByMobile case 5 search entries which do not exist
	 */
	@Test
	public void testSearchByMobile5() {
		init();
		String regex = "12345678901";
		int expected = 0;
		int actual = dao.searchByType("mobile", regex).size();
		Assert.assertEquals(expected, actual);
	}

	/**
	 * searchByMobile case 6 search by invaild regex
	 */
	@Test
	public void testSearchByMobile6() {
		init();
		String regex = "*";
		List<AddressBookInfo> expected = null;
		List<AddressBookInfo> actual = dao.searchByType("mobile", regex);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * searchByAddress case 1 address list is empty.
	 */
	@Test
	public void testSearchByAddress1() {
		String regex = ".*";
		int expected = 0;
		int actual = dao.searchByType("address", regex).size();
		Assert.assertEquals(expected, actual);
	}

	/**
	 * searchByAddress case 2 search by regex
	 */
	@Test
	public void testSearchByAddress2() {
		init();
		String regex = ".*";
		int expected = 10;
		int actual = dao.searchByType("address", regex).size();
		Assert.assertEquals(expected, actual);
	}

	/**
	 * searchByAddress case 3 search entry by no-regex and just have one matched in address entries.
	 */
	@Test
	public void testSearchByAddress3() {
		init();
		String regex = "SJTU5";
		int expected = 1;
		int actual = dao.searchByType("address", regex).size();
		Assert.assertEquals(expected, actual);
	}

	/**
	 * searchByAddress case 4 search entry by no-regex and have more than one matched in address entries.
	 */
	@Test
	public void testSearchByAddress4() {
		init();
		dao.getInfoList().add(new AddressBookInfo("LuXiaorui", "12345678900", "SJTU3"));
		String regex = "SJTU3";
		int expected = 2;
		int actual = dao.searchByType("address", regex).size();
		Assert.assertEquals(expected, actual);
	}

	/**
	 * searchByAddress case 5 search entries which do not exist.
	 */
	@Test
	public void testSearchByAddress5() {
		init();
		String regex = "edu.sjtu";
		int expected = 0;
		int actual = dao.searchByType("address", regex).size();
		Assert.assertEquals(expected, actual);
	}

	/**
	 * searchByAddress case 6 search by invaild regex
	 */
	@Test
	public void testSearchByAddress6() {
		init();
		String regex = "*";
		List<AddressBookInfo> expected = null;
		List<AddressBookInfo> actual = dao.searchByType("address", regex);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * deleteByName case 1 delete by no-regex
	 */
	@Test
	public void testDeleteByName1() {
		init();
		String regex = "LuXiaorui6";
		int expected = 1;
		int actual = dao.deleteByType("NAME", regex);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * deleteByName case 2 delete more than one entries
	 */
	@Test
	public void testDeleteByName2() {
		init();
		String regex = ".*";
		int expected = 10;
		int actual = dao.deleteByType("name", regex);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * deleteByName case 3 address entries's count is 0
	 */
	@Test
	public void testDeleteByName3() {
		String regex = ".*";
		int expected = 0;
		int actual = dao.deleteByType("name", regex);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * deleteByName case 4 delete entries which do not exist
	 */
	@Test
	public void testDeleteByName4() {
		String regex = "edu.sjtu";
		int expected = 0;
		int actual = dao.deleteByType("name", regex);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * deleteByName case 5 delete by invaild regex
	 */
	@Test
	public void testDeleteByName5() {
		String regex = "*";
		int expected = -1;
		int actual = dao.deleteByType("name", regex);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * deleteByMobile case 1 delete by no-regex
	 */
	@Test
	public void testDeleteByMobile1() {
		init();
		String regex = "15000188890";
		int expected = 0;
		int actual = dao.deleteByType("mobile", regex);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * deleteByMobile case 2 delete more than one entries
	 */
	@Test
	public void testDeleteByMobile2() {
		init();
		String regex = ".*";
		int expected = 10;
		int actual = dao.deleteByType("mobile", regex);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * deleteByMobile case 3 address entries's count is 0
	 */
	@Test
	public void testDeleteByMobile3() {
		String regex = ".*";
		int expected = 0;
		int actual = dao.deleteByType("mobile", regex);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * deleteByMobile case 4 delete entries which do not exist
	 */
	@Test
	public void testDeleteByMobile4() {
		init();
		String regex = "10086100861";
		int expected = 0;
		int actual = dao.deleteByType("mobile", regex);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * deleteByMobile case 5 delete by invaild regex
	 */
	@Test
	public void testDeleteByMobile5() {
		String regex = "*";
		int expected = -1;
		int actual = dao.deleteByType("mobile", regex);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * deleteByAddress case 1 delete by no-regex
	 */
	@Test
	public void testDeleteByAddress1() {
		init();
		String regex = "SJTU3";
		int expected = 1;
		int actual = dao.deleteByType("address", regex);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * deleteByAddress case 2 delete more than one entries
	 */
	@Test
	public void testDeleteByAddress2() {
		init();
		String regex = ".*";
		int expected = 10;
		int actual = dao.deleteByType("address", regex);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * deleteByAddress case 3 address entries's count is 0
	 */
	@Test
	public void testDeleteByAddress3() {
		String regex = ".*";
		int expected = 0;
		int actual = dao.deleteByType("address", regex);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * deleteByAddress case 4 delete entries which do not exist
	 */
	@Test
	public void testDeleteByAddress4() {
		init();
		String regex = "sssss";
		int expected = 0;
		int actual = dao.deleteByType("address", regex);
		Assert.assertEquals(expected, actual);
	}

	/**
	 * deleteByAddress case 5 delete by invaild regex
	 */
	@Test
	public void testDeleteByAddress5() {
		String regex = "*";
		int expected = -1;
		int actual = dao.deleteByType("address", regex);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testInitAndExport() {
		init();
		List<AddressBookInfo> before = new LinkedList<AddressBookInfo>(dao.getInfoList());
		dao.export2XML();
		try {
			dao.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<AddressBookInfo> after = new LinkedList<AddressBookInfo>(dao.getInfoList());

		Assert.assertEquals(before.size(), after.size());

		int expected = 10;
		int actual = 0;
		for (int i = 0; i < before.size(); i++) {
			if (before.get(i).equals(after.get(i))) {
				actual++;
			}
		}
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testExport2XML() {
		dao.export2XML();
		File file = new File("ab_info.xml");
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String expected = getXML();
		String actual = "";
		while (scanner.hasNext()) {
			actual += scanner.nextLine() + "\n";
		}
		scanner.close();
		Assert.assertEquals(expected, actual);
	}

	public String getXML() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n");
		sb.append("<abinfos/>\n");
		return sb.toString();
	}
}
