package com.solstice.cloud.messaging.aws;

import java.util.LinkedHashMap;
import java.util.Map;

import com.solstice.command.GenericCommandType;

/**
 * Created by alawlor on 1/30/2017.
 */
public class CommandQueues {
    private static final String categoryQQueue = "Category_Command_Queue";
    private static final String productQQueue = "Product_Command_Queue";
    private static final String shippingQQueue = "Shipping_Command_Queue";
    private static final String spuQQueue = 	 "SPU_Command_Queue";
    private static final String deliveryQQueue = "Delivery_Command_Queue";
    private static final String atcQQueue = "ATC_Command_Queue";
    private static final String checkoutQQueue = "Checkout_Command_Queue";
    private static final String paymentQQueue = "Payment_Command_Queue";
    private static final String accountQQueue = "Account_Command_Queue";
    private static final String addressQQueue = "Address_Command_Queue";
  
    private static final String debugQueue = "Debug_Queue";
    private static final String todoQueue = "TODO_Queue";



    public static  Map<String, String> CommandQueuesMap;
    static {
        CommandQueuesMap = new LinkedHashMap<String, String>();
        
        CommandQueuesMap.put(GenericCommandType.CREATE_CATEGORY.toString(), checkoutQQueue);
        CommandQueuesMap.put(GenericCommandType.UPDATE_CATEGORY.toString(), checkoutQQueue);
        
        
        CommandQueuesMap.put(GenericCommandType.CREATE_PRODUCT.toString(), checkoutQQueue);
        CommandQueuesMap.put(GenericCommandType.UPDATE_PRODUCT.toString(), checkoutQQueue);
        
        CommandQueuesMap.put(GenericCommandType.CREATE_SHIPPING.toString(), checkoutQQueue);
        CommandQueuesMap.put(GenericCommandType.UPDATE_SHIPPING.toString(), checkoutQQueue);
        
        CommandQueuesMap.put(GenericCommandType.CREATE_SPU.toString(), checkoutQQueue);
        CommandQueuesMap.put(GenericCommandType.UPDATE_SPU.toString(), checkoutQQueue);
        
        CommandQueuesMap.put(GenericCommandType.CREATE_DELIVERY.toString(), checkoutQQueue);
        CommandQueuesMap.put(GenericCommandType.UPDATE_DELIVERY.toString(), checkoutQQueue);
        
        CommandQueuesMap.put(GenericCommandType.ADD_TO_CART.toString(), checkoutQQueue);
        CommandQueuesMap.put(GenericCommandType.UPDATE_CART.toString(), checkoutQQueue);
        
        CommandQueuesMap.put(GenericCommandType.CREATE_CHECKOUT.toString(), checkoutQQueue);
        CommandQueuesMap.put(GenericCommandType.UPDATE_CHECKOUT.toString(), checkoutQQueue);
        
        CommandQueuesMap.put(GenericCommandType.CREATE_PAYMENT.toString(), checkoutQQueue);
        CommandQueuesMap.put(GenericCommandType.UPDATE_PAYMENT.toString(), checkoutQQueue);
        
        CommandQueuesMap.put(GenericCommandType.CREATE_ACCOUNT.toString(), checkoutQQueue);
        CommandQueuesMap.put(GenericCommandType.UPDATE_ACCOUNT.toString(), checkoutQQueue);
        
        CommandQueuesMap.put(GenericCommandType.CREATE_ADDRESS.toString(), checkoutQQueue);
        CommandQueuesMap.put(GenericCommandType.UPDATE_ADDRESS.toString(), checkoutQQueue);

		CommandQueuesMap.put(GenericCommandType.CREATE_TODO.toString(), todoQueue);
        CommandQueuesMap.put(GenericCommandType.UPDATE_TODO.toString(), todoQueue);

     
        CommandQueuesMap.put(GenericCommandType.DEBUG.toString(), debugQueue);
    }
}
