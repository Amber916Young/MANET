package com.yang.myapplication.Tools;

import android.bluetooth.BluetoothDevice;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BluetoothTools {

    static public boolean pair(BluetoothDevice device){
        return createBond(device);
    }

    static public boolean unpair(BluetoothDevice device) {
        int state = device.getBondState();
        if (state == BluetoothDevice.BOND_BONDING) {
            cancelBondProcess(device);
        }
        if (state != BluetoothDevice.BOND_NONE) {
            final boolean successful = removeBond(device);
            return successful;
        }
        return false;
    }

    static public void setPin(BluetoothDevice device, String pin){
        try {
            device.setPin(pin.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }



    static private boolean createBond(BluetoothDevice device){
        return device.createBond();
    }

    static public  boolean removeBond(BluetoothDevice device){
        boolean result = false;
        try {
            Method method = null;
            method = device.getClass().getDeclaredMethod("removeBond");
            method.setAccessible(true);
            result = (boolean) method.invoke(device);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return result;
    }

    static private void cancelBondProcess(BluetoothDevice device){
        try {
            Method method = null;
            method = device.getClass().getDeclaredMethod("cancelBondProcess");
            method.setAccessible(true);
            method.invoke(device);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}