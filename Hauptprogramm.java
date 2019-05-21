
//import java.awt.Color;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Hauptprogramm {
    public static BufferedImage[] returnedBimages;
    public static BufferedImage ReadImage;
    public static Rectangle Size;
    public static JLabel frame1 =new JLabel();
    public static JLabel frame2 =new JLabel();
    public static JFrame MainFrame = new JFrame("Projektwoche");// MainFrame wird erschafen mit überschrift

    public static void main(String[] args) {
        Size=new Rectangle(400,400);
        File bmpFile = new File("Hi!.gif");//setzt die zu lesende DAtei auf Hi! 
        try {
            ReadImage = ImageIO.read(bmpFile);//versucht, ein BI daraus zu machen.
        } catch (IOException e) {//falls das nicht gelingt, stoppe das Programm.
            e.printStackTrace();
            System.out.println("Picture not found, exiting");
            System.exit(1);
        }
        returnedBimages=new BufferedImage[2];
        BufferedImage[] Imagearray = createimagearray();
        File outputfile1= new File("img1.gif");
        File outputfile2= new File("img2.gif");

        try {
            ImageIO.write(Imagearray[0], "gif", outputfile1);
            ImageIO.write(Imagearray[1], "gif", outputfile2);
        } catch (Exception e) {
            System.out.println(e);
        }
        

        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// es schließt sich wenn nötig
        MainFrame.setSize(1000, 800);// es ist 400*400 pixel groß
        MainFrame.setResizable(false);// man kann es nicht vergrößern
        MainFrame.setLayout(null);
        
        
        
        
        

        ImageIcon icon1 = new ImageIcon(Imagearray[0]); 
        ImageIcon icon2 = new ImageIcon(Imagearray[1]); 
        ImageIcon resizedicon1 = new ImageIcon(getScaledImage(icon1.getImage(),Size.width,Size.height));
        ImageIcon resizedicon2 = new ImageIcon(getScaledImage(icon2.getImage(),Size.width,Size.height));


        frame1.setIcon(resizedicon1);
        frame2.setIcon(resizedicon2);
        frame1.setBounds(Size);
        frame2.setBounds(Size);
        MainFrame.add(frame1);// die frames wird in das MainFrame gelegt
        MainFrame.add(frame2);// die frames wird in das MainFrame gelegt
        frame1.setBounds(0, 0, Size.width, Size.height);
        frame2.setBounds(0, 0, Size.width, Size.height);


        MainFrame.setVisible(true);// es wird sichtbar gemacht

        frame1.setName("1");
        frame2.setName("2");
        
        mousedrag md = new mousedrag();
        frame1.addMouseListener(md);
        frame2.addMouseListener(md);
        frame1.addMouseMotionListener(md);
        frame2.addMouseMotionListener(md);
        md.frame1 = frame1;
        md.frame2 = frame2;
        md.Mainframe = MainFrame;

        MainFrame.getContentPane().setBackground(Color.black);
        MainFrame.setBackground(Color.black);

        
    
    }

    public JLabel getframe1(){
        return frame1;
    }

    public JLabel getframe2(){
        return frame2;
    }

    public JFrame getmainframe(){
        return MainFrame;
    }

    //Stackoverflow, again.

    public static Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
    
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
    
        return resizedImg;
    }


    public static BufferedImage[] createimagearray() {
        BufferedImage img1 = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);// deklariert typ und größe
        BufferedImage img2 = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);// deklariert typ und größe

        
        //immg. setRGB
        Boolean boolarray[][]=new Boolean[100][100];
        for (int Spalte = 0; Spalte < 99; Spalte++) {
            for (int Zeile = 0; Zeile < 99; Zeile++) {

                boolarray[Spalte][Zeile] =process(ReadImage.getRGB(Spalte, Zeile));
            }
        }
        //Color schwarz = new Color(0, 0, 0,50);
        //Color weiß = new Color(255, 255, 255,50);
        int weiß = -1;
        int schwarz = 15;
        for (int Spalte = 0; Spalte < 99; Spalte++) {
            for (int Zeile = 0; Zeile < 99; Zeile++) {
                if (boolarray[Spalte][Zeile]) {//wenn der Pixel weiß ist
                    if (Math.random()<0.5) {
                        
                        
                    img1.setRGB(Spalte*2, Zeile*2, schwarz);//oben links schwarz
                    img1.setRGB(Spalte*2+1, Zeile*2, weiß);//oben rechts weiß
                    img1.setRGB(Spalte*2, Zeile*2+1, weiß);//unten links weiß
                    img1.setRGB(Spalte*2+1, Zeile*2+1, schwarz);//unten rechts schwarz

                    img2.setRGB(Spalte*2, Zeile*2, schwarz);//oben links schwarz
                    img2.setRGB(Spalte*2+1, Zeile*2, weiß);//oben rechts weiß
                    img2.setRGB(Spalte*2, Zeile*2+1, weiß);//unten links weiß
                    img2.setRGB(Spalte*2+1, Zeile*2+1, schwarz);//unten rechts schwarz

                    } else {
                        
                    img1.setRGB(Spalte*2, Zeile*2, weiß);//oben links schwarz
                    img1.setRGB(Spalte*2+1, Zeile*2, schwarz);//oben rechts weiß
                    img1.setRGB(Spalte*2, Zeile*2+1, schwarz);//unten links weiß
                    img1.setRGB(Spalte*2+1, Zeile*2+1, weiß);//unten rechts schwarz

                    img2.setRGB(Spalte*2, Zeile*2, weiß);//oben links schwarz
                    img2.setRGB(Spalte*2+1, Zeile*2, schwarz);//oben rechts weiß
                    img2.setRGB(Spalte*2, Zeile*2+1, schwarz);//unten links weiß
                    img2.setRGB(Spalte*2+1, Zeile*2+1, weiß);//unten rechts schwarz

                    }

                } else {//wenn der Pixel schwarz ist
                    
                    if (Math.random()<0.5) {
                        
                        img1.setRGB(Spalte*2, Zeile*2, schwarz);//oben links schwarz
                        img1.setRGB(Spalte*2+1, Zeile*2, weiß);//oben rechts weiß
                        img1.setRGB(Spalte*2, Zeile*2+1, weiß);//unten links weiß
                        img1.setRGB(Spalte*2+1, Zeile*2+1, schwarz);//unten rechts schwarz
    
                        img2.setRGB(Spalte*2, Zeile*2, weiß);//oben links weiß
                        img2.setRGB(Spalte*2+1, Zeile*2, schwarz);//oben rechts schwarz
                        img2.setRGB(Spalte*2, Zeile*2+1, schwarz);//unten links schwarz
                        img2.setRGB(Spalte*2+1, Zeile*2+1, weiß);//unten rechts weiß

                    } else {

                        img1.setRGB(Spalte*2, Zeile*2, weiß);//oben links schwarz
                        img1.setRGB(Spalte*2+1, Zeile*2, schwarz);//oben rechts weiß
                        img1.setRGB(Spalte*2, Zeile*2+1, schwarz);//unten links weiß
                        img1.setRGB(Spalte*2+1, Zeile*2+1, weiß);//unten rechts schwarz

                        img2.setRGB(Spalte*2, Zeile*2, schwarz);//oben links schwarz
                        img2.setRGB(Spalte*2+1, Zeile*2, weiß);//oben rechts weiß
                        img2.setRGB(Spalte*2, Zeile*2+1, weiß);//unten links weiß
                        img2.setRGB(Spalte*2+1, Zeile*2+1, schwarz);//unten rechts schwarz
                        
                    }

                }
            }
        }



        returnedBimages[0]=img1;
        returnedBimages[1]=img2;
        return returnedBimages;

    }

    private static Boolean process(int rgb) {
        if (rgb==-16777216) {
            return false;//schwarz = false
        } else {
            return true;
        }
    }
}