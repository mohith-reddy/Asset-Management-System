package edu.ufl.SangavaramM;


import edu.ufl.SangavaramM.Inventorydblib.dbCode;

import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        dbCode o = new dbCode();

        Scanner sc = new Scanner(System.in);


        //AssignComputer asC = new AssignComputer();


        System.out.println("l to list inventory, a to assign, r to reassign, q to quit");

        String choice = sc.nextLine();

        while (!choice.toLowerCase().equals("q")) {

            switch (choice.toLowerCase()) {
                /*case "q":
                    System.out.println("Exiting program...");
                    break; */


                case "l":
                    System.out.println("Enter employee id");

                    String empId = sc.nextLine();

                    List<String> invList = o.listInventory(empId);

                    if (invList.size() == 0) {
                        System.out.println("No assignments for this employee");
                    } else {

                        for (int i = 0; i < invList.size(); i++) {

                            System.out.println(invList.get(i));
                        }
                    }
                    break;

                case "a":

                    System.out.println("Enter employee id");

                    empId = sc.nextLine();

                    System.out.println("Enter office number");
                    String officeno = sc.nextLine();

                    System.out.println("Enter computer id");
                    String computerid = sc.nextLine();

                    String output = o.assignEquipment(empId, officeno, computerid);

                    System.out.print(output + "\n");

                    break;

                case "r":

                    System.out.println("Enter employee id");

                    empId = sc.nextLine();

                    System.out.println("Enter office number");
                    officeno = sc.nextLine();

                    System.out.println("Enter computer id");
                    computerid = sc.nextLine();

                    output = o.reassignEquipment(empId, officeno, computerid);

                    System.out.print(output + "\n");

                    break;

                default:

                    System.out.println("Please enter valid choice");

            }

            System.out.println("l to list inventory, a to assign, r to reassign, q to quit");

            choice = sc.nextLine();

        }

        System.out.println("Exiting program...");


    }

}

