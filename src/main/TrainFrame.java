package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import trainstation.Gare;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import java.awt.TextArea;
import javax.swing.border.TitledBorder;


public class TrainFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;    
    final static String trainInStat1 = "/resources/TrainInStat1.png";
    final static String trainInStat2 = "/resources/TrainInStat2.png";
    final static String rails = "/resources/Rails.png";    
    public JPanel panel; 
    //public JTextArea quaisTextArea;
    //public JTextArea voiesTextArea;
    //public JTextArea rotationTextArea;

    private static TrainFrame instance=null ; 
    public static TrainFrame getInstance()
    {
        if (instance == null) 
            instance = new TrainFrame();
        return instance;
    }
    
    private TrainFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(null);
        setSize(new Dimension(1804, 1031));
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        revalidate();
        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(6,6,1768, 983);        
        
        
        getContentPane().add(panel);
        panel.setLayout(null);       
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "Boutons de control", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(1564, 818, 192, 152);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        
        JButton btnEntreeEnGare = new JButton(" + Quais ");
        btnEntreeEnGare.setBounds(6, 18, 180, 23);
        panel_1.add(btnEntreeEnGare);
        
        JButton btnDepart = new JButton("+ ROTATION");
        btnDepart.setBounds(6, 50, 180, 23);
        panel_1.add(btnDepart);
        
                JButton btnSortirTrainGare = new JButton("Quitter QUAIS");
                btnSortirTrainGare.setBounds(6, 86, 180, 23);
                panel_1.add(btnSortirTrainGare);
                
                JButton btnEntreeTrainGare = new JButton("Rotation -> Quais");
                btnEntreeTrainGare.setBounds(6, 122, 180, 23);
                panel_1.add(btnEntreeTrainGare);
                btnEntreeTrainGare.addMouseListener(new MouseAdapter() {
                	@Override
                	public void mouseClicked(MouseEvent e) {
                		try {
					Gare.getInstance().entreeGare();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                	}
                });
                btnSortirTrainGare.addMouseListener(new MouseAdapter() {
                	@Override
                	public void mouseClicked(MouseEvent e) {
                		try {
					Gare.getInstance().quitterGare();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                	}
                });
        btnDepart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               Gare.getInstance().nouveauTrainEnRotation();
            }
        });
        btnEntreeEnGare.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              Gare.getInstance().nouveauTrainEnGare();
            }
        });
        /*
        JLabel lblQuais = new JLabel("Quais");
        lblQuais.setBounds(169, 100, 61, 16);
        panel.add(lblQuais);

        quaisTextArea = new JTextArea();
        quaisTextArea.setBackground(new Color(255, 250, 240));
        quaisTextArea.setBounds(40, 128, 331, 634);
        panel.add(quaisTextArea);
        
        JLabel lblVoies = new JLabel("Voies");
        lblVoies.setBounds(566, 100, 61, 16);
        panel.add(lblVoies);
        
        voiesTextArea = new JTextArea();
        voiesTextArea.setBackground(new Color(255, 250, 240));
        voiesTextArea.setBounds(394, 128, 375, 634);
        panel.add(voiesTextArea);
        
        JLabel lblRotation = new JLabel("Rotation");
        lblRotation.setBounds(962, 100, 61, 16);
        panel.add(lblRotation);
        
        rotationTextArea = new JTextArea();
        rotationTextArea.setBackground(new Color(255, 250, 240));
        rotationTextArea.setBounds(796, 128, 383, 634);
        panel.add(rotationTextArea);*/
    }       

    
    
    public JLabel trains(String name)
    {       
        JLabel lblNewLabel = new JLabel(name);
        lblNewLabel.setIcon(new ImageIcon(TrainFrame.class.getResource(trainInStat1)));
        lblNewLabel.setBounds(33, 308, 133, 128);
        lblNewLabel.setVisible(true);
        panel.add(lblNewLabel);        
        panel.revalidate();
        panel.repaint();
        panel.setComponentZOrder(lblNewLabel, 0);
        
        return lblNewLabel;     
    }
    
    
    public void delete(JLabel label)
    {       
        panel.remove(label);
        panel.revalidate();    
        panel.repaint();
    }
    

    public JLabel trainRails(String name, Point p)
    {       
        JLabel lblNewLabel = new JLabel(name);
     
        lblNewLabel.setIcon(new ImageIcon(TrainFrame.class.getResource(rails)));
        lblNewLabel.setBounds( p.x, p.y, 100,1000);
        lblNewLabel.setVisible(true);
        panel.add(lblNewLabel);        
        panel.revalidate();
        panel.repaint();
        
        return lblNewLabel;     
    }    
    
    public JLabel createStationnementLabel(String name, Point p)
    {       
        JLabel lblNewLabel = new JLabel(name);
        lblNewLabel.setBackground(Color.yellow);
        lblNewLabel.setOpaque(true);
        
        lblNewLabel.setBounds(p.x, p.y, 130  , 130);
        lblNewLabel.setVisible(true);
        panel.add(lblNewLabel);        
        panel.revalidate();
        panel.repaint();
        
        return lblNewLabel;     
    }      
}
