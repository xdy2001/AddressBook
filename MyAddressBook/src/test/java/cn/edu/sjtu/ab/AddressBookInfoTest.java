/**
 * @author luxr123
 * @email luxr123@sjtu.edu.cn
 */
package cn.edu.sjtu.ab;

import org.junit.Assert;
import org.junit.Test;

public class AddressBookInfoTest {

	AddressBookInfo info;

	@Test
	public void testGet() {
		info = new AddressBookInfo("luxiaorui", "15000182934", "SJTU");
		Assert.assertEquals("luxiaorui", info.getName());
		Assert.assertEquals("15000182934", info.getMobile());
		Assert.assertEquals("SJTU", info.getAddress());
	}

	@Test
	public void testSet() {
		info = new AddressBookInfo();
		info.setName("LuXiaoRui");
		info.setMobile("15000182934");
		info.setAddress("SJTU");
		Assert.assertEquals("LuXiaoRui", info.getName());
		Assert.assertEquals("15000182934", info.getMobile());
		Assert.assertEquals("SJTU", info.getAddress());
	}

	@Test
	public void testEquals() {
		AddressBookInfo info = new AddressBookInfo("luxiaorui", "15000182934", "SJTU");
		Assert.assertEquals(true, info.equals(info));

		AddressBookInfo info1 = new AddressBookInfo();
		AddressBookInfo info2 = null;
		StringBuilder builder = new StringBuilder();
		Assert.assertEquals(false, info.equals(info2));
		Assert.assertEquals(false, info.equals(builder));
		
		AddressBookInfo info3 = new AddressBookInfo("luxiaorui2", "15000182934", "SJTU");
		AddressBookInfo info4 = new AddressBookInfo("luxiaorui", "15000182935", "SJTU");
		AddressBookInfo info5 = new AddressBookInfo("luxiaorui", "15000182935", "SJTU2");
		
		Assert.assertEquals(false, info3.equals(info4));
		Assert.assertEquals(false, info.equals(info4));
		Assert.assertEquals(false, info.equals(info5));
		info1.setName("luxiaorui");
		Assert.assertEquals(false, info1.equals(info5));
		info1.setMobile("15000182935");
		Assert.assertEquals(false, info1.equals(info5));
		info1.setAddress("SJTU");
		Assert.assertEquals(false, info1.equals(info5));
	}

	@Test
	public void testHashCode() {
		AddressBookInfo info1 = new AddressBookInfo("luxiaorui", "15000182934", "SJTU");
		AddressBookInfo info2 = new AddressBookInfo("luxiaorui", "15000182934", "SJTU");
		Assert.assertEquals(true,info1.hashCode()==info2.hashCode());
		
		AddressBookInfo info3 = new AddressBookInfo("luxiaorui2", "15000182934", "SJTU");
		Assert.assertEquals(false,info1.hashCode()==info3.hashCode());
		
		AddressBookInfo info4 = new AddressBookInfo();
		AddressBookInfo info5 = new AddressBookInfo();
		Assert.assertEquals(false,info3.hashCode()==info4.hashCode());
		Assert.assertEquals(false,info1.hashCode()==info5.hashCode());
	}
}
