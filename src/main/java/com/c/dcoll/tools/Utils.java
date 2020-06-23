package com.c.dcoll.tools;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

public class Utils {

    public static HardwareAbstractionLayer  gethardware(){
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        return hardware;
    }

}
