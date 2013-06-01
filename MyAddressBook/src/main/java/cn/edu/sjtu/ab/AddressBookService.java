/**
 * @author luxr123
 * @email luxr123@sjtu.edu.cn
 */
package cn.edu.sjtu.ab;
/**
 * ab Service
 */
import java.util.Scanner;

public class AddressBookService implements Runnable {

	private static final String ab = "ab>";
	private Scanner scanner = new Scanner(System.in);
	private String command = null;
	private AddressBookDAO abDao = new AddressBookDAO();

	public void run() {
		while (true) {
			System.out.print(ab);
			command = scanner.nextLine().trim();
			if ("!help".equalsIgnoreCase(command)) {
				XmlUtils.showCommands();
			} else if ("search".equalsIgnoreCase(command)) {
				while (true) {
					System.out.print("search by (name|mobile|address): ");
					String searchType = scanner.nextLine().trim();
					if (XmlUtils.checkSearchType(searchType)) {
						System.out.print(searchType.toLowerCase() + ": ");
						String regex = scanner.nextLine();
						abDao.searchByType(searchType, regex);
						break;
					} else {
						System.out.println("error command.");
					}
				}
			} else if ("add".equalsIgnoreCase(command)) {
				System.out.print("name: ");
				String name = scanner.nextLine();
				String mobile = null;
				while (true) {
					System.out.print("mobile: ");
					mobile = scanner.nextLine();
					if (mobile.matches("^\\d{11}$")) {
						break;
					} else {
						System.out.println("mobile should be 11 digit.");
					}
				}
				System.out.print("address: ");
				String address = scanner.nextLine();
				abDao.add(name, mobile, address);
			} else if ("delete".equalsIgnoreCase(command)) {
				while (true) {
					System.out.print("delete by (name|mobile|address): ");
					String searchType = scanner.nextLine();
					if (XmlUtils.checkSearchType(searchType)) {
						System.out.print(searchType.toLowerCase() + ": ");
						String regex = scanner.nextLine();
						int count = abDao.deleteByType(searchType, regex);
						if (count >= 0) {
							System.out.println(count + " ab entries deleted");
						}
						break;
					} else {
						System.out.println("error command.");
					}
				}
			} else if ("!quit".equalsIgnoreCase(command)) {
				abDao.export2XML();
				return;
			} else {
				System.out.println("error command, please use !help to show help.");
			}
		}
	}

}
