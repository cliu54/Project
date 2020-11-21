package ca.uvic.seng330.ex5;
import java.io.IOException;
import java.util.Date;

public class WhaleMonitoringSystem {

	private ObservationCatalog observations;
	private ReporterCatalog reporters;
	private WhaleCatalog whales;

	public WhaleMonitoringSystem(){

		this.observations = new ObservationCatalog();
		this.reporters = new ReporterCatalog();
		this.whales = new WhaleCatalog();
	}

	public WhaleMonitoringSystem(WhaleMonitoringSystem wms){

		this.observations = wms.getObservationCatalog();
		this.reporters = wms.getReporterCatalog();
		this.whales = wms.getWhaleCatalog();
	}

	public ObservationCatalog getObservationCatalog(){
		return this.observations;
	}

	public ReporterCatalog getReporterCatalog(){
		return this.reporters;
	}

	public WhaleCatalog getWhaleCatalog(){
		return this.whales;
	}

	public Whale addWhale(Whale.Gender whaleGender, Whale.Breed whaleBreed, Boolean isAlive, int weight, int length){

		return this.whales.addWhale(whaleGender,whaleBreed,isAlive,weight,length);
	}

	public Reporter addReporter(String reporterName){

		return this.reporters.addReporter(reporterName);
	}

	public Observation addObservation(Reporter reporter, Whale whale, Location location, Date timeStamp) throws IOException {

		Observation obs=this.observations.addObservation(reporter, whale, timeStamp, location);
		reporter.addObservation(obs);
		return obs;
	}


}
