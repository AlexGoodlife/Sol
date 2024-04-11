package Tasm;

public class RuntimeError
{
    public static void dispatchError(String message)
    {
        System.err.println(message);
        System.exit(1);
    }
}
