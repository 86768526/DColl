package com.c.dcoll.quartz;

import com.c.dcoll.tools.Utils;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import oshi.hardware.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Configuration
@EnableScheduling
public class timingTask {

//    @Scheduled(cron = "0/5 * * * * ?")
    @Scheduled(fixedRate=5000)
    public void collectData(){
        System.out.println("5秒一次 -------------------------------------------------");
        System.out.println(getPowerSources());
        System.out.println(getNetworkIFs());
        System.out.println(getGraphicsCards());
        System.out.println(getComputerSystem());
        System.out.println(getDiskStores());
        System.out.println(getDisplays());
        System.out.println(getMemory());
        System.out.println(getSoundCards());
        System.out.println(getUsbDevices());
        System.out.println(getSensors());
        System.out.println(getCpuInfo());


    }

//    private Map<String,String> getAddress(){
//        Map<String,String> returnMap =  new HashMap<String,String>();
//        Locale locale = Locale.getDefault();
//        String languageCode = locale.getLanguage();
//        String MacAddress="";
//        String Ipv4Address="";
//        String Ipv6Address="";
//        String HostName="";
//        if(languageCode.equals("zh")) {
//            String os=System.getProperty("os.name");
//            if(os!=null&&os.startsWith("Windows")){
//                try{
//                    String command = "cmd.exe /c ipconfig /all";
//                    Process p = Runtime.getRuntime().exec(command);
//                    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(),"GBK"));
//                    String line;
//                    while((line = br.readLine())!=null){
//                        if(line.indexOf("物理地址")>0){
//                            int index = line.indexOf(":");
//                            index+=2;
//                            MacAddress=line.substring(index);
//                        }
//                        if(line.indexOf("IPv4")>0){
//                            int index = line.indexOf(":");
//                            index+=2;
//                            Ipv4Address=line.substring(index);
//                        }
//                        if(line.indexOf("IPv6")>0){
//                            int index = line.indexOf(":");
//                            index+=2;
//                            Ipv6Address=line.substring(index);
//                        }
//                        if(line.indexOf("主机")>0){
//                            int index = line.indexOf(":");
//                            index+=2;
//                            HostName=line.substring(index);
//                        }
//                    }
//                    br.close();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }
//        if(languageCode.equals("en")){
//        }
//        returnMap.put("Ipv4Address",Ipv4Address);
//        returnMap.put("Ipv6Address",Ipv6Address);
//        returnMap.put("HostName",HostName);
//        returnMap.put("MacAddress",MacAddress);
//        return returnMap;
//    }
private HardwareAbstractionLayer hardware = Utils.gethardware();

private Map getPowerSources(){
        Map return_map = new HashMap<String,Object>();
        List<PowerSource> powerSources = hardware.getPowerSources();
        IntStream.range(0,powerSources.size()).forEach(i->{
            Map power_map = new HashMap<String,Object>();
            power_map.put("Amperage",powerSources.get(i).getAmperage());
            power_map.put("CapacityUnits",powerSources.get(i).getCapacityUnits());
            power_map.put("Chemistry",powerSources.get(i).getChemistry());
            power_map.put("CurrentCapacity",powerSources.get(i).getCurrentCapacity());
            power_map.put("CycleCount",powerSources.get(i).getCycleCount());
            power_map.put("DesignCapacity",powerSources.get(i).getDesignCapacity());
            power_map.put("DeviceName",powerSources.get(i).getDeviceName());
            power_map.put("ManufactureDate",powerSources.get(i).getManufactureDate());
            power_map.put("Manufacturer",powerSources.get(i).getManufacturer());
            power_map.put("MaxCapacity",powerSources.get(i).getMaxCapacity());
            power_map.put("Name",powerSources.get(i).getName());
            power_map.put("PowerUsageRate",powerSources.get(i).getPowerUsageRate());
            power_map.put("RemainingCapacityPercent",powerSources.get(i).getRemainingCapacityPercent());
            power_map.put("TimeRemainingEstimated",powerSources.get(i).getTimeRemainingEstimated());
            power_map.put("Temperature",powerSources.get(i).getTemperature());
            power_map.put("SerialNumber",powerSources.get(i).getSerialNumber());
            power_map.put("TimeRemainingInstant",powerSources.get(i).getTimeRemainingInstant());
            power_map.put("Voltage",powerSources.get(i).getVoltage());
            return_map.put("powerData_"+i,power_map);
        });
        return return_map;
    }

    private Map getNetworkIFs(){
        Map return_map = new HashMap<String,Object>();
        List<NetworkIF> networkIFS = hardware.getNetworkIFs();
        IntStream.range(0,networkIFS.size()).forEach(i->{
            Map networkData_map = new HashMap<String,Object>();
            networkData_map.put("BytesRecv",networkIFS.get(i).getBytesRecv());
            networkData_map.put("BytesSent",networkIFS.get(i).getBytesSent());
            networkData_map.put("Collisions",networkIFS.get(i).getCollisions());
            networkData_map.put("DisplayName",networkIFS.get(i).getDisplayName());
            networkData_map.put("IfType",networkIFS.get(i).getIfType());
            networkData_map.put("InDrops",networkIFS.get(i).getInDrops());
            networkData_map.put("InErrors",networkIFS.get(i).getInErrors());

            networkData_map.put("IPv4addr",Stream.of(networkIFS.get(i).getIPv4addr()).collect(Collectors.toList()).toString());
            networkData_map.put("IPv6addr",Stream.of(networkIFS.get(i).getIPv6addr()).collect(Collectors.toList()).toString());
            networkData_map.put("Macaddr",networkIFS.get(i).getMacaddr());
            networkData_map.put("MTU",networkIFS.get(i).getMTU());
            networkData_map.put("Name",networkIFS.get(i).getName());
            networkData_map.put("NdisPhysicalMediumType",networkIFS.get(i).getNdisPhysicalMediumType());
            networkData_map.put("OutErrors",networkIFS.get(i).getOutErrors());
            networkData_map.put("PacketsRecv",networkIFS.get(i).getPacketsRecv());
            networkData_map.put("PacketsSent",networkIFS.get(i).getPacketsSent());
            networkData_map.put("PrefixLengths", Stream.of(networkIFS.get(i).getPrefixLengths()).collect(Collectors.toList()));
            networkData_map.put("Speed",networkIFS.get(i).getSpeed());
            networkData_map.put("SubnetMasks",Stream.of(networkIFS.get(i).getSubnetMasks()).collect(Collectors.toList()).toString());
            networkData_map.put("TimeStamp",networkIFS.get(i).getTimeStamp());
            return_map.put("NetworkIFs_"+i,networkData_map);
        });
        return return_map;
    }

    private  Map getGraphicsCards(){
        Map return_map = new HashMap<String,Object>();
        List<GraphicsCard> graphicsCards =  hardware.getGraphicsCards();
        IntStream.range(0,graphicsCards.size()).forEach(i->{
            Map graphicsData = new HashMap<String,Object>();
            graphicsData.put("DeviceId",graphicsCards.get(i).getDeviceId());
            graphicsData.put("Name",graphicsCards.get(i).getName());
            graphicsData.put("Vendor",graphicsCards.get(i).getVendor());
            graphicsData.put("VersionInfo",graphicsCards.get(i).getVersionInfo());
            graphicsData.put("VRam",graphicsCards.get(i).getVRam());
            return_map.put("graphics_"+i,graphicsData);
        });
        return return_map;
    }

    private Map getComputerSystem(){
        Map computerSystemData = new HashMap<String,Object>();
        ComputerSystem computerSystem = hardware.getComputerSystem();
        computerSystemData.put("Baseboard",computerSystem.getBaseboard());
        computerSystemData.put("Firmware",computerSystem.getFirmware());
        computerSystemData.put("Manufacturer",computerSystem.getManufacturer());
        computerSystemData.put("Model",computerSystem.getModel());
        computerSystemData.put("SerialNumber",computerSystem.getSerialNumber());
        return computerSystemData;
    }

    private Map getDiskStores() {
        Map return_map = new HashMap<String,Object>();
        List<HWDiskStore> hds = hardware.getDiskStores();
        IntStream.range(0,hds.size()).forEach(i->{
            Map hdData = new HashMap<String,Object>();
            hdData.put("CurrentQueueLength",hds.get(i).getCurrentQueueLength());
            hdData.put("Model",hds.get(i).getModel());
            hdData.put("Name",hds.get(i).getName());
            hdData.put("Partitions",hds.get(i).getPartitions());
            hdData.put("ReadBytes",hds.get(i).getReadBytes());
            hdData.put("Reads",hds.get(i).getReads());
            hdData.put("Serial",hds.get(i).getSerial());
            hdData.put("Size",hds.get(i).getSize());
            hdData.put("TimeStamp",hds.get(i).getTimeStamp());
            hdData.put("TransferTime",hds.get(i).getTransferTime());
            hdData.put("WriteBytes",hds.get(i).getWriteBytes());
            hdData.put("Writes",hds.get(i).getWrites());
            hdData.put("Attributes",hds.get(i).updateAttributes());
            return_map.put("hd_"+i,hdData);
        });
        return  return_map;
    }

    private Map getDisplays(){
        Map return_map = new HashMap<String,Object>();
        List<Display> displays = hardware.getDisplays();
        IntStream.range(0,displays.size()).forEach(i->{
            Map displayData = new HashMap<String,Object>();
            displayData.put("Edid",Bytes2StrList(displays.get(i).getEdid()));
            return_map.put("display_"+i,displayData);
        });
        return return_map;
    }

    private  Map getMemory(){
        Map memoryData = new HashMap<String,Object>();
        GlobalMemory memory = hardware.getMemory();
        memoryData.put("Available",memory.getAvailable());
        memoryData.put("PageSize",memory.getPageSize());
        memoryData.put("PhysicalMemory",memory.getPhysicalMemory());
        memoryData.put("Total",memory.getTotal());
        memoryData.put("VirtualMemory",memory.getVirtualMemory());
        return memoryData;
    }

    private Map getSoundCards(){
        Map return_map = new HashMap<String,Object>();
        List<SoundCard> soundCards = hardware.getSoundCards();
        IntStream.range(0,soundCards.size()).forEach(i->{
            Map soundCardData = new HashMap<String,Object>();
            soundCardData.put("Codec",soundCards.get(i).getCodec());
            soundCardData.put("DriverVersion",soundCards.get(i).getDriverVersion());
            soundCardData.put("Name",soundCards.get(i).getName());
            return_map.put("soundCard_"+i,soundCardData);
        });
        return return_map;
    }

    private Map getUsbDevices(){
        Map retuen_map = new HashMap<String,Object>();
        List<UsbDevice> usbDevices =  hardware.getUsbDevices(true);
        IntStream.range(0,usbDevices.size()).forEach(i->{
             Map usbDeviceData = new HashMap<String,Object>();
            List<UsbDevice> connectDevices = usbDevices.get(i).getConnectedDevices();
            IntStream.range(0,connectDevices.size()).forEach(index->{
                Map connectDeviceData = new HashMap<String,Object>();
                connectDeviceData.put("VendorId",connectDevices.get(index).getVendorId());
                connectDeviceData.put("Vendor",connectDevices.get(index).getVendor());
                connectDeviceData.put("DeviceId",connectDevices.get(index).getUniqueDeviceId());
                connectDeviceData.put("SerialNumber",connectDevices.get(index).getSerialNumber());
                connectDeviceData.put("ProductId",connectDevices.get(index).getProductId());
                connectDeviceData.put("Name",connectDevices.get(index).getName());
                connectDeviceData.put("ConnectedDevices",connectDevices.get(index).getConnectedDevices());
                usbDeviceData.put("connectDevive_"+index,connectDeviceData);
            });
            usbDeviceData.put("Name",usbDevices.get(i).getName());
            usbDeviceData.put("ProductId",usbDevices.get(i).getProductId());
            usbDeviceData.put("SerialNumber",usbDevices.get(i).getSerialNumber());
            usbDeviceData.put("UniqueDeviceId",usbDevices.get(i).getUniqueDeviceId());
            usbDeviceData.put("Vendor",usbDevices.get(i).getVendor());
            usbDeviceData.put("VendorId",usbDevices.get(i).getVendorId());
            retuen_map.put("usbDevice_"+i,usbDeviceData);
        });
        return retuen_map;
    }

    private Map getSensors(){
        Map return_map = new HashMap<String,Object>();
        Sensors sensors = hardware.getSensors();
        return_map.put("Temperature",sensors.getCpuTemperature());
        return_map.put("CpuVoltage",sensors.getCpuVoltage());
        return_map.put("FanSpeeds",intList2StrList(sensors.getFanSpeeds()));
        return return_map;
    }

    private Map getCpuInfo(){
        Map cpu_info = new HashMap<String,Object>();
        CentralProcessor cpu = hardware.getProcessor();
        cpu_info.put("ContextSwitches",cpu.getContextSwitches());
        cpu_info.put("CurrentFreq",LongList2StrList(cpu.getCurrentFreq()));
        cpu_info.put("Interrupts",cpu.getInterrupts());
        cpu_info.put("LogicalProcessorCount",cpu.getLogicalProcessorCount());
        List<CentralProcessor.LogicalProcessor> logicalProcessors =  cpu.getLogicalProcessors();

        IntStream.iterate(0,i -> {
            Map logicalProcessor_map = new HashMap<String,Object>();
            logicalProcessor_map.put("NumaNode",logicalProcessors.get(i).getNumaNode());
            logicalProcessor_map.put("PhysicalPackageNumber",logicalProcessors.get(i).getPhysicalPackageNumber());
            logicalProcessor_map.put("PhysicalProcessorNumber",logicalProcessors.get(i).getPhysicalProcessorNumber());
            logicalProcessor_map.put("ProcessorGroup",logicalProcessors.get(i).getProcessorGroup());
            logicalProcessor_map.put("ProcessorNumber",logicalProcessors.get(i).getProcessorNumber());
            cpu_info.put("logicalProcessor_"+i,logicalProcessor_map);
            i++;
            return i;}).limit(logicalProcessors.size());

//          IntStream.range(0, logicalProcessors.size()).forEach( index -> {
//                      Map logicalProcessor_map = new HashMap<String,Object>();
//                      logicalProcessor_map.put("NumaNode", logicalProcessors.get(index).getNumaNode());
//                      logicalProcessor_map.put("PhysicalPackageNumber", logicalProcessors.get(index).getPhysicalPackageNumber());
//                      logicalProcessor_map.put("PhysicalProcessorNumber", logicalProcessors.get(index).getPhysicalProcessorNumber());
//                      logicalProcessor_map.put("ProcessorGroup", logicalProcessors.get(index).getProcessorGroup());
//                      logicalProcessor_map.put("ProcessorNumber", logicalProcessors.get(index).getProcessorNumber());
//                      cpu_info.put("logicalProcessor_"+index,logicalProcessor_map);
//          });

        cpu_info.put("MaxFreq",cpu.getMaxFreq());
        cpu_info.put("PhysicalPackageCount",cpu.getPhysicalPackageCount());
        cpu_info.put("PhysicalProcessorCount",cpu.getPhysicalProcessorCount());
        cpu_info.put("ProcessorCpuLoadTicks",LongList2StrList(cpu.getProcessorCpuLoadTicks()));
        cpu_info.put("ProcessorIdentifier",cpu.getProcessorIdentifier());
        cpu_info.put("SystemCpuLoadTicks",LongList2StrList(cpu.getSystemCpuLoadTicks()));
        cpu.getSystemLoadAverage(3);
        cpu.getProcessorCpuLoadBetweenTicks(cpu.getProcessorCpuLoadTicks());
        cpu.getSystemCpuLoadBetweenTicks(cpu.getSystemCpuLoadTicks());
        return cpu_info;
    }

    private List<String>  Bytes2StrList(byte[] bytes){
        List<String> return_list = new ArrayList<String>();
        IntStream.range(0,bytes.length).forEach(i->{
            return_list.add(String.valueOf(bytes[i]));
        });
        return return_list;
    }
    private List<String>  intList2StrList(int[] intList){
        List<String> return_list = new ArrayList<String>();
        IntStream.range(0,intList.length).forEach(i->{
            return_list.add(String.valueOf(intList[i]));
        });
        return return_list;
    }

    private List<String>  LongList2StrList(long[] longlist){
        List<String> return_list = new ArrayList<String>();
        IntStream.range(0,longlist.length).forEach(i->{
            return_list.add(String.valueOf(longlist[i]));
        });
        return return_list;
    }
    private List<String>  LongList2StrList(long[][] longlist){
        List<String> return_list = new ArrayList<String>();
        IntStream.range(0,longlist.length).forEach(i->{
            IntStream.range(0,longlist[i].length).forEach(n->{
                return_list.add(i+":" + String.valueOf(longlist[i][n]));
            });
        });
        return return_list;
    }
}
