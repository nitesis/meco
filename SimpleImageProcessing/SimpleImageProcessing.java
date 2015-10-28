import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SimpleImageProcessing
{

	public static void main(String[] args) throws IOException
	{
		// Bilddatei als BufferedImage Objekt einlesen
		BufferedImage img = ImageIO.read(new File("hongkong.jpg"));
		
		// Schleife über alle Pixel im Bild basierend auf Breite und Höhe (w = width, h = height)
		for (int w = 0; w < img.getWidth(); w++)
		{
			for (int h = 0; h < img.getHeight(); h++)
			{
				// BufferedImage.getRGB() speichert Farben eines Pixels als einzelne Integer Werte
				// Man kann das Color(int) Objekt verwenden, um die RGB Werte anzupassen.
				Color color = new Color(img.getRGB(w, h));
				
				int r = color.getRed();
				int g = color.getGreen();
				int b = color.getBlue();
				
				Color newColor; //new color object for this pixel
				
				//b/w image code
				
				
				//gray image
				
				
				//soften image
				
				
				//change color channels
				
				
				//draw only one color channel
				
				
				//replace color by other color
				
				
				//reduce to 32 / 64 colors
				
				
				//other filters ...
				
				
				//color change
				newColor = new Color(g,b,r);
											
				//-----------------------------------------
				// Pixelwert mit neuer Farbe an Position setzen
				// Color.getRGB().
				img.setRGB(w, h, newColor.getRGB());
			}
		}
		// neu erstelltes Bild in Datei speichern.
		ImageIO.write(img, "jpg", new File("hongkong_bw.jpg"));
	}
	
	/*
	 * Methode zur Ausgabe der r,g,b Werte des aktuellen Pixels
	 */
	public static void printPixelRGB(int r, int g, int b) 
	{
		System.out.println("rgb: " + r + ", " + g + ", " + b);
	}
}