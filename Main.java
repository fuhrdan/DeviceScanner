import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            List<DeviceInfo> devices = DeviceScanner.scanDevices();
            DeviceDashboard dashboard = new DeviceDashboard(devices);
            dashboard.setVisible(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
