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


public class TrainFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;    
    final static String trainInStat1 = "/resources/TrainInStat1.png";
    final static String trainInStat2 = "/resources/TrainInStat2.png";
    final static String rails = "/resources/Rails.png";    
    public JPanel panel; 

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
        setSize(new Dimension(1200, 900));
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        revalidate();
        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(6,6,getBounds().width, getBounds().height);        
        
        
        getContentPane().add(panel);
        panel.setLayout(null);       
        
        
        JButton btnEntreeEnGare = new JButton("Nouveau Train en Gare");
        btnEntreeEnGare.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              Gare.getInstance().nouveauTrainEnGare();
            }
        });
        btnEntreeEnGare.setBounds(40, 18, 150, 23);
        panel.add(btnEntreeEnGare);
        
        JButton btnDepart = new JButton("Depart");
        btnDepart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               Gare.getInstance().nouveauTrainEnRotation();
            }
        });
        btnDepart.setBounds(221, 18, 150, 23);
        panel.add(btnDepart);        

        JButton btnSortirTrainGare = new JButton("Quitter Gare");
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
        
        btnSortirTrainGare.setBounds(429, 18, 150, 23);
        panel.add(btnSortirTrainGare);
        
        JButton btnEntreeTrainGare = new JButton("Entree Gare");
        btnSortirTrainGare.addMouseListener(new MouseAdapter() {
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
        
        btnEntreeTrainGare.setBounds(637, 18, 180, 23);
        panel.add(btnEntreeTrainGare);
        
        JLabel lblQuais = new JLabel("Quais");
        lblQuais.setBounds(169, 100, 61, 16);
        panel.add(lblQuais);
        
        JTextPane txtpnQuaistextpanel = new JTextPane();
        txtpnQuaistextpanel.setBackground(new Color(255, 250, 240));
        txtpnQuaistextpanel.setBounds(40, 128, 331, 378);
        panel.add(txtpnQuaistextpanel);
        
        JLabel lblVoies = new JLabel("Voies");
        lblVoies.setBounds(566, 100, 61, 16);
        panel.add(lblVoies);
        
        JTextPane textPane = new JTextPane();
        textPane.setBackground(new Color(255, 250, 240));
        textPane.setBounds(394, 128, 375, 378);
        panel.add(textPane);
        
        JLabel lblRotation = new JLabel("Rotation");
        lblRotation.setBounds(962, 100, 61, 16);
        panel.add(lblRotation);
        
        JTextPane textPane_1 = new JTextPane();
        textPane_1.setBackground(new Color(255, 250, 240));
        textPane_1.setBounds(796, 128, 383, 378);
        panel.add(textPane_1);
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
     
        lblNewLabel.setIcon(new ImageIcon(TrainFrame.class.getResource(trainInStat2)));
        lblNewLabel.setBounds(p.x, p.y, 1000, 100);
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
