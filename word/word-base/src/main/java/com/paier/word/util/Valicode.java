package com.paier.word.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 验证码
 * @author Administrator
 *
 */
public class Valicode {

    public static String drawImage(BufferedImage bf){
    	int width = 90 , height = 30 , codeCount = 4 , fontHeight = 20 , codeX = 16 , codeY = 20 , red = 0 , green = 0 , blue = 0 ;
    	Graphics grap = bf.getGraphics();
    	Random random = new Random();
    	grap.setColor(Color.WHITE);
    	grap.fillRect(0, 0, width, height);
    	char[] chars = { '2', '3', '4', '5', '6','7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' ,'J','K','L','M','N','P','Q','R','S','T','V','W','X','Y','Z',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' ,'j','k','m','n','p','q','r','s','t','v','w','x','y','z'};
    	Font font = new Font("Fixedsys", Font.BOLD, fontHeight) ;
    	grap.setFont(font);
    	grap.setColor(Color.BLACK);
    	grap.drawRect(0, 0, width - 1, height - 1 );
    	for(int i = 1 ; i < 30 ; i ++ ){
    		int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(15);
            int yl = random.nextInt(15);
            grap.drawLine(x, y, x + xl, y + yl);
    	}
    	StringBuilder sb = new StringBuilder() ;
    	for(int j = 0 ; j < codeCount ; j ++ ){
    		int code = ThreadLocalRandom.current().nextInt(chars.length);
	 		String rand = String.valueOf(chars[code]);
	 		grap.setColor( new Color( random.nextInt(100) , random.nextInt(100) , random.nextInt(100) ) );
	 		grap.drawString(rand, codeX * ( j + 1) , codeY );
	 		sb.append(rand) ; 
    	}
    	grap.dispose();
    	return sb.toString();
    }
	
}
