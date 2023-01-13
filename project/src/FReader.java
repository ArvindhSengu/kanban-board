import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class FReader{
    String filepath;
    ArrayList<TaskDetails> tasks;
    FReader(String inp){
        filepath = inp;
        tasks = new ArrayList<TaskDetails>();
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
	public ArrayList<TaskDetails> getTaskDetails(){
		return tasks;
	}
}