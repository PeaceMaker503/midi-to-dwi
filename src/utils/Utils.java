package utils;

public class Utils {

	public static double roundDouble(double d)
	{
    	String s = String.format("%.2f", d);
    	s = s.replaceAll(",", ".");
    	double dr = Double.valueOf(s);
        double mid = 4 * dr;
        double out = Math.ceil(mid);
        s = String.format("%.2f", out/4.0);
        s = s.replaceAll(",", ".");
        dr = Double.valueOf(s);
        return dr;
	}
	
	public static double roundDouble3(double d)
	{
    	String s = String.format("%.3f", d);
    	s = s.replaceAll(",", ".");
    	double dr = Double.valueOf(s);
        return dr;
	}
}
