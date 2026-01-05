public class DeviceInfo
{
    public String name;
    public String errorCode;

    public DeviceInfo(String name, String errorCode)
    {
        this.name = name;
        this.errorCode = errorCode;
    }

    public Status getStatus()
    {
        if ("CM_PROB_NONE".equals(errorCode))
            return Status.WORKING;

        if ("CM_PROB_PHANTOM".equals(errorCode))
            return Status.NEEDS_ATTENTION;

        return Status.NOT_WORKING;
    }
}
