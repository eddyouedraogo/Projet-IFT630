package main;

import client.*;
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


		try
		{
			Gare.getInstance().initGare();
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
