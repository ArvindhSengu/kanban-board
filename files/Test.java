class Test{
    public static void main(String[] args){
        TaskHandler th = new TaskHandler("C:\\Users\\admin\\OneDrive\\Documents\\GitHub\\kanban-board");
        th.readFile();
        th.addTask(new TaskDetails("learn driving", "get g2", "mid", "to do"));
        th.deleteTask("exercise");
        th.sortTasks();
        for(TaskDetails td : th.getTaskDetails()){
            System.out.println(td.toString());
        }
    }
}
