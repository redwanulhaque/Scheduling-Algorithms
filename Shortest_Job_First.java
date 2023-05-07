import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Shortest_Job_First {
	
	
	// method for calculating waitTime and turnaroundTime of CPU burst cycle
	public static void calculateWaitTurnaround(int taskNumber, int []burstTimes, int []turnaroundTimes, int []WaitingTimes) {
        
		for(int i = 0; i < taskNumber; i++) {  // the for loop will iterate all the taskNumber to get waitingTimes and turnaroundTimes
			
		    if (i == 0) {  // first taskNumber
		    	
		        turnaroundTimes[i] = burstTimes[i];  // turnaroundTime of first task is set to its burstTime
		        WaitingTimes[i] = 0;  // waitingTime will start with 0
		        
		    } else {
		    	
		        turnaroundTimes[i] = turnaroundTimes[i-1] + burstTimes[i];  // turnaroundTimes is sum of the previous turnaroundTime and the current burst time
		        WaitingTimes[i] = turnaroundTimes[i-1];  // waitingTime is set to the previous turnaroundTime 
		    }
		}	
	}
	
	
	// method for calculating averageWait Time
	public static double averageWaitTime(int []waitingTimes, int totalTask) {
			
		int sumWaitTime = 0; 
			
		for(int i = 0; i < waitingTimes.length; i++) {  // for loop will iterate over all the values in the waitTimes array
			sumWaitTime = sumWaitTime + waitingTimes[i];  // sumWaitTimes add all the values in the waitTImes array one by one
		}
			
		double averageWaitTime = (double) sumWaitTime / totalTask;  // getting the average by dividing the sumWaitTime by taskNumber
			
		return averageWaitTime; 
	}
		
		
	// method for calculating averageTurnaround Time
	public static double averageTurnaroundTime(int []turnaroundTime, int taskNumber) {
			
        int sumTurnAroundTime = 0;
			
		for (int i = 0; i < turnaroundTime.length; i++) {  // for loop will iterate over all the values in the turnaroundTime array
			sumTurnAroundTime = sumTurnAroundTime + turnaroundTime[i];  // sumTurnAroundTime add all the values in the turnaroundTime array one by one
		}
			
		double averageTurnaroundTime = (double) sumTurnAroundTime / taskNumber;  // getting the average by dividing the sumWaitTime by taskNumber
			
		return averageTurnaroundTime;
	}
	
	
	// class for printing gantt chart
	 static class TaskNumber {
		 int taskNumber;
		 int burstTime;
	
		 // default constructor
		 TaskNumber(int taskNumber, int burstTime) {
			 
		     this.taskNumber = taskNumber;
		     this.burstTime = burstTime;
		 }
	 }
  
    
	public static void main(String[] args) {
		
		
		int taskNumberTotal = 5;  // task number will be 5 since there is 5 task being performed

				
		// waitingTime array and turnaroundTime array are place holder so once I call the calculate method they will be filled with the appropriate values
        int[] burstTimes = {20, 25, 25, 15, 10};  // burstTimes array holds the value for the all threads CPU burst time
        int[] burst = {20, 25, 25, 15, 10};  // burst array will be used to create the gantt chart
        int[] waitingTimes = new int[taskNumberTotal];
        int[] turnaroundTime = new int[taskNumberTotal];
        
        
        // Built in java array sort will soft the CPU burst time in ascending order so the shortest ones go first
        Arrays.sort(burstTimes);


        // calling on the calculateWaitTurnaround method
        calculateWaitTurnaround(taskNumberTotal, burstTimes, turnaroundTime, waitingTimes);
        
        
        // name of the scheduler algorithm 
        System.out.println("SJF:");
        
        
        // Using array list to store the each CPU burst so it can print the gantt chart
        ArrayList<TaskNumber> taskNumberID = new ArrayList<>();
        taskNumberID.add(new TaskNumber(1, burst[0]));  // task 1
        taskNumberID.add(new TaskNumber(2, burst[1]));  // task 2
        taskNumberID.add(new TaskNumber(3, burst[2]));  // task 3
        taskNumberID.add(new TaskNumber(4, burst[3]));  // task 4
        taskNumberID.add(new TaskNumber(5, burst[4]));  // task 5

    	
    	// with Comparator the task will be sorted in the array list
        taskNumberID.sort(new Comparator<TaskNumber>() {
        	
            public int compare(TaskNumber taskNumber1, TaskNumber taskNumber2) {  // get two different taskNumber and Compare
            	
                if (taskNumber1.burstTime == taskNumber2.burstTime) {  // if the compare catches 2 same burstTimes then the program will do randomly breaking tie
                	
                    if (Math.random() < 0.5) {  // using math.random to randomly breaking tie
                    	
                        return -1;
                        
                    } else {  // otherwise randomly break tie in the opposite direction 
                    	
                        return 1;
                    }
                    
                } else {  // burst times of the two different task numbers are compared.
                	
                    return Integer.compare(taskNumber1.burstTime, taskNumber2.burstTime);  // return the compared taskNumber
                }
            }
        });

    	
    	// printing the taskNumber in order as well as waitingTime and turnaroundTime
    	for(int i = 0; i < taskNumberID.size(); i++) {
    		
    		TaskNumber task = taskNumberID.get(i);  // get each task separately 
    		
    	    System.out.print("T" + task.taskNumber + " [" + waitingTimes[i] + " - " + turnaroundTime[i] + "]");  // printing the gantt chart
    	    
    	    if (i < taskNumberID.size() - 1) {  // remove the last comma after all the results are printed
    	        System.out.print(", ");
    	    }
    	}
        
        
        // calling on averageWaitTime method
        System.out.println("\nAvg. waiting time: " + averageWaitTime(waitingTimes, taskNumberTotal));
        
        
        // calling on averageTurnaroundTime method
        System.out.println("Avg. turnaround time: " + averageTurnaroundTime(turnaroundTime, taskNumberTotal));
    }
}