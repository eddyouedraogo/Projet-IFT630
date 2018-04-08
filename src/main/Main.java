package main;

import client.*;
import trainstation.Gare;
//import train.*;
//import trainstation.*;
//import java.util.concurrent.*;
public class Main {

	public static void main(String args[]) throws InterruptedException{

		/**
		 * TODO 
		 * 1- ajout du FRAME CLASS gare de LYON 
		 * 2- instanciation GARE
		 */
		System.out.println("test compilation");
		/*
		try
		{
			Gare.getInstance().initGare();
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */

		TrainFrame.getInstance().setVisible(true);
		try {
			Gare.getInstance().initGare();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}
