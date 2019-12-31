
package com.dileep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ParseException, SQLException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        TaskManager taskManager = new TaskManager();

        while(true){
            System.out.print("------------\nMenu\n1.Add\n2.Display\n3.Delete\n4.SearchByTaskId\n" +
                                           "5.ListByStatus\n6:updateStatus\n7:TotalTasks\n" +
                                            "8:getPendingTasks\n9:todaysTasks\n10:Exit\n");
            System.out.println("Enter choice:");
            int menuId=Integer.parseInt(br.readLine());

            if(menuId == 1){
                System.out.println("Enter Task name");
                String name=br.readLine();
                System.out.println("Enter task descripton");
                String description=br.readLine();
                System.out.println("Enter the status of task : \n 1:Created \n 2:InProgress \n 3:Done");
                String status=br.readLine();
                System.out.println("Enter the due date:");
                Date dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(br.readLine());
                //Task task= new Task(idCount, name, description, Status.valueOf(status));
                taskManager.addTask(name, description, Status.valueOf(status), dueDate);
            }

            else if(menuId == 2){
                List<task> taskList = taskManager.display();
                for(com.dileep.task task:taskList) System.out.println(task);
            }

            else if(menuId == 3){
                System.out.println("Enter taskId to Delete :");
                int taskId=Integer.parseInt(br.readLine());
                taskManager.delete(taskId);
            }

            else if(menuId == 4){
                System.out.println("Enter TaskId name to search:");
                task task=taskManager.searchByTaskId(Integer.parseInt(br.readLine()));
                System.out.println(task);
                }

            else if(menuId == 5){
                System.out.println("Enter the status:");
                String status=br.readLine();
                List<task> taskList=taskManager.listByStatus(Status.valueOf(status));
                if(taskList.size()==0){
                    System.out.println("No Tasks Found related to your search");
                }
                else{
                    for(Object task : taskList){
                        System.out.println(task);
                    }
                }
            }

            else if(menuId == 6){
                System.out.println("Enter taskId to update:");
                int getTaskID=Integer.parseInt(br.readLine());
                System.out.println("Enter status to Update");
                String status=br.readLine();
                taskManager.updateStatus(Status.valueOf(status), getTaskID);

            }

            else if(menuId == 7){
                System.out.println("Total number of Tasks:"+taskManager.getTotalCount());
            }

            else if(menuId==8){
                System.out.println("Search by status InProgress or Created");
                List<task> taskList = taskManager.getPendingTasks();
                for(com.dileep.task task : taskList){
                    System.out.println(task);
                }
            }

            else if(menuId==9){
                List<task> taskList = taskManager.getTodaysTasks();
                if(taskList.size()==0){
                    System.out.println("No data Found Related To Your Search");
                }
                else{
                    for(com.dileep.task task : taskList) System.out.println(task);
                }
            }
            else if(menuId == 10) System.exit(0);

            }

        }

    }
