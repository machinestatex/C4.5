package ss;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Cizim extends Frame implements ActionListener
{
    int x1,x2,y1,y2;
    int yatay=1200;
    int dikey=750;

    public Cizim()
    {
        this.setTitle("Result tree drawing");
        this.setSize(yatay,dikey);
        this.setLocation(300, 100);
        this.setBackground(Color.WHITE);







        this.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

    }



    @Override
    public void paint(Graphics g){
        String s;
        s=" ";
        Map<String,String> nodeList=new HashMap<>();
        try {
            nodeList=new MainClass().main();
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.setColor(Color.red);
        
        g.setColor(Color.BLACK);
        g.drawLine((yatay*9/17)+20, 75,(yatay*4/17) , 200);
        g.drawLine((yatay*9/17)+20, 75, (yatay*13/17), 200);
        g.drawLine((yatay*4/17)+10, 230, (yatay*2/17), 400);
        g.drawLine((yatay*4/17)+10, 230, (yatay*6/17), 400);
        g.drawLine((yatay*13/17)+10, 230, (yatay*12/17), 400);
        g.drawLine((yatay*13/17)+10, 230, (yatay*16/17), 400);






        g.setColor(Color.pink);
        g.drawString(nodeList.get("key"),(yatay*9/17),45); 
        g.drawString(nodeList.get("secondLeftKey"),((yatay*4/17)),200); 
        g.drawString(nodeList.get("secondRightKey"),(yatay*13/17),200); 
        g.drawString(nodeList.get("thirdLeft1Key"),(yatay*2/17),400);  
        g.drawString(nodeList.get("thirdRight1Key"),((yatay*6/17)),400); 
        g.drawString(nodeList.get("thirdLeft2Key"),(yatay*12/17),400);
        g.drawString(nodeList.get("thirdRight2Key"),(yatay*16/17),400);




        g.fillOval((yatay*9/17),45,45,45); 
        g.fillOval(((yatay*4/17)),200,45,45); 
        g.fillOval((yatay*13/17),200,45,45); 
        g.fillOval((yatay*2/17),400,45,45);  
        g.fillOval(((yatay*6/17)),400,45,45); 
        g.fillOval((yatay*12/17),400,45,45);
        g.fillOval((yatay*16/17),400,45,45);


    }

    public static void main(String arg[]){
        new Cizim().setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }
}
