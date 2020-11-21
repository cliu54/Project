package ca.uvic.seng330.ex5;

import java.util.ArrayList;

public class ReporterCatalog {

    private ArrayList<Reporter> reporters;

    public ReporterCatalog(){
        this.reporters= new ArrayList<Reporter>();
    }

     public ReporterCatalog(ReporterCatalog reporterCatalog){
        this.reporters= reporterCatalog.getReporterCatalog();
    }


    public Reporter getReporter(int id)
    {
        return reporters.get(id);
    }

    public Reporter addReporter(String reporterName)
    {
        Reporter newReporter = createReporter(getId(),reporterName);
        reporters.add(newReporter);

        return newReporter;
    }

    private int getId(){
        return this.reporters.size();
    }

    public int size(){
        return this.reporters.size();
    }

    private Reporter createReporter(int id,String reporterName){
        return new Reporter(id,reporterName);
    }
    private ArrayList<Reporter> getReporterCatalog()
    {
        return reporters;
    }

}
