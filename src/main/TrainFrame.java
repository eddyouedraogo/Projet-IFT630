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
        panel.setBounds(0,0,getBounds().width, getBounds().height);        
        
        
        getContentPane().add(panel);
        panel.setLayout(null);       
        

        
        JButton btnEntreeEnGare = new JButton("Entree en gare ");
        btnEntreeEnGare.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              Gare.getInstance().nouveauTrainEnGare();
            }
        });
        btnEntreeEnGare.setBounds(40, 460, 150, 23);
        panel.add(btnEntreeEnGare);
        
        JButton btnDepart = new JButton("Depart");
        btnDepart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               Gare.getInstance().nouveauTrainEnRotation();
            }
        });
        btnDepart.setBounds(40, 490, 150, 23);
        panel.add(btnDepart);        
        
        
        JButton btnEntreePassager = new JButton("Entree passager");
        btnEntreePassager.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        btnEntreePassager.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
              Gare.getInstance().nouveauPassager();
            }
        });
        btnEntreePassager.setBounds(40, 520, 150, 23);
        panel.add(btnEntreePassager);
        
        
        JButton btnDescentePassager = new JButton("Descente passager");
        btnDescentePassager.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	Gare.getInstance().entreePassager();
            }
        });
        btnDescentePassager.setBounds(40, 550, 150, 23);
        panel.add(btnDescentePassager);
        
        JButton btnSortirTrainGare = new JButton("Sortie de gare");
        btnSortirTrainGare.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		//Aziz
        	}
        });
        btnSortirTrainGare.setBounds(40, 580, 150, 23);
        panel.add(btnSortirTrainGare);
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
