package utils;

import java.util.Random;

public class Stepmania 
{
	public static char UP_LEFT = '7';
	public static char UP = '8';
	public static char UP_RIGHT = '9';
	public static char LEFT = '4';
	public static char RIGHT = '6';
	public static char DOWN_LEFT = '1';
	public static char DOWN = '2';
	public static char DOWN_RIGHT = '3';
	public static char UP_DOWN = 'A';
	public static char LEFT_RIGHT = 'B';
	private static char[] arrowsJump = {UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT, UP_DOWN, LEFT_RIGHT};
	private static char[] arrowsDefault = {UP, RIGHT, DOWN, LEFT};
	private static Random randomGenerator = new Random();
	private static float jumpsPercent = 20f;
	private static int arrowsNumber = 0;
	private static int jumpsNumber = 0;
	private static char lastArrow = ' ';
	
	public static String randomArrows(double arrowLength)
	{
		if(arrowsNumber == 0  || ((float)((float)jumpsNumber / (float)arrowsNumber)) * 100f > jumpsPercent)
		{
			char arrow = lastArrow;
			while(arrow == lastArrow)
			{
				int i = randomGenerator.nextInt(arrowsDefault.length);
				arrow = arrowsDefault[i];
			}
			
			if(arrowLength > 0.25)
				arrowsNumber++;
			
			lastArrow = arrow;
			return String.valueOf(arrow);
		}
		else
		{
			char arrow = lastArrow;
			while(arrow == lastArrow)
			{
				int i = randomGenerator.nextInt(arrowsJump.length);
				arrow = arrowsJump[i];
			}
			arrowsNumber++;
			jumpsNumber++;
			lastArrow = arrow;
			return String.valueOf(arrow);
		}
	}
}
