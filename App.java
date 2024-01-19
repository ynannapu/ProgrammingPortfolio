package com.mycompany.finalproject;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
//import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.CheckBox;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.Random;

import java.io.*;


/******************************************************************************************
* Final Project CIS 290 JAVA Patient Intake Form
* Author: Yashu Nannapuraju
* Project Purpose: Program to display form, create file for new patients 
* Input: Patient name, ID, DOB, address, etc
* Desired Output: File with accurate information. Result label should also display correct message and form should be cleared after submit button is pressed
* Variables and Classes: There is one class, with the main method. 
* Formulas: Formula to generate unique patient ID involves generating random int
* Testing: When the program is run with inputs, file is handled appropriately.
* April 25, 2023
**********************************************************************************************/
public class App extends Application {
    //All my fields to be displayed on the form
    Label nameLabel;
    private TextField tfName = new TextField();
    Label DOBLabel;
    private TextField tfDOB = new TextField();
    Label PhoneNumLabel;
    private TextField tfPhoneNum = new TextField();
    Label addressLabel;
    private TextField tfAddress = new TextField();
    Label medicationsLabel;
    private TextField tfMedications = new TextField();
    Label chiefComplaintLabel;
    private TextField tfchiefComplaint= new TextField();
    Label resultLabel;
    
    //history chronic diseases label and checkboxes 
    Label chronicdiseasesLabel;
    CheckBox diabetesCheckBox;
    CheckBox thyroidCheckBox;
    CheckBox bloodPressureCheckBox;
    CheckBox highCholesterolCheckBox;
    
        public static void main(String[] args) {
        launch(args);
    }

    @Override 
    public void start(Stage primaryStage) {
        // Create the textfields,labels and checkboxes
       nameLabel=new Label ("Enter Name: ");
       tfName= new TextField("");
       
       
       DOBLabel= new Label ("Enter Date of Birth(MMDDYYYY): ");
       tfDOB= new TextField(""); 
       
       PhoneNumLabel= new Label("Enter phone number: ");
       tfPhoneNum= new TextField(""); 
       
       addressLabel = new Label ("Enter address: ");
       tfAddress= new TextField(""); 
       
       medicationsLabel=new Label ("List Medications (with dosage if possible): ");
       tfMedications= new TextField ("");
       
       chronicdiseasesLabel= new Label ("Indicate which chronic illnesses you have ");
       diabetesCheckBox = new CheckBox ("Diabetes");
       thyroidCheckBox = new CheckBox ("Thyroid Disorder");
       bloodPressureCheckBox= new CheckBox ("High Blood Pressure");
       highCholesterolCheckBox = new  CheckBox ("High Cholesterol");
       chiefComplaintLabel= new Label ("Please briefly describe your main concerns and date your response: ");
       tfchiefComplaint= new TextField("");
       resultLabel=new Label("");
       
        
       //Create the buttons 
      Button btSubmitForm = new Button("Submit Form");
      Button btClear = new Button("Close");
      
  
        //Put the Labels+ TextFields in a VBox
        VBox tfVBox=new VBox (10,nameLabel,tfName, DOBLabel,tfDOB, PhoneNumLabel,tfPhoneNum, addressLabel,tfAddress, medicationsLabel,tfMedications);
      
      //Put the Checkboxes in another VBox
        VBox chronicVBox= new VBox(10, chronicdiseasesLabel,diabetesCheckBox,thyroidCheckBox, bloodPressureCheckBox,highCholesterolCheckBox, chiefComplaintLabel, tfchiefComplaint, resultLabel);
        
       //Create another VBox to use as the root node
       VBox mainVBox= new VBox(10, tfVBox, chronicVBox, btSubmitForm, btClear);
       
       //Set the main VBox's alignment to center
       mainVBox.setAlignment(Pos.CENTER);
       
       //Set the main VBox's padding to 10 pixels
       mainVBox.setPadding(new Insets(10));

        // Process events
        btSubmitForm.setOnAction(e -> StoreNewPatient());

        btClear.setOnAction(e -> {
            System.exit(0);
        });   

        // Create a scene and add it to the stage
        Scene scene = new Scene(mainVBox);
        primaryStage.setTitle("Patient Intake Form"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    public void StoreNewPatient() {         
        //We need to generate a unique ID for them
        Random rand= new Random();
        int x= rand.nextInt(10000);
        String patientID= x + tfDOB.getText();
        
        PrintWriter fw = null;

        try {
            fw = new PrintWriter(patientID + ".txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Patient Information Form");
            bw.write("\nPatient Name: " + tfName.getText());
            bw.write("\nPatient ID: " + patientID);
            bw.write("\nDOB: " + tfDOB.getText());
            bw.write("\nPhone Number: " + tfPhoneNum.getText());
            bw.write("\nAddress: " + tfAddress.getText());
            bw.write("\nMedications: " + tfDOB.getText());
            bw.write("\nChronic Illnesses: " );
            if (diabetesCheckBox.isSelected())
            {
                bw.write("-Diabetes\n");
            }
            if (thyroidCheckBox.isSelected())
            {
                bw.write("-Thyroid Disorder\n");
            }
            if (bloodPressureCheckBox.isSelected())
            {
                bw.write("-High blood pressure\n");
            }
            if (highCholesterolCheckBox.isSelected())
            {
                bw.write("-High Cholesterol\n");
            }
            bw.write("\nChief Complaint: " + tfchiefComplaint.getText());
            
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //After file is created, we want to clear all the fields and display a message to the patient
            tfName.clear();
            tfDOB.clear();
            tfPhoneNum.clear();
            tfAddress.clear();
            tfMedications.clear();
            diabetesCheckBox.setSelected(false);
            thyroidCheckBox.setSelected(false);
            bloodPressureCheckBox.setSelected(false);
            highCholesterolCheckBox.setSelected(false);
            tfchiefComplaint.clear();
            
            resultLabel=new Label("Thank you for completing this form");
    }

                
                            }
                


