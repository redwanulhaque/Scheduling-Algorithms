
public class Round_Robin {
	
	
	// method for calculateServiceTime of CPU burst cycle
	public static void calculateServiceTime(int taskNumber, int[] readyQueue, int timeQuantum, int[] remainingTimeInQueue, int[] turnaroundTime) {
	    
		int serviceTime = 0;  // Initialize service time to 0
	    int currentTask = -1;  // currentTask set to -1 to indicate that no task has yet started
	    int taskStartTime = -1;  // taskStartTime set to -1 to indicate that no task has yet started

	    while (true) {  // until all taskNumber are done, the while loop is true
	    	
	        boolean done = true;  // assume all the task are done

	        for (int i = 0; i < remainingTimeInQueue.length; i++) {  // for loop will iterate over all the remainingTimeInQueue
	        	
	            if (remainingTimeInQueue[i] > 0) {  // If the taskNumber has burst time remaining, it is not yet finished
	                done = false;
	                
	                if (currentTask != i) {  // If this is a new task, print the previous task's service time and update the current task and task start time
	                	
	                    if (currentTask != -1) {
	                    
	                        System.out.print("T" + (currentTask + 1) + " [" + taskStartTime + " - " + serviceTime + "], ");  // Printing gantt chart
	                        
	                    }  // if loop
	                    
	                    currentTask = i;  // current task will be updated
	                    taskStartTime = serviceTime;  // taskStartTime will be updated to the serviceTime
	                }  // if loop
	                
	                
	                // Math.min built in function will determine how much time to process for this iteration 
	                // (either the full time quantum or the remaining burst time)
	                // Depending on which ever task is smaller
	                int timeToProcess = Math.min(remainingTimeInQueue[i], timeQuantum);
	                
	                serviceTime += timeToProcess;  // update serviceTime by adding it with timeToProcess
	                
	                remainingTimeInQueue[i] -= timeToProcess; 
	                
	                if (remainingTimeInQueue[i] == 0) {  // If the task has finished in the readyQueue, update its turnaroundTime
	                    turnaroundTime[i] = serviceTime;
	                }  // if loop
	                
	            }  // first if loop
	            
	        }  // for loop
	        
	        if (done) {  // when all task finish running print the remaining task to the gantt chart
	            System.out.print("T" + (currentTask + 1) + " [" + taskStartTime + " - " + serviceTime + "]");
	            break;  // break out of loop when done 
	        }  // if loop
	        
	    }  // while loop
	}

	
	// method for calculateWaitTurnaround time
	public static void calculateWaitTurnaround(int taskNumber, int[] burstTimes, int[] turnaroundTime, int timeQuantum, int[] readyQueue, int[] waitingTimes) {
	    
		int[] remainingTimeInQueue = readyQueue.clone();  // remainingTimeInQueue will keep a copy of the readyQueue. The readyQueue is keep in order in which a task arrive

	    calculateServiceTime(taskNumber, readyQueue, timeQuantum, remainingTimeInQueue, turnaroundTime);  // calling on calculateServiceTime method

	    for (int i = 0; i < taskNumber; i++) {  // the for loop will iterate over all the taskNumber
	        waitingTimes[i] = turnaroundTime[i] - burstTimes[i];  // calculate waiting time by current turnaroundTime - burstTimes
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
	
		
	public static void main(String args[]) {
		
		
		int taskNumberTotal = 5;  // number of task is 5
		int timeQuantum = 10;  // For the round robin scheduling algorithm the time quantum is set to 10
		
		
		int[] burstTimes = {20,25,25,15,10};  // the CPU burst for each task Number
		int[] readyQueue = {20,25,25,15,10};  // ready queue array
		int[] waitingTimes = new int[taskNumberTotal];  // once calculateWaitTurnaround is called, waitingTimes array will be filled with its value
        int[] turnaroundTime = new int[taskNumberTotal];  // once calculateWaitTurnaround is called, turnaroundTime array will be filled with its value
          
		
        // name of the scheduling algorithm
        System.out.print("RR:\n"); 
        
        
        // calling on calculateWaitTurnaround method
        calculateWaitTurnaround(taskNumberTotal, burstTimes, turnaroundTime, timeQuantum, readyQueue, waitingTimes);  
        
        
        // calling on averageWaitTime method
        System.out.println("\nAvg. waiting time: " + averageWaitTime(waitingTimes, taskNumberTotal));
        
        
        // calling on averageTurnaroundTime method
        System.out.println("Avg. turnaround time: " + averageTurnaroundTime(turnaroundTime, taskNumberTotal));
	}  
}  