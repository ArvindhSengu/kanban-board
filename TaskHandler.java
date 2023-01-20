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
    String filepath;
    ArrayList<TaskDetails> tasks;
	HashMap<String, Integer> urgMap;
    TaskHandler(String inp){
        filepath = inp;
        tasks = new ArrayList<TaskDetails>();
		urgMap = new HashMap<String, Integer>(3);
		urgMap.put("high", 1);
		urgMap.put("mid", 2);
		urgMap.put("low", 3);
    }
    public void readFile() {
       	BufferedReader br = null;
       	try{
           	br = new BufferedReader(new FileReader(filepath));		

           	//One way of reading the file
			String contentLine = br.readLine();
			while (contentLine != null) {
				contentLine = br.readLine();
				//System.out.println(contentLine);
				if(contentLine == null) break;
				TaskDetails atd = new TaskDetails();
				//String[] aStringArr = contentLine.split("\\|");
				try{
					atd.setTask(contentLine.split("\\|"));
				}
				catch(Exception e){
					System.err.println(e.getStackTrace());
				}
                tasks.add(atd);
			}
			/*
            for(TaskDetails td : tasks){
                System.out.println(td.toString());
				System.out.println(td.getCat());
            } */
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
	public void sortTasks(){
		Collections.sort(tasks, new Comparator<TaskDetails>() {
			@Override
			public int compare(TaskDetails a, TaskDetails b){
				return urgMap.get(a.getUrg()) - urgMap.get(b.getUrg());
			}
		});
	}
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
	public int searchTask(String name){
		for(int i = 0; i < tasks.size(); i++){
			if(name.equalsIgnoreCase(tasks.get(i).getName())){
				return i;
			}
		}
		return -1;
	}
	public void deleteTask(String name){
		int i = searchTask(name);
		if(i >= 0 && i <= tasks.size()){
			tasks.remove(i);
			refreshFile();
		}
	}
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
	public ArrayList<TaskDetails> getTaskDetails(){
		return tasks;
	}
}
