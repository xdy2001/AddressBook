/**
 * @author luxr123
 * @email luxr123@sjtu.edu.cn
 */
package cn.edu.sjtu.ab;

import org.junit.Test;

public class AddressBookUITest {

	@Test
	public void testUI() {
		AddressBookUI addressBookUI = new AddressBookUI();
		System.out.println(addressBookUI.toString());
		AddressBookUI.main(new String[] {});
	}
}
