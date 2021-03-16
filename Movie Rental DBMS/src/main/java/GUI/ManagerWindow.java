package GUI;

import javafx.scene.control.Button;

public class ManagerWindow {

    private Button employeOp = new Button("Employee Operation");

    public void paint(){

        employeOp.setPrefSize(150,150);
        EmployeeWindow employeeWindow = new EmployeeWindow();
        employeeWindow.gridPane.add(employeOp,2,0);
        employeeWindow.paint();

        employeOp.setOnAction(e->{
            EmployeOpWindow employeOpWindow = new EmployeOpWindow();
            employeOpWindow.paint();
        });
    }

}
