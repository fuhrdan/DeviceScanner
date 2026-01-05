import java.io.*;
import java.util.*;

public class DeviceScanner
{
    public static List<DeviceInfo> scanDevices() throws IOException
    {
        List<DeviceInfo> devices = new ArrayList<>();

        List<String> command = new ArrayList<>();
        command.add("powershell");
        command.add("-Command");
        command.add(
            "Get-PnpDevice | " +
            "Select-Object FriendlyName,ConfigManagerErrorCode | " +
            "ConvertTo-Csv -NoTypeInformation"
        );

        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectErrorStream(true);

        Process process = builder.start();

        BufferedReader reader =
            new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        boolean firstLine = true;

        while ((line = reader.readLine()) != null)
        {
            // Skip CSV header
            if (firstLine)
            {
                firstLine = false;
                continue;
            }

            // CSV: "Name","CM_PROB_*"
            String[] parts = line.split("\",\"");

            if (parts.length == 2)
            {
                String name = parts[0].replace("\"", "").trim();
                String code = parts[1].replace("\"", "").trim();

                if (!name.isEmpty())
                {
                    devices.add(new DeviceInfo(name, code));
                }
            }
        }

        return devices;
    }
}
