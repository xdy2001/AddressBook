/**
 * @author luxr123
 * @email luxr123@sjtu.edu.cn
 */
package cn.edu.sjtu.ab;
/**
 * ab project UI
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddressBookUI {

	public static void main(String[] args) {

		ExecutorService execService = Executors.newCachedThreadPool();// get a threadPool
		execService.execute(new AddressBookService());// thread execute
		execService.shutdown();// Initiates an orderly shut down
	}

}
