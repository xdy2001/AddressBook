/**
 * @author luxr123
 * @email luxr123@sjtu.edu.cn
 */
package cn.edu.sjtu.ab;

/**
 * ab basic information
 */
public class AddressBookInfo {
	private String name;
	private String mobile;
	private String address;

	public AddressBookInfo() {
		super();
	}

	public AddressBookInfo(String name, String mobile, String address) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 8;//any digit
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AddressBookInfo)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		AddressBookInfo info = (AddressBookInfo) obj;
		if (name == null) {
			if (info.name != null) {
				return false;
			}
		} else if (!name.equals(info.name)) {
			return false;
		}
		if (mobile == null) {
			if (info.mobile != null) {
				return false;
			}
		} else if (!mobile.equals(info.mobile)) {
			return false;
		}
		if (address == null) {
			if (info.address != null) {
				return false;
			}
		} else if (!address.equals(info.address)) {
			return false;
		}

		return true;
	}
}
