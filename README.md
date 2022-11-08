<h1 align="center">TaskManager</h1>

<p align="center">
  
<img src="https://img.shields.io/badge/made%20by-glebinside-blue.svg" >

<img src="https://img.shields.io/badge/java-19-orange.svg">

<img src="https://img.shields.io/github/languages/top/glebinside/TaskManager.svg">

</p>

## Description

**Divide and Conquer**
![Image](https://user-images.githubusercontent.com/95642615/200635108-b2942777-19cb-4d41-b02b-dac52ba250b3.png)

1. Getting a list of all tasks
2. Deleting all tasks
3. Getting by ID
4. Creation
5. Update
6. Deletion by ID
7. Getting the browsing history

## How to use

### So far, only manual control of the program is implemented here:

1. Use Intellij IDEA
2. Open the main class
3. Use the fileBackedTasksManager class object to call task management methods
4. Use the Managers utility class to create an object of the fileBackedTasksManager class and restore data after a restart

## About the project

### Task Manager

- Create tasks of three types: simple tasks, epics - large tasks divided into parts, subtasks - components of epics
- Add tasks, change their status and content (the status of epics is calculated automatically)
- Save all tasks even when restarting the application. Saving the data to a file will restore the data after a reboot
