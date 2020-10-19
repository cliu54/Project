package ca.uvic.seng330.ex4;

import java.util.ArrayList;

public class ReporterCatalog {

    private ArrayList<Reporter> reporter_list;

    public ReporterCatalog(){
        this.reporter_list= new ArrayList<Reporter>();
    }

     public ReporterCatalog(ReporterCatalog reporterCatalog){
        this.reporter_list= reporterCatalog.getReporterCatalog();
    }


    public Reporter getReporter(int id)
    {
        return reporter_list.get(id);
    }

    public Reporter addReporter(String reporterName)
    {
        Reporter newReporter = createReporter(getId(),reporterName);
        reporter_list.add(newReporter);

        return newReporter;
    }

    private int getId(){
        return this.reporter_list.size();
    }

    private Reporter createReporter(int id,String reporterName){
        return new Reporter(id,reporterName);
    }
    private ArrayList<Reporter> getReporterCatalog()
    {
        return reporter_list;
    }

}
