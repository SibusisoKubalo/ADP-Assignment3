/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sibusisokubalo218316038;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * @author Sibusiso Kubalo 218316038
 * Date 08 June 2021
 */
public class Mabudah {
    
    ObjectInputStream obj;
    ArrayList<Customer> customers;
    ArrayList<Supplier> suppliers;
    ArrayList<Integer> ages;
    int c = 0;
    
    public Mabudah(){
        customers = new ArrayList();
        suppliers = new ArrayList();
        ages = new ArrayList();
    }
  
    public void storeDataIntoLists() {
        try{
            obj = new ObjectInputStream(new FileInputStream("stakeholder.ser"));
            while(true){
                customers.add((Customer)obj.readObject());
                suppliers.add((Supplier)obj.readObject());
            } 
        }
        catch(EOFException e){
           System.out.println("File closed.");
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
          catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("unSorted customers");
        displayCustomers();
        sortAllCustomers();
        System.out.println("Sorted customers");
        displayCustomers();
        System.out.println("Ages of customers");
        ages();
        System.out.println("Dates re-formed");
        changeDate();
        displayCustomers();
        writeCustomerToTextFile();
        writeSupplierToTextFile();
    }
    
    public void displayCustomers(){
        for(Customer c: customers){
            System.out.println(c);
        }
    }
    
    public void displaySuppliers(){
        for(Supplier s: suppliers){
            System.out.println(s);
        }
    }
    
    public void sortAllCustomers(){
        for(int i = 0; i < customers.size() - 1; i++){
            for(int r = i + 1; r < customers.size(); r++){
                if(customers.get(i).getStHolderId().compareTo(customers.get(r).getStHolderId()) > 0){
                    Customer cus = customers.get(i);
                    customers.set(i, customers.get(r)); 
                    customers.set(r, cus);
                }
            }
        }
    }
    
    public void ages(){
        for(Customer c: customers){
           String []birth = c.getDateOfBirth().split("-");
           int born = Integer.parseInt(birth[0]);
            System.out.println(c.getSurName()+" > "+(2021-born));
            ages.add((2021-born));
        }
    }
    
    public void changeDate(){
        //1993-01-24  24 Jan 1993
        for(Customer c: customers){
            String []birth = c.getDateOfBirth().split("-");
            String born = birth[1];
            if(born.equals("01"))
                c.setDateOfBirth(birth[2]+" Jan "+birth[0]);
            else if(born.equals("02"))
                c.setDateOfBirth(birth[2]+" Feb "+birth[0]);
             else if(born.equals("03"))
                c.setDateOfBirth(birth[2]+" Mar "+birth[0]);
             else if(born.equals("04"))
                c.setDateOfBirth(birth[2]+" Apr "+birth[0]);
             else if(born.equals("05"))
                c.setDateOfBirth(birth[2]+" May "+birth[0]);
             else if(born.equals("06"))
                c.setDateOfBirth(birth[2]+" Jun "+birth[0]);
             else if(born.equals("07"))
                c.setDateOfBirth(birth[2]+" Jul "+birth[0]);
             else if(born.equals("08"))
                c.setDateOfBirth(birth[2]+" Aug "+birth[0]);
             else if(born.equals("09"))
                c.setDateOfBirth(birth[2]+" Sep "+birth[0]);
             else if(born.equals("10"))
                c.setDateOfBirth(birth[2]+" Oct "+birth[0]);
             else if(born.equals("11"))
                c.setDateOfBirth(birth[2]+" Nov "+birth[0]);
             else if(born.equals("12"))
                c.setDateOfBirth(birth[2]+" Dec "+birth[0]);
             else 
                c.setDateOfBirth(birth[2]+" Invalid "+birth[0]);
        }
    }
    
    public void writeCustomerToTextFile(){
        try{
            PrintWriter writer = new PrintWriter(new FileWriter("customerOutFile.txt"));
            writer.write("============================= CUSTOMERS =================================\n");
            writer.write("ID   	Name      	Surname   	Date of birth  	Age  \n");
            writer.write("=========================================================================\n");
            int i = 0;
            int rent = 0;
            for(Customer c: customers){
                if( c.getSurName().length() < 8)
                    writer.write(c.getStHolderId()+ "\t"+ c.getFirstName()+ "\t\t" + c.getSurName()+ "\t\t" + c.getDateOfBirth()+ "\t\t" + ages.get(i)+"\n");
                else
                    writer.write(c.getStHolderId()+ "\t"+ c.getFirstName()+ "\t\t" + c.getSurName()+ "\t" + c.getDateOfBirth()+ "\t\t" + ages.get(i)+"\n");
                i++;
                if(c.getCanRent())
                    rent++;
            }
            writer.write("Number of customers who can rent: "+rent+"\n");
            writer.write("Number of customers who cannot rent: "+(customers.size()-rent)+"\n");
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void writeSupplierToTextFile(){
        try{
            PrintWriter writer = new PrintWriter(new FileWriter("supplierOutFile.txt"));
            writer.write("============================== SUPPLIERS ====================================\n");
            writer.write("ID   	Name                	Prod Type	Description    \n");
            writer.write("=============================================================================\n");
            for(int i = 0; i < suppliers.size() - 1; i++){
                for(int r = i + 1; r < suppliers.size(); r++){
                    if(suppliers.get(i).getName().compareTo(customers.get(r).getFirstName()) > 0){
                        Supplier sup = suppliers.get(i);
                        suppliers.set(i, suppliers.get(r)); 
                        suppliers.set(r, sup);
                    }
                }
            }
             for(Supplier s: suppliers){
               if( s.getName().length() < 8)
                        writer.write(s.getStHolderId()+ "\t"+ s.getName()+ "\t\t\t" +s.getProductType()+ "\t\t\t" + s.getProductDescription()+"\n");
                    else
                        writer.write(s.getStHolderId()+ "\t"+ s.getName()+ "\t\t" + s.getProductType()+ "\t\t" + s.getProductDescription()+"\n");
            }
             writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public static void main(String[] args) {
        new Mabudah().storeDataIntoLists();
    }           

    private void displaywriteSupplierToTextFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void displaySupplierToTextFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
