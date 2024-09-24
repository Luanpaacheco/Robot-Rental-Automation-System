# ACMERobots - Robot Rental Automation System

This project implements a graphical user interface (GUI) application to automate the ACMERobots business. The system features a cyclic screen that allows users to perform various operations related to registering robots, clients, rentals, and managing this information.

## System Features

- **Register New Robot**: Allows users to enter the details of a new robot. The system checks for existing robots with the same ID and keeps the robots sorted in ascending order. In case of a duplicate, an error message is displayed.

- **Register New Client**: Prompts for and registers the details of a new client. It checks if the client code already exists and maintains clients in ascending order by code. If a duplicate code is detected, an error message is shown.

- **Register New Rental**: Displays a list of registered clients for the user to select. Then, it prompts for and registers the details of a new rental for the selected client, including the selection of one or more available robots. Rentals are queued as pending. If the rental number already exists, an error message is displayed.

- **Process Rentals**: Automatically processes pending rentals from the queue. It checks if all requested robots are available. If so, the rental is marked as EXECUTING. Otherwise, the already allocated robots are freed, and the rental returns to the pending queue. If there are no rentals in the queue, an error message is shown.

- **Show General Report**: Displays all registered data for robots, clients, and rentals. If no data is registered, an error message is displayed.

- **Consult All Rentals**: Shows all details of registered rentals, including information about the clients and, if applicable, the allocated robots along with the final rental cost. If no rentals are registered, an error message is displayed.

- **Change Rental Status**: Allows users to change the status of an existing rental. It prompts for the rental number, displays its details, and requests the new status. Changes are not permitted for rentals in FINALIZED or CANCELED status. If the rental is not found, an error message is shown.

- **Load Initial System Data**: Prompts for the filename (without extension) to load the initial data into the system. Rentals are loaded into a pending queue. After loading, it displays all data for robots, clients, and rentals. If there are issues with loading data, an error message is displayed.

- **Save Data**: Prompts the user for a filename (without extension) and saves all registered data in the system to one or more files. If there are issues during saving, an error message is displayed.

- **Load Data**: Prompts the user for a filename (without extension) to load data from one or more files into the system. If there are issues during loading, an error message is displayed.

- **Exit System**: Ends the execution of the system.

## Technologies Used
- **Programming Language**: Java
- **Graphical User Interface (GUI)**: Designed to facilitate user interaction with the system.
- **Data Storage**: Uses CSV and JSON files to store data for robots, clients, and rentals.
- **Development Methodology**: Employed techniques such as pair programming for collaboration among developers.

## Developers
This project was developed by:
- Arthur Blasi
- Luan Pacheco Lima
- Luis Trein
