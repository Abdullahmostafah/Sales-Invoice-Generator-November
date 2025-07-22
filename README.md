# Sales Invoice Generator

## Overview
The Sales Invoice Generator is a Java-based desktop application designed to create, manage, and store sales invoices. It provides a user-friendly GUI built with JFrame and JTable, supports CSV file storage, and includes robust exception handling for file operations. The project is structured into modular packages and follows proper code styling conventions for maintainability and readability.

## Features
- **Invoice Creation and Management**: Create and manage sales invoices with details such as invoice number, date, customer information, and itemized products.
- **GUI**: Interactive interface using JFrame and JTable for displaying and editing invoice data.
- **Data Storage**: Stores invoice data in CSV files with a defined format.
- **Exception Handling**: Handles file reading and writing errors with descriptive messages for invalid file formats, missing files, or incorrect paths.

## Project Structure
The project is organized into four main tasks, each corresponding to specific functionalities and packages.

### 1. Code Skeleton and Classes/Objects
The application is designed with object-oriented principles, using classes to represent key entities:
- **Invoice**: Represents an invoice with attributes like invoice number, date, customer name, and a list of items.
- **Item**: Represents an item in an invoice with attributes like item name, quantity, and price.
- **Customer**: Stores customer details such as name and contact information.
- **FileHandler**: Manages reading from and writing to CSV files.

### 2. Package Structure
The code is organized into the following packages:
- `com.invoice.model`: Contains entity classes (`Invoice`, `Item`, `Customer`).
- `com.invoice.file`: Handles file I/O operations (`FileHandler`).
- `com.invoice.gui`: Contains GUI-related classes (`MainFrame`, `InvoiceTableModel`).
- `com.invoice.exception`: Custom exception classes for handling file and data errors.

### 3. Data Storage & I/O
- **File Format**: Invoices are stored in CSV files with the following structure:
  ```
  invoice_number,date,customer_name,item_name,quantity,price
  1001,2025-07-22,John Doe,Product A,2,50.00
  1001,2025-07-22,John Doe,Product B,1,30.00
  ```
- **Storage**: Data is saved to and read from CSV files (`invoices.csv`) in the project directory.
- **File Operations**: The `FileHandler` class manages reading and writing operations, ensuring data consistency.

### 4. GUI
- **Framework**: Built using Java Swing with `JFrame` as the main window and `JTable` for displaying invoice data.
- **Components**:
  - `JTable`: Displays invoice details in a tabular format.
  - Buttons for actions like creating, saving, and loading invoices.
  - Text fields and date pickers for inputting invoice details.
- **Event Handling**: Handles multiple action events, such as button clicks for saving invoices or loading data from files.

### 5. Exception Handling
The application includes robust error handling for file operations:
- **Reading Exceptions**:
  - **Wrong File Format**: Displays "Error: Invalid CSV file format" if the file structure does not match the expected format.
  - **Wrong Date Format**: Displays "Error: Invalid date format in file" if dates are not in `YYYY-MM-DD` format.
  - **File Not Found**: Displays "Error: File not found" if the specified CSV file is missing.
- **Writing Exceptions**:
  - **Wrong File Format**: Displays "Error: Unable to write to file due to invalid format" if the data cannot be written correctly.
  - **File Not Found**: Displays "Error: Output file not found" if the file cannot be created.
  - **Folder/File Path Not Found**: Displays "Error: Invalid file or folder path" if the specified path is inaccessible.

## Prerequisites
- Java Development Kit (JDK) 8 or higher
- An IDE (e.g., IntelliJ IDEA, Eclipse) or command-line tools for compiling and running Java code
- Basic knowledge of Java Swing for GUI interaction

## Setup Instructions
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Abdullahmostafah/Sales-Invoice-Generator-November.git
   cd Sales-Invoice-Generator-November
   ```
2. **Compile the Code**:
   Use your IDE to import the project or compile manually:
   ```bash
   javac -d bin src/com/invoice/*/*.java
   ```
3. **Run the Application**:
   ```bash
   java -cp bin com.invoice.gui.MainFrame
   ```
4. **File Storage**:
   - Ensure the project directory has write permissions for creating `invoices.csv`.
   - The application will automatically create or read from `invoices.csv` in the project root.

## Usage
1. Launch the application to open the main GUI window.
2. Use the interface to:
   - Create a new invoice by entering details (invoice number, date, customer name, items).
   - Add items to the invoice using the provided fields.
   - Save the invoice to `invoices.csv` using the "Save" button.
   - Load existing invoices from `invoices.csv` using the "Load" button.
3. The `JTable` displays all invoices and their items.
4. Error messages will appear in dialogs for any file or data issues.

## Code Styling
- Follows Java naming conventions (e.g., `CamelCase` for classes, `camelCase` for methods and variables).
- Consistent indentation (4 spaces).
- Descriptive variable and method names.
- Javadoc comments for classes and methods.
- Modular design with separation of concerns (model, file handling, GUI).

## Contributing
Contributions are welcome! Please:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -m "Add your feature"`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Open a pull request.


## Acknowledgments
- Built using Java Swing for GUI components.
- Inspired by the need for a simple, open-source invoice generator for small businesses.
