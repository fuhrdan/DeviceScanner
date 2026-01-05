import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DeviceDashboard extends JFrame
{
    public DeviceDashboard(List<DeviceInfo> devices)
    {
        setTitle("Device Health Dashboard");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (DeviceInfo device : devices)
        {
            panel.add(createDeviceRow(device));
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);
    }

    private JPanel createDeviceRow(DeviceInfo device)
    {
        JPanel row = new JPanel(new BorderLayout());
        row.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel nameLabel = new JLabel(device.name);
        JLabel statusLabel = new JLabel("●");

        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        statusLabel.setForeground(getColor(device.getStatus()));

        row.add(nameLabel, BorderLayout.WEST);
        row.add(statusLabel, BorderLayout.EAST);
        return row;
    }

    private Color getColor(Status status)
    {
        switch (status)
        {
            case WORKING:
                return Color.GREEN;
            case NEEDS_ATTENTION:
                return Color.YELLOW;
            default:
                return Color.RED;
        }
    }
}
