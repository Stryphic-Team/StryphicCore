package com.stryphic.stryphiccore.util;

/**
 * This is helper class for the client and the server
 */
public class CommonUtils {
    /**
     * Creates a namespaced name with global mod id
     * @param name
     * @return
     */
    public static String createUnlocalizedName(String name){
        return Reference.MODID + ":" + name;
    }

}
