import java.util.Random;

public class Priority_Scheduling {
	
	
	static int[] startTimes = new int[5];  // array to store the start times of each task
    static int[] endTimes = new int[5];  // array to store the end times of each task
    
    
    // method for calculating waitTime and turnaroundTime of CPU burst cycle
    public static void calculateWaitTurnaround(int taskNumber, int[] arrivalTimes, int[] burstTimes, int[] priority, int[] turnaroundTimes, int[] waitingTimes) {
	    
        int currentTime = 0;  // setting current time to 0, this will be used to keep track of the current time during the algorithm
        int totalProcessor = 5;  // total processor is 5

        while (totalProcessor > 0 && currentTime <= 1000) {
        	
            int minimumPriority = Integer.MAX_VALUE;  // keep track of the task with the highest priority at any given time
            int minimumPriorityIndex = -1;  // keep track of the task with the highest priority at any given time
            
            for (int i = 0; i < taskNumber; i++) {  // iterate through each task
            	
                if (arrivalTimes[i] <= currentTime && priority[i] < minimumPriority) {  // checks if the current task has arrived and has a higher priority than the other task
                    
                    minimumPriority = priority[i];  // if the if loop is true new highest priority will be updated
                    minimumPriorityIndex = i;  // if the if loop is true new highest priority will be updated
                }
            } // for loop
            
            if (minimumPriorityIndex == -1) {  // if there no more task
                currentTime++;  // current time will be incremented
            } 
            
            else {
            	
                waitingTimes[minimumPriorityIndex] = currentTime - arrivalTimes[minimumPriorityIndex];  // calculates the waiting time for the task with the highest priority that is being scheduled
                startTimes[minimumPriorityIndex] = currentTime-1;  // set the start time of the task
                
                currentTime += burstTimes[minimumPriorityIndex];  // updates the current time by adding the burst time of the task with the highest priority
                endTimes[minimumPriorityIndex] = currentTime-1;  // set the end time of the task
                
                turnaroundTimes[minimumPriorityIndex] = currentTime - arrivalTimes[minimumPriorityIndex];  // calculates the turn around time for the task with the highest priority that is being scheduled
                totalProcessor--;
                
                priority[minimumPriorityIndex] = Integer.MAX_VALUE;  // updates the priority of the task with the highest priority that is being scheduled
            }  // else
        }  // while
        
        int[] processOrder = {0, 1, 2, 3, 4};  // default order process
        
        for (int i = 0; i < taskNumber; i++) {  // iterate each of the task
        	
            for (int j = i+1; j < 5; j++) {
            	
                if (endTimes[processOrder[i]] > endTimes[processOrder[j]]) {  // determine the order of each task by endTimes
                    int temp = processOrder[i];
                    processOrder[i] = processOrder[j];
                    processOrder[j] = temp;
                }  // if loop
            }  // inner for loop
        }  // Outer for loop
        
        for (int i = 0, j = 0; i < taskNumber; i++) {  // iterate through each of the task
        	
            int index = processOrder[i];  // get the order of each task start and end time based on the time each task finished
            
            if (startTimes[index] > j) {  // print out the blank start and end times
                System.out.print("TX [" + j + " - " + startTimes[index] + "], ");
            }
            
            System.out.print("T" + (index+1) + " [" + startTimes[index] + " - " + endTimes[index] + "]");  // print the order of each task
            
            if (i < taskNumber - 1) {  // once the result reaches the last entry, then the last comma will not show
                System.out.print(", ");
            }
            
            j = endTimes[index];
        }
    }


	// method for calculating averageWait Time
	public static double averageWaitTime(int []waitingTimes, int totalTask) {
			
		int sumWaitTime = 0; 
			
		for(int i = 0; i < totalTask; i++) {  // for loop will iterate over all the values in the waitTimes array
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


	public static void main(String[] args) {
		
		
		int taskNumberTotal = 5;  // task number will be 5 since there is 5 task being performed

		
        int[] burstTimes = {20, 25, 25, 15, 10};  // burstTimes array holds the value for the all threads CPU burst time
        int[] prioritys = {2,4,3,3,1};  // priority of each task 
        int[] arrivalTimes = new Random().ints(5, 0, 100).toArray();
        int[] turnaroundTime = new int[taskNumberTotal];
        int[] waitingTimes = new int[taskNumberTotal];
        
        
        // name of the scheduling algorithm
        System.out.print("P:\n");
                
        
        // calling on the calculateWaitTurnaround method
        calculateWaitTurnaround(taskNumberTotal, arrivalTimes, burstTimes, prioritys, turnaroundTime, waitingTimes);
        
        
        // calling on averageWaitTime method
        System.out.println("\nAvg. waiting time: " + averageWaitTime(waitingTimes, taskNumberTotal));

        
        // calling on averageTurnaroundTime method
        System.out.println("Avg. turnaround time: " + averageTurnaroundTime(turnaroundTime, taskNumberTotal));
	}
}
