package com.mark.addressbook;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddressBook {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Console());
        executorService.shutdown();
    }
}
