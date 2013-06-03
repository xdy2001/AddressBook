/**
 * @author luxr123
 * @email luxr123@sjtu.edu.cn
 */
package cn.edu.sjtu.ab;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class XmlUtilsTest {
	List<AddressBookInfo> list = null;

	@Test
	public void testPrintSearchResult() {

		list = new ArrayList<AddressBookInfo>();
		for (int i = 0; i < 10; i++) {
			list.add(new AddressBookInfo("LuXiaorui" + i, "1500018293" + i, "SJTU" + i));
		}
		XmlUtils.printSearchResult(list);
	}
}
