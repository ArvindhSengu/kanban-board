class Test{
    public static void main(String[] args){
        FReader fr = new FReader("C:\\Users\\alan3\\kanban_example.txt");
        fr.readFile();
        for(TaskDetails td : fr.getTaskDetails()){
            System.out.println(td.toString());
        }
    }
}