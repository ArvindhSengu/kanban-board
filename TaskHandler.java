import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.File;

class TaskHandler{
	//the file to be read
    String filepath;
	//ArrayList containing the details of every task
    ArrayList<TaskDetails> tasks;
	//Hashmap containing key:value pairs for urgency:numval
	HashMap<String, Integer> urgMap;

	public void initializeTH(String filePath){
		
	}

	/**
	 * constructor to get the filepath, initialize ArrayList tasks and declare UrgMap
	 * @param inp the filepath to be read
	 */
    TaskHandler(String inp){
        filepath = inp;
        tasks = new ArrayList<TaskDetails>();
		urgMap = new HashMap<String, Integer>(3);
		urgMap.put("high", 1);
		urgMap.put("mid", 2);
		urgMap.put("low", 3);
    }
	/**
	 * reads the file and formats the data read into the ArrayList 'tasks' using TaskDetails object
	 */
    public void readFile() {
       	BufferedReader br = null;
       	try{
           	br = new BufferedReader(new FileReader(filepath));		
			String contentLine = br.readLine();
			while (contentLine != null) {
				contentLine = br.readLine();
				if(contentLine == null) break;
				TaskDetails atd = new TaskDetails();
				try{
					atd.setTask(contentLine.split("\\|"));
				}
				catch(Exception e){
					System.err.println(e.getStackTrace());
				}
                tasks.add(atd);
			}
       	}
		catch (IOException e){
	   		e.printStackTrace();
       	} 
       	finally{
	   		try {
	      		if (br != null){
					br.close();
				}
	   		} 
	   		catch (IOException e) {
				System.out.println("Error in closing the BufferedReader");
	   		}
		}
  	}
	/**
	 * sorts the tasks from highest urgency to lowest urgency
	 */
	public void sortTasks(){
		Collections.sort(tasks, new Comparator<TaskDetails>() {
			@Override
			/**
			 * determines how to compare the values of TaskDetails, in this case it will compare them by using the
			 * hashmap values of their urgencies
			 */
			public int compare(TaskDetails a, TaskDetails b){
				return urgMap.get(a.getUrg()) - urgMap.get(b.getUrg());
			}
		});
	}
	/**
	 * method to add new tasks into the ArrayList 'tasks' and write it to the file
	 * @param newtask the new task to be added with all the details
	 */
	public void addTask(TaskDetails newtask){
		if(searchTask(newtask.getName()) != -1){
			System.err.println("task already exists");
			return;
		}
		tasks.add(newtask);
		try{
			File file = new File(filepath);
			FileWriter fr = new FileWriter(file, true);
			fr.write(newtask.toString() + "\n");
			fr.close();
		} catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
	}
	/**
	 * method to find the index of the task givem, returns -1 if task is not found
	 * @param name the taskname of which index we are looking for
	 * @return the index of the task 'name' or -1 if not found
	 */
	public int searchTask(String name){
		for(int i = 0; i < tasks.size(); i++){
			if(name.equalsIgnoreCase(tasks.get(i).getName())){
				return i;
			}
		}
		return -1;
	}
	/**
	 * deletes task from the ArrayList 'tasks' and from the database
	 * @param name the name of the task to be deleted
	 */
	public void deleteTask(String name){
		int i = searchTask(name);
		if(i >= 0 && i <= tasks.size()){
			tasks.remove(i);
			refreshFile();
		}
	}
	/**
	 * method that rewrites the file based on the current ArrayList 'tasks'
	 * writes all of the tasks in the same format as it is read in
	 */
	public void refreshFile(){
		try{
			File file = new File(filepath);
			FileWriter fr = new FileWriter(file);
			fr.write("task name|task description|urgency|category\n");
			for(TaskDetails td : tasks){
				fr.write(td.toString() + "\n");
			}
			fr.close();
		} catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
	}
	/**
	 * method to move a task's category one to the right (to do -> in progress -> complete)
	 * @param name name of the task to be moved
	 */
	public void rightArrow(String name){
		int i = searchTask(name);
		if(i != -1){
			String aCat = tasks.get(i).getCat();
			if(aCat.equals("to do")){
				tasks.get(i).updateCat("in progress");
			} else if(aCat.equals("in progress")){
				tasks.get(i).updateCat("complete");
			}
			refreshFile();
		}
	}
	/**
	 * method to move a task's category one to the left (to do <- in progress <- complete)
	 * @param name name of the task to be moved
	 */
	public void leftArrow(String name){
		int i = searchTask(name);
		if(i != -1){
			String aCat = tasks.get(i).getCat();
			if(aCat.equals("complete")){
				tasks.get(i).updateCat("in progress");
			} else if(aCat.equals("in progress")){
				tasks.get(i).updateCat("to do");
			}
			refreshFile();
		}
	}
	/**
	 * method to update the name of a task in the ArrayList 'tasks'
	 * @param task the name of the task to be changed
	 * @param newname the new requested name of the task
	 */
	public void updateTaskName(String task, String newname){
		int i = searchTask(task);
		int j = searchTask(newname);
		if(i != -1 && j == -1){
			tasks.get(i).updateName(newname);
			refreshFile();
		} else{
			System.out.println("could not find task or new taskname already exists");
		}
	}
	/**
	 * method to update the description of the task
	 * @param name the name of the task to be changed
	 * @param newDescr the new requested description of the task
	 */
	public void updateDescription(String name, String newDescr){
		int i = searchTask(name);
		if(i != -1){
			tasks.get(i).updateDescr(newDescr);
			refreshFile();
		}
	}
	/**
	 * method to update the urgency of the task
	 * @param name the name of the task to be changed
	 * @param newUrgency the new requested urgency of the task
	 */
	public void updateUrg(String name, String newUrgency){
		int i = searchTask(name);
		if(i != -1){
			tasks.get(i).updateUrgency(newUrgency.toLowerCase());
			refreshFile();
		}
	}
	/**
	 * getter for the ArrayList containing all the tasks
	 * @return the ArrayList containing the details on all the tasks
	 */
	public ArrayList<TaskDetails> getTaskDetails(){
		return tasks;
	}
}
