import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.sql.*;
import javax.swing.border.TitledBorder;
//
public class DBMSInterface extends JFrame {
    private JButton insertButton, updateButton, displayButton, stopButton, startButton,searchButton,controlButton,helpButton,veButton,clearButton;
    private JTextArea logTextArea, displayTextArea;
    private int count = 0;//takes count
    private int scount=0;
    private long x;
    private int check=0;
    private Connection connection;
    
    public DBMSInterface() {
        setTitle("DBMS Interface");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        //furhter edits needed
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        showImageDialog("glitch.jpg","");
        welcome();
        
        
        
        
        // Create buttons with colorful styling
        clearButton =new JButton("     Clear    ");
        clearButton.setBackground(new Color(0, 120, 215)); // Light Coral
        clearButton.setForeground(Color.white); //blue text
        veButton =new JButton(" DATABASE MANAGER  Version 3.6.2      ");
        veButton.setBackground(new Color(0, 0, 0)); // Light Coral
        veButton.setForeground(Color.white); //blue text
        controlButton=new JButton("                  Control Panel --->                 ");
        controlButton.setBackground(new Color(0, 0, 0)); // Light Coral
        controlButton.setForeground(Color.white); //blue text
        helpButton =new JButton("         Help & Support       ");
        helpButton.setBackground(new Color(0, 0, 0)); // Light Coral
        helpButton.setForeground(Color.white); //blue text
        insertButton = new JButton("                Insert           ");
        insertButton.setBackground(new Color(0, 102, 204)); // Light Coral
        insertButton.setForeground(Color.WHITE); // White text
        updateButton = new JButton("       Update       ");
        updateButton.setBackground(new Color(255,0,0)); // Light Green
        updateButton.setForeground(Color.BLACK); // Black text
        displayButton = new JButton("         Display      ");
        displayButton.setBackground(new Color(255,255,255)); // White
        displayButton.setForeground(Color.BLACK); // Black text
        stopButton = new JButton("       Stop     ");
        stopButton.setBackground(new Color(255, 0, 0)); // Light Coral
        stopButton.setForeground(Color.WHITE); // White text
        startButton = new JButton("        Start     ");
        startButton.setBackground(new Color(0,255,0)); // white
        startButton.setForeground(Color.BLACK); // Black text
        searchButton = new JButton("     Search      ");
        searchButton.setBackground(new Color(0, 120, 215)); // Blue
        searchButton.setForeground(Color.WHITE); // White text

        // Create log text area with scroll pane
        logTextArea = new JTextArea(5, 30); // Adjust the size as needed
        logTextArea.getBackground();
        logTextArea.setBackground(new Color(160, 160, 160)); // Black
        logTextArea.setForeground(Color.BLACK); // White text
        TitledBorder logBorder = BorderFactory.createTitledBorder("Log Area");
        logBorder.setTitleJustification(TitledBorder.CENTER); // Title alignment
        logTextArea.setBorder(logBorder);
        JScrollPane logScrollPane = new JScrollPane(logTextArea);
        logScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Create display text area with scroll pane
        displayTextArea = new JTextArea(10, 60); // Adjust the size as needed// Make the JTextArea non-opaque
        displayTextArea.setBackground(new Color(123,50,250)); // White
        //prev rgb 142,142,142
        displayTextArea.setForeground(Color.BLACK); // Black text
        displayTextArea.setFont(new Font("Arial", Font.PLAIN, 25));
        TitledBorder displayBorder = BorderFactory.createTitledBorder("Display Area");
        displayBorder.setTitleJustification(TitledBorder.LEFT); // Title alignment
        displayTextArea.setBorder(displayBorder);
        JScrollPane displayScrollPane = new JScrollPane(displayTextArea);
        displayScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0, 0,153)); // Blanched Almond //2 magenta
        
        buttonPanel.add(veButton);
        buttonPanel.add(controlButton);
        buttonPanel.add(startButton);
        buttonPanel.add(insertButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(helpButton);

    
        

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(new Color(240, 255, 240)); // Honeydew
        contentPane.add(buttonPanel, BorderLayout.NORTH);
        contentPane.add(logScrollPane, BorderLayout.EAST); // Adjust the location as needed
        contentPane.add(displayScrollPane, BorderLayout.CENTER);
      
        // Button listeners
        ActionListener buttonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton sourceButton = (JButton) e.getSource();
                
                count++;
                
                 if(sourceButton == clearButton){
                    displayTextArea.setText("");
                }
                
                else if (sourceButton == insertButton) {
                    logTextArea.append("Insert button clicked\n");
                    if(scount!=1){
                        err();
                        ed1();help();
                    }
                    else{
                        String options = displayOption();
                        if (options != null) {
                            // Process selected option here
                            logTextArea.append("Selected option: " + options + "\n"); 
                            switch (options) {
                                case "Employees":
                                try{
                                     insertEmployee(displayTextArea);           }
                                catch (SQLException g) {
                                    logTextArea.append("Network Traffic " + g.getMessage() + "\n");
                                    g.printStackTrace();} // Print the stack trace for debugging
                                break;
                        case "Departments":
                            try{    
                                insertDepartment(displayTextArea);}
                                catch (SQLException h) {
                                    logTextArea.append("Network Traffic " + h.getMessage() + "\n");
                                    h.printStackTrace();} // Print the stack trace for debugging
                                break;
                        case "Attendance":
                            try{
                                insertAttendance(displayTextArea);}
                                catch (SQLException i) {
                                    logTextArea.append("Network Traffic " + i.getMessage() + "\n");
                                    i.printStackTrace();} // Print the stack trace for debugging
                                break;
                        case "Leaves":
                                    try{
                                insertLeave(displayTextArea);}
                                catch (SQLException j) {
                                    logTextArea.append("Network Traffic " + j.getMessage() + "\n");
                                    j.printStackTrace();} // Print the stack trace for debugging
                                break;
                        case "Payroll":
                                    logTextArea.append("Automatically calculated\nAuthorization Needed-Sensitive data!\nTry contacting developer!");
                                    sel();
                                    break;
                        default:
                            break;                     
                               
                               
                            } 
                        }
                    }
                   // createInputBox(getName());}
                    // Insert data into database
                    // Example: insertData();}
                } 
                else if(sourceButton == searchButton) {
                        logTextArea.append("Search button clicked\n");
                        if(scount!=1){
                            err();
                            ed1();help();
                        }
                        else{
                            try{
                            find(displayTextArea);}
                                catch (SQLException w) {
                                    logTextArea.append("Network Traffic " + w.getMessage() + "\n");
                                    w.printStackTrace();} // Print the stack trace for debugging
                            }
                        
                }
                else if (sourceButton == updateButton) {
                    logTextArea.append("Update button clicked\n");
                    if(scount!=1){
                        err();ed1();help();
                    }
                    else{
                        String opt= displayOpt();
                        switch (opt) {
                            case "Employees":
                            try{
                                updateEmployee(displayTextArea);}
                            catch (SQLException p) {
                                    logTextArea.append("Network Traffic " + p.getMessage() + "\n");
                                    p.printStackTrace();} // Print the stack trace for debugging
                    
                                break;
                            case "Departments":
                                try{
                                    updateDepartment(displayTextArea);}
                                catch (SQLException q) {
                                    logTextArea.append("Network Traffic " + q.getMessage() + "\n");
                                    q.printStackTrace();} // Print the stack trace for debugging
                                break;
                            case "Attendance":
                                try{
                                    updateAttendance(displayTextArea);}
                                catch (SQLException r) {
                                    logTextArea.append("Network Traffic " + r.getMessage() + "\n");
                                    r.printStackTrace();} // Print the stack trace for debugging
                                break;
                            case "Leaves":
                                try{
                                    update_leave_record(displayTextArea);}
                                catch (SQLException s) {
                                    logTextArea.append("Network Traffic " + s.getMessage() + "\n");
                                    s.printStackTrace();} // Print the stack trace for debugging
                                break;
                            case "Payroll":
                                try{
                                    update_payroll(displayTextArea);}
                                catch (SQLException t) {
                                    logTextArea.append("Network Traffic " + t.getMessage() + "\n");
                                    t.printStackTrace();} // Print the stack trace for debugging
                                break;
                            default:
                                break;
                        }
                    }
                    // Update data in database
                    // Example: updateData();
                } else if (sourceButton == displayButton) {
                    logTextArea.append("Display button clicked\n");
                    if(scount!=1){
                        err();ed1();help();
                    }
                    else{
                        String option = displayOptions();
                        if (option != null) {
                            // Process selected option here
                            logTextArea.append("Selected option: " + option + "\n");
                            switch (option) {
                                case "All":
                                try{
                                    fetchEmployeeData(displayTextArea);
                                    fetchDepartmentData(displayTextArea);
                                    fetchAttendanceData(displayTextArea);
                                    fetchLeaveRecordData(displayTextArea);
                                    fetchPayrollData(displayTextArea);}
                                    catch (SQLException f) {
                                        logTextArea.append("Network Traffic " + f.getMessage() + "\n");
                                        f.printStackTrace();} // Print the stack trace for debugging

                                    break;
                                case "Employees":
                                        try{
                                        fetchEmployeeData(displayTextArea);}
                                        catch (SQLException g) {
                                            logTextArea.append("Network Traffic " + g.getMessage() + "\n");
                                            g.printStackTrace();} // Print the stack trace for debugging
                                        break;
                                case "Departments":
                                    try{    
                                        fetchDepartmentData(displayTextArea);}
                                        catch (SQLException h) {
                                            logTextArea.append("Network Traffic " + h.getMessage() + "\n");
                                            h.printStackTrace();} // Print the stack trace for debugging
                                        break;
                                case "Attendance":
                                    try{
                                        fetchAttendanceData(displayTextArea);}
                                        catch (SQLException i) {
                                            logTextArea.append("Network Traffic " + i.getMessage() + "\n");
                                            i.printStackTrace();} // Print the stack trace for debugging
                                        break;
                                case "Leaves":
                                            try{
                                        fetchLeaveRecordData(displayTextArea);}
                                        catch (SQLException j) {
                                            logTextArea.append("Network Traffic " + j.getMessage() + "\n");
                                            j.printStackTrace();} // Print the stack trace for debugging
                                        break;
                                case "Payroll":
                                            try{
                                                fetchPayrollData(displayTextArea);}
                                            catch (SQLException k) {
                                                    logTextArea.append("Network Traffic " + k.getMessage() + "\n");
                                                    k.printStackTrace();} // Print the stack trace for debugging
                                        break;
                                default:
                                    break;
                            }
                        }
                    }
                    // Fetch data from database and display in displayTextArea
                    // Example: displayData();
                } else if (sourceButton == stopButton) {
                    try {
                        if(scount==1){
                        connection.close();
                        scount--;dc();
                        tim(x);
                        logTextArea.append("Disconnected\n");}
                        else{
                            ed();help();
                            logTextArea.append("No existing connections!\n");
                        }
                    } catch (SQLException ex) {
                        logTextArea.append("Error closing connection: " + ex.getMessage() + "\n");
                    }
                } else if (sourceButton == startButton) {
                    if(scount!=1){
                    connectToDatabase();
                    x = System.currentTimeMillis();
                    scount++;}
                    else{
                        logTextArea.append("Connection Exists!\n");
                        ed2();
                    }

                }
                else if(sourceButton == helpButton){
                    browser();
                    logTextArea.append("Help Request Initiated\n");
                }
                
            }
        };
        insertButton.addActionListener(buttonListener);
        updateButton.addActionListener(buttonListener);
        displayButton.addActionListener(buttonListener);
        stopButton.addActionListener(buttonListener);
        startButton.addActionListener(buttonListener);
        searchButton.addActionListener(buttonListener);
        helpButton.addActionListener(buttonListener);
        clearButton.addActionListener(buttonListener);
    }
    // Method to connect to the database
    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "query");
            logTextArea.append("Connected to database.\n");
            displayTextArea.append("Connected to Database Project\nConnection ID:"+ connection+"\n");
            feed();
            System.out.println(connection);
        } catch (SQLException e) {
            logTextArea.append("Failed to connect to database: " + e.getMessage() + "\n");
        }
    }
   
    // Methods to interact with the database
    // Example methods:
    /*
    
    private void insertData() {
        // Implement insertion logic here
    }

    private void updateData() {
        // Implement update logic here
    }

    private void displayData() {
        // Implement display logic here
    }
    */
//update
private void updateEmployee(JTextArea logTextArea) throws SQLException{
    try{
        int e_id = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee ID:"));
            String name = JOptionPane.showInputDialog("Enter Employee Name:");
            int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee Age:"));
            String phone = JOptionPane.showInputDialog("Enter Employee Phone:");
            String gender = JOptionPane.showInputDialog("Enter Employee Gender:");
            String sql = "{CALL update_employee(?, ?, ?, ?, ?)}";
            CallableStatement stmt = connection.prepareCall(sql);
            stmt.setInt(1, e_id);
            stmt.setString(2, name);
            stmt.setInt(3, age);
            stmt.setString(4, phone);
            stmt.setString(5, gender);
            stmt.execute();
            logTextArea.append("Stored procedure update_employee executed successfully.");
        //function call dev st 2 add here serach e_id

    }
    catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid input format. Please enter valid numbers for Employee ID and Age.");}}
private void updateDepartment(JTextArea logTextArea) throws SQLException{
            try{
                int d_id = Integer.parseInt(JOptionPane.showInputDialog("Enter Department ID:"));
            int e_id = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee ID:"));
            String dname = JOptionPane.showInputDialog("Enter Department Name:");
            String post = JOptionPane.showInputDialog("Enter Post:");
            int salary = Integer.parseInt(JOptionPane.showInputDialog("Enter Salary:"));
                String sql="{CALL update_department(?, ?, ?, ?, ?)}";
                CallableStatement stmt = connection.prepareCall(sql);
        
        
            stmt.setInt(1, d_id);
            stmt.setInt(2, e_id);
            stmt.setString(3, dname);
            stmt.setString(4, post);
            stmt.setDouble(5, salary);
            stmt.execute();
            logTextArea.append("Stored procedure update_department executed successfully.");
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input format. Please enter valid numbers for Employee ID and Age.");
            }
        }
        //3
private void updateAttendance(JTextArea logTextArea) throws SQLException{
            try{
                int a_id = Integer.parseInt(JOptionPane.showInputDialog("Enter Attendance Id:"));
                    int e_id = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee ID:"));
                    Date date = Date.valueOf(JOptionPane.showInputDialog("Enter date (yyyy-MM-dd):"));
                    Time punch_in = Time.valueOf(JOptionPane.showInputDialog("Enter punch_in (HH:mm:ss):"));
                    Time punch_out = Time.valueOf(JOptionPane.showInputDialog("Enter punch_out (HH:mm:ss):"));
                    double hours = Double.parseDouble(JOptionPane.showInputDialog("Enter hours:"));
                    double deduction = Double.parseDouble(JOptionPane.showInputDialog("Enter deduction:"));
        
                    String sql="{CALL update_attendance(?, ?, ?, ?, ?, ?, ?)}";
                    CallableStatement stmt = connection.prepareCall(sql);
                    stmt.setInt(1, a_id);
                    stmt.setInt(2, e_id);
                    stmt.setDate(3, date);
                    stmt.setTime(4, punch_in);
                    stmt.setTime(5, punch_out);
                    stmt.setDouble(6, hours);
                    stmt.setDouble(7, deduction);
                    stmt.execute();
                    logTextArea.append("Stored procedure update_attendance executed successfully.");
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input format. Please enter valid numbers for Employee ID and attendance id.");
            }}
        //4
private void update_leave_record(JTextArea logTextArea) throws SQLException{
            try{
                int l_id = Integer.parseInt(JOptionPane.showInputDialog("Enter Leave Id:"));
                    int e_id = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee ID:"));
                    int total_days = Integer.parseInt(JOptionPane.showInputDialog("Enter total_days:"));
                    String leave_type = JOptionPane.showInputDialog("Enter leave type:");
                    double deduction = Double.parseDouble(JOptionPane.showInputDialog("Enter deduction:"));
                    String sql="{CALL update_leave_record(?, ?, ?, ?, ?)}";
                    CallableStatement stmt=connection.prepareCall(sql);
                    stmt.setInt(1, l_id);
                    stmt.setInt(2, e_id);
                    stmt.setInt(3, total_days);
                    stmt.setString(4, leave_type);
                    stmt.setDouble(5, deduction);
                    stmt.execute();
                    logTextArea.append("Stored procedure update_leave executed successfully.");
        
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input format. Please enter valid numbers for Employee ID and attendance id.");}
            }
//5  
private void update_payroll(JTextArea logTextArea) throws SQLException{
    try{
        int p_id = Integer.parseInt(JOptionPane.showInputDialog("Enter payroll ID:"));
            int e_id = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee ID:"));
            double salary = Double.parseDouble(JOptionPane.showInputDialog("Enter deduction:"));
            double deduction = Double.parseDouble(JOptionPane.showInputDialog("Enter deduction:"));
            double netpay = Double.parseDouble(JOptionPane.showInputDialog("Enter Net Pay:"));
            String sql="{CALL update_payroll(?, ?, ?, ?, ?)}";
            CallableStatement stmt=connection.prepareCall(sql);
            stmt.setInt(1, p_id);
            stmt.setInt(2, e_id);
            stmt.setDouble(3, salary);
            stmt.setDouble(4, deduction);
            stmt.setDouble(5, netpay);
            stmt.execute();
            logTextArea.append("Stored procedure update_payroll executed successfully.");

    }
    catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid input format. Please enter valid numbers for Employee ID and attendance .");}
    }






//new method
/* 
private void createInputBox(String action) {
    JFrame frame = new JFrame(action + " Input"); // Dialog box title
    JPanel panel = new JPanel();
    JLabel label = new JLabel(action + " Data:");
    JTextField textField = new JTextField(20); // Text field for input
    panel.add(label);
    panel.add(textField);
    JButton confirmButton = new JButton("Confirm");
    confirmButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String inputData = textField.getText(); // Get input data
            logTextArea.append(action + " data: " + inputData + "\n"); // Log input data
            frame.dispose(); // Close the dialog box
        }
    });
    panel.add(confirmButton);
    frame.add(panel);
    frame.setSize(300, 150);
    frame.setVisible(true);
}*/
//calculate time
private void tim(long x) {
    long y = System.currentTimeMillis();
    long t = y - x;
    long r = t / (1000 * 60);
    displayTextArea.append("\nLast Uptime:"+r+" minutes\n");
}
//
void err(){
    logTextArea.append("Database is offline!\n");
    logTextArea.append("Press Start to initiate a Connection\n");
}
void dis() {
    try {
        fetchLeaveRecordData(displayTextArea);
        Statement statement = connection.createStatement();
        // Example SELECT query to fetch all data from the table
        String selectQuery = "select * from employee";
        ResultSet resultSet = statement.executeQuery(selectQuery);
        // Processing the result set
        while (resultSet.next()) {
            // Accessing columns by name
            int eId = resultSet.getInt("e_id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            String gender = resultSet.getString("gender");
            String phone = resultSet.getString("phone");
            // Do something with the retrieved data
            displayTextArea.append("Employee ID: " + eId+'\n');
            displayTextArea.append("Name: " + name+'\n');
            displayTextArea.append("Age: " + age+'\n');
            displayTextArea.append("Gender: " + gender+'\n');
            displayTextArea.append("Phone: " + phone+'\n');
            displayTextArea.append("-----------------------------------------\n");
        }
        // Close the result set and statement
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        // Handle any SQL exceptions that occur during execution
        logTextArea.append("Error fetching data: " + e.getMessage() + "\n");
    }
}

private  void fetchEmployeeData(JTextArea displayTextArea) throws SQLException {
    
    Statement statement = connection.createStatement();
    String query = "SELECT * FROM employee";
    ResultSet resultSet = statement.executeQuery(query);
    // Processing the result set
    while (resultSet.next()) {
        int eId = resultSet.getInt("e_id");
        String name = resultSet.getString("name");
        int age = resultSet.getInt("age");
        String gender = resultSet.getString("gender");
        String phone = resultSet.getString("phone");
        // Appending the retrieved data to the JTextArea
        displayTextArea.append("Employee ID: " + eId + "\n");
        displayTextArea.append("Name: " + name + "\n");
        displayTextArea.append("Age: " + age + "\n");
        displayTextArea.append("Gender: " + gender + "\n");
        displayTextArea.append("Phone: " + phone + "\n");
        displayTextArea.append("-----------------------------------------\n");
    }
}

//
private void fetchDepartmentData(JTextArea displayTextArea) throws SQLException {
    String query = "SELECT * FROM department";
    Statement statement=connection.createStatement();
    ResultSet resultSet = statement.executeQuery(query);

    // Processing the result set
    while (resultSet.next()) {
        int dId = resultSet.getInt("d_id");
        int eId = resultSet.getInt("e_id");
        String dName = resultSet.getString("d_name");
        String post = resultSet.getString("post");
        double salary = resultSet.getDouble("salary");

        // Appending the retrieved data to the JTextArea
        displayTextArea.append("Department ID: " + dId + "\n");
        displayTextArea.append("Employee ID: " + eId + "\n");
        displayTextArea.append("Department Name: " + dName + "\n");
        displayTextArea.append("Post: " + post + "\n");
        displayTextArea.append("Salary: " + salary + "\n");
        displayTextArea.append("-----------------------------------------\n");
    }
}
//3
private void fetchAttendanceData(JTextArea displayTextArea) throws SQLException {
    String query = "SELECT * FROM attendance";
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(query);
    // Processing the result set
    while (resultSet.next()) {
        int aId = resultSet.getInt("a_id");
        int eId = resultSet.getInt("e_id");
        Date date = resultSet.getDate("date");
        Time punchIn = resultSet.getTime("punch_in");
        Time punchOut = resultSet.getTime("punch_out");
        double hours = resultSet.getDouble("hours");
        double deduction = resultSet.getDouble("deduction");
        // Appending the retrieved data to the JTextArea
        displayTextArea.append("Attendance ID: " + aId + "\n");
        displayTextArea.append("Employee ID: " + eId + "\n");
        displayTextArea.append("Date: " + date + "\n");
        displayTextArea.append("Punch In: " + punchIn + "\n");
        displayTextArea.append("Punch Out: " + punchOut + "\n");
        displayTextArea.append("Hours: " + hours + "\n");
        displayTextArea.append("Deduction: " + deduction + "\n");
        displayTextArea.append("-----------------------------------------\n");
    }
}
//4
private  void fetchLeaveRecordData(JTextArea displayTextArea) throws SQLException {
    String query = "SELECT * FROM leave_record";
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(query);
    // Processing the result set
    while (resultSet.next()) {
        int lId = resultSet.getInt("l_id");
        int eId = resultSet.getInt("e_id");
        int totalLeaves = resultSet.getInt("total_no_of_leaves");
        String leaveType = resultSet.getString("leave_type");
        double deduction = resultSet.getDouble("deduction");
        // Appending the retrieved data to the JTextArea
        displayTextArea.append("Leave Record ID: " + lId + "\n");
        displayTextArea.append("Employee ID: " + eId + "\n");
        displayTextArea.append("Total Leaves: " + totalLeaves + "\n");
        displayTextArea.append("Leave Type: " + leaveType + "\n");
        displayTextArea.append("Deduction: " + deduction + "\n");
        displayTextArea.append("-----------------------------------------\n");
    }
}
//5
private void fetchPayrollData(JTextArea displayTextArea) throws SQLException {
    String query = "SELECT * FROM payroll";
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(query);
    // Processing the result set
    while (resultSet.next()) {
        int pId = resultSet.getInt("p_id");
        int eId = resultSet.getInt("e_id");
        double salary = resultSet.getDouble("salary");
        double totalDeduction = resultSet.getDouble("total_deduction");
        double netPay = resultSet.getDouble("net_pay");
        // Appending the retrieved data to the JTextArea
        displayTextArea.append("Payroll ID: " + pId + "\n");
        displayTextArea.append("Employee ID: " + eId + "\n");
        displayTextArea.append("Salary: " + salary + "\n");
        displayTextArea.append("Total Deduction: " + totalDeduction + "\n");
        displayTextArea.append("Net Pay: " + netPay + "\n");
        displayTextArea.append("-----------------------------------------\n");
    }
}
//insert functions
private void insertEmployee(JTextArea logTextArea) throws SQLException{
    try{
        int e_id = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee ID:"));
            String name = JOptionPane.showInputDialog("Enter Employee Name:");
            int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee Age:"));
            String phone = JOptionPane.showInputDialog("Enter Employee Phone:");
            String gender = JOptionPane.showInputDialog("Enter Employee Gender:");
            String sql = "{CALL add_employee(?, ?, ?, ?, ?)}";
            CallableStatement stmt = connection.prepareCall(sql);
            stmt.setInt(1, e_id);
            stmt.setString(2, name);
            stmt.setInt(3, age);
            stmt.setString(4, phone);
            stmt.setString(5, gender);
            stmt.execute();
            logTextArea.append("Stored procedure add_employee executed successfully.");
        //function call dev st 2 add here serach e_id

    }
    catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid input format. Please enter valid numbers for Employee ID and Age.");
    }
}
//2
private void insertDepartment(JTextArea logTextArea) throws SQLException{
    try{
        int d_id = Integer.parseInt(JOptionPane.showInputDialog("Enter Department ID:"));
    int e_id = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee ID:"));
    String dname = JOptionPane.showInputDialog("Enter Department Name:");
    String post = JOptionPane.showInputDialog("Enter Post:");
    int salary = Integer.parseInt(JOptionPane.showInputDialog("Enter Salary:"));
        String sql="{CALL add_department(?, ?, ?, ?, ?)}";
        CallableStatement stmt = connection.prepareCall(sql);


    stmt.setInt(1, d_id);
    stmt.setInt(2, e_id);
    stmt.setString(3, dname);
    stmt.setString(4, post);
    stmt.setDouble(5, salary);
    stmt.execute();
    logTextArea.append("Stored procedure add_department executed successfully.");
    }
    catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid input format. Please enter valid numbers for Employee ID and Age.");
    }
}
//3
private void insertAttendance(JTextArea logTextArea) throws SQLException{
    try{
        int a_id = Integer.parseInt(JOptionPane.showInputDialog("Enter Attendance Id:"));
            int e_id = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee ID:"));
            Date date = Date.valueOf(JOptionPane.showInputDialog("Enter date (yyyy-MM-dd):"));
            Time punch_in = Time.valueOf(JOptionPane.showInputDialog("Enter punch_in (HH:mm:ss):"));
            Time punch_out = Time.valueOf(JOptionPane.showInputDialog("Enter punch_out (HH:mm:ss):"));
            double hours = Double.parseDouble(JOptionPane.showInputDialog("Enter hours:"));
            double deduction = Double.parseDouble(JOptionPane.showInputDialog("Enter deduction:"));

            String sql="{CALL add_attendance(?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement stmt = connection.prepareCall(sql);
            stmt.setInt(1, a_id);
            stmt.setInt(2, e_id);
            stmt.setDate(3, date);
            stmt.setTime(4, punch_in);
            stmt.setTime(5, punch_out);
            stmt.setDouble(6, hours);
            stmt.setDouble(7, deduction);
            stmt.execute();
            logTextArea.append("Stored procedure add_attendance executed successfully.");
    }
    catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid input format. Please enter valid numbers for Employee ID and attendance id.");
    }}
//4
private void insertLeave(JTextArea logTextArea) throws SQLException{
    try{
        int l_id = Integer.parseInt(JOptionPane.showInputDialog("Enter Leave Id:"));
            int e_id = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee ID:"));
            int total_days = Integer.parseInt(JOptionPane.showInputDialog("Enter total_days:"));
            String leave_type = JOptionPane.showInputDialog("Enter leave type:");
            double deduction = Double.parseDouble(JOptionPane.showInputDialog("Enter deduction:"));
            String sql="{CALL add_leave(?, ?, ?, ?, ?)}";
            CallableStatement stmt=connection.prepareCall(sql);
            stmt.setInt(1, l_id);
            stmt.setInt(2, e_id);
            stmt.setInt(3, total_days);
            stmt.setString(4, leave_type);
            stmt.setDouble(5, deduction);
            stmt.execute();
            logTextArea.append("Stored procedure add_leave executed successfully.");

    }
    catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid input format. Please enter valid numbers for Employee ID and attendance id.");}

}
//searching all tables
private void find(JTextArea dispTextArea) throws SQLException{
    try{
        int em_id = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee ID:"));
        String sql="SELECT e.e_id, e.name, e.age, e.gender, e.phone, " +
        "d.d_id, d.e_id, d.d_name, d.post, d.salary, " +
        "a.a_id, a.e_id, a.date, a.punch_in, a.punch_out, a.hours, a.deduction, " +
        "p.p_id, p.e_id, p.salary, p.total_deduction, p.net_pay " +
        "FROM employee e " +
        "INNER JOIN department d ON e.e_id = d.e_id " +
        "INNER JOIN attendance a ON e.e_id = a.e_id " +
        "INNER JOIN payroll p ON e.e_id = p.e_id " +
        "WHERE e.e_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, em_id);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                logTextArea.append("Details collected\nSending to Display Panel");
                // Retrieve data from the ResultSet
                int e_id = rs.getInt("e_id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String phone = rs.getString("phone");
                int d_id = rs.getInt("d_id");
                String d_name = rs.getString("d_name");
                String post = rs.getString("post");
                double salary = rs.getDouble("salary");
                int a_id = rs.getInt("a_id");
                Date date = rs.getDate("date");
                Time punch_in = rs.getTime("punch_in");
                Time punch_out = rs.getTime("punch_out");
                double hours = rs.getDouble("hours");
                double deduction = rs.getDouble("deduction");
                int p_id = rs.getInt("p_id");
                double net_pay = rs.getDouble("net_pay");

                // Append the retrieved data to the JTextArea
                displayTextArea.append("Employee ID: " + e_id + "\n");
                displayTextArea.append("Name: " + name + "\n");
                displayTextArea.append("Age: " + age + "\n");
                displayTextArea.append("Gender: " + gender + "\n");
                displayTextArea.append("Phone: " + phone + "\n");
                displayTextArea.append("Department ID: " + d_id + "\n");
                displayTextArea.append("Department Name: " + d_name + "\n");
                displayTextArea.append("Post: " + post + "\n");
                displayTextArea.append("Salary: " + salary + "\n");
                displayTextArea.append("Attendance ID: " + a_id + "\n");
                displayTextArea.append("Date: " + date + "\n");
                displayTextArea.append("Punch In: " + punch_in + "\n");
                displayTextArea.append("Punch Out: " + punch_out + "\n");
                displayTextArea.append("Hours: " + hours + "\n");
                displayTextArea.append("Deduction: " + deduction + "\n");
                displayTextArea.append("Payslip ID: " + p_id + "\n");
                displayTextArea.append("Net Pay: " + net_pay + "\n\n");
                check++;}
                if(check==0){
                    logTextArea.append("No Matches Found!\n");
                    mat();
                
            }
                else{
                    check=0;
                }

    }
}
catch (SQLException v) {
    logTextArea.append("Network Traffic " + v.getMessage() + "\n");
    v.printStackTrace();} // Print the stack trace for debugging
}
//mdsply 1
private String displayOptions() {
    String[] options = {"All","Employees", "Departments", "Attendance", "Leaves", "Payroll"};
    return (String) JOptionPane.showInputDialog(this, "Select an option:", "Options", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
}
//mdsply insert
private String displayOption() {
    String[] option = {"Employees", "Departments", "Attendance", "Leaves","Payroll"};
    return (String) JOptionPane.showInputDialog(this, "Select a table:", "Options", JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
}
private String displayOpt() {
    String[] option = {"Employees", "Departments", "Attendance", "Leaves","Payroll"};
    return (String) JOptionPane.showInputDialog(this, "Select a table:", "Options", JOptionPane.QUESTION_MESSAGE, null, option,option[0]);
}
//mdsply 2
/* 
private void InputBox(String option) {
    // Implement creation of input box based on selected option
    JOptionPane.showMessageDialog(this, "Input box for option: " + option, "Input Box", JOptionPane.INFORMATION_MESSAGE);
}*/
//error dialogue
private String ed() {
    JOptionPane.showMessageDialog(null,"Error occured !\nNo Existing Connections!\n Error code:406");
    return "Error occured !\n code:409" ;
}
private String sel() {
    JOptionPane.showMessageDialog(null,"Authorization Required!\nError occured !\nInvalid Selection!\n Error code:369");
    return "Error occured !\n code:409" ;
}
private String ed1() {
    JOptionPane.showMessageDialog(null,"Error occured !\nDatabase offline!\nError code:409");
    return "Error occured !\n code:409" ;
}
private String welcome() {
    JOptionPane.showMessageDialog(null,"WELCOME USER\nLoading Resources...\nLoad Successfull!\nPress Ok to Launch");
    return "Error occured !\n code:409" ;
}
private String mat() {
    JOptionPane.showMessageDialog(null,"No Matches Found!");
    return "Error occured !\n code:409" ;}
private String feed() {
    JOptionPane.showMessageDialog(null,"Connection Successfull!\nDatabase in use:Project\nConn ID:"+connection+"\nLast login:27/04/2024 20:28:39");
    return "Error occured !\n code:409" ;
}
private String dc() {
    JOptionPane.showMessageDialog(null,"Disconnected!\nDatabase Status:Offline\nTotal Operations done:"+count);
    return "Error occured !\n code:409" ;
}



private String ed2() {
    JOptionPane.showMessageDialog(null,"Error occured !\nExisting Connections!\nError code:499");
    return "Error occured !\n code:409" ;
}
private String help() {
    JOptionPane.showMessageDialog(null,"Help\nPress start button to initiate a connection");
    return "Error occured !\n code:409" ;
}

//browser plugin
private void browser(){
  

        try {
            String url = "https://linktr.ee/itz_me_js_007"; // Replace with the URL of the website you want to open
            Desktop desktop = Desktop.getDesktop();
            
            // Check if desktop is supported and if URI is valid
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(new URI(url));
            } else {
                System.out.println("Desktop browsing is not supported.");
                // Alternatively, you can try opening the URL using the Process class:
                // Runtime.getRuntime().exec("cmd.exe /c start " + url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
private void showImageDialog(String imagePath, String title) {
    ImageIcon icon = new ImageIcon(imagePath);

    // Create a JPanel to hold the image
    JPanel panel = new JPanel() {

        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    };
    panel.setPreferredSize(new Dimension(900,500)); // Set panel size to match image size

    // Show the JOptionPane with the custom panel
    JOptionPane.showMessageDialog(null, panel, title, JOptionPane.PLAIN_MESSAGE);{
}
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DBMSInterface frame = new DBMSInterface();
                frame.setVisible(true);
            }
        });
    }
}
