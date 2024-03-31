# Task Manager App

## Classes

### Basic Structure
These are the 3 main Classes where we create our methods for working with Data

#### TaskApp
Acts as a handler for I/O for the User
Calls functions from MySqlTaskDAO

#### MySqlTaskDAO
Actually handles Data between the Program & Database
Uses the TaskDaoInterface as an Interface

#### TaskDaoInterface
Declares the Methods we need to use

### Other Classes

#### MySqlDao
Contains Methods for Opening & Closing Connections to Database

#### DTO - Task
Basic Class for creating Task Objects