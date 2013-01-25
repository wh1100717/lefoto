/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.filter;

/**
 *
 * @author Eric
 */
public class InputValidityFilter {

    public static boolean integerFilter(String input) {
        boolean result = false;
        try {
            Integer.parseInt(input);
            result = true;
        } catch (Exception e) {
        }
        return result;
    }
}
