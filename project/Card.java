package project;

import java.net.MalformedURLException;
import java.nio.file.FileSystems;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card
	{
	public static String[] _color = { "Spade", "Diamond", "Heart", "Club" };
//	Card[] arr = {"Spade A","Spade 2","Spade 3","Spade 4","Spade 5","Spade 6","Spade 7"
//			,"Spade 8","Spade 9","Spade 10","Spade J","Spade Q","Spade K","Heart A"
//			,"Heart 2","Heart 3","Heart 4","Heart 5","Heart 6","Heart 7","Heart 8"
//			,"Heart 9","Heart 10","Heart J","Heart Q","Heart K","Diamond A","Diamond 2"
//			,"Diamond 3","Diamond 4","Diamond 5","Diamond 6","Diamond 7","Diamond 8"
//			,"Diamond 9","Diamond 10","Diamond J","Diamond Q","Diamond K","Club A"
//			,"Club 2","Club 3","Club 4","Club 5","Club 6","Club 7","Club 8","Club 9"
//			,"Club 10","Club J","Club Q","Club K"};
	
		public int number;
		public int color;
		
		public int getnumber() 
		{
			return number;
		}
		public int getcolor() 
		{
			return color;
		}
		public Card(int color,int number) 
		{
			this.number = number;
			this.color = color;
		}
		public static String GetCardName(Card card) {
			String file = _color[card.getcolor()] + Integer.toString(card.getnumber() + 1);
			return file;

		}
		public static void GetImage(ImageView image,Card card)
		{
			Image imageU3;
			try {
				imageU3 = new Image(FileSystems.getDefault()
						.getPath("src", "project", "PNG", GetCardName(card) + ".png").toUri().toURL().toString());
				image.setImage(imageU3);
				image.setVisible(true);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}