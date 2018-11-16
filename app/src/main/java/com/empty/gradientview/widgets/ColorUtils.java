package com.empty.gradientview.widgets;

import android.graphics.Color;

public class ColorUtils {
	public static int[] String2Int(String color_staring) {
		int color = Color.parseColor(color_staring);
		int red = (color & 0xff0000) >> 16;
		int green = (color & 0x00ff00) >> 8;
		int blue = (color & 0x0000ff);
		return new int[]{red,green,blue};
	}
	
	public static int[] initColors(){
		int baseLength = GradientVIew.colorBase.length;
		int colors[] = new int[baseLength*GradientVIew.ColorCount];
		for (int i = 0; i < baseLength; i++) {
			for (int j = 0; j < 10; j++) {
				String color1 = GradientVIew.colorBase[(i+1)%baseLength];
				String color2 = GradientVIew.colorBase[i];
				int color = getTenColor(color1,color2,j);
				colors[i*GradientVIew.ColorCount+j] = color;
			}
		}
		return colors;
	}
	
	private static int getTenColor(String color1, String color2,int index){
		int [] rgb1 = String2Int(color1);
		int [] rgb2 = String2Int(color2);
		return getColorInt(rgb1,rgb2,index);
	}
	private static int getColorInt(int[] start, int[] end, int index) {
		int R = 0,G = 0,B = 0;
		for (int i = 0; i < start.length; i++) {
			float l = start[i] - end[i];
			float f = l / GradientVIew.ColorCount;
			int rgb = (int) (end[i] + f*index);
			switch (i) {
			case 0:
				R = rgb;
				break;
			case 1:
				G = rgb;
				break;
			case 2:
				B = rgb;
				break;
			}
		}
		return Color.rgb(R, G, B);
	}
}
