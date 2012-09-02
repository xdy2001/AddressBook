/**
 * 
 */
package com.mark.addressbook;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Mark_jiang
 * 
 */
public class AddressTest {

   @Test
   public void testGet(){
       Address address = new Address("Yuan Jiang","13817070313","Shanghai"); 
       assertEquals("Yuan Jiang",address.getName());
       assertEquals("13817070313",address.getMobile());
       assertEquals("Shanghai",address.getAddress());
   }
   
   @Test
   public void testSet(){
       Address address = new Address(); 
       address.setName("Yuan Jiang");
       address.setMobile("13817070313");
       address.setAddress("Shanghai");
       assertEquals("Yuan Jiang",address.getName());
       assertEquals("13817070313",address.getMobile());
       assertEquals("Shanghai",address.getAddress());
   }
   
   @Test
   public void testEquals(){
       Address address = new Address("Yuan Jiang","13817070313","Shanghai");
       assertEquals(true,address.equals(address));
       
       Address addressNull1 = new Address();
       Address addressNull2 = new Address();
       assertEquals(true,addressNull1.equals(addressNull2));
       assertEquals(false,addressNull1.equals(address));
   }
}
