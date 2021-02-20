package controllers;

import models.Gender;
import models.Observation;
import models.ObservationStore;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import static play.libs.Scala.asScala;

@Singleton
public class ObservationController extends Controller {
    private final Form<ObservationData> form;
    private final Form<ObservationData> searchForm;
    private final FormFactory formFactory;
    private final ObservationStore observations;
    private  MessagesApi messagesApi;
    private int id;

    @Inject
    public ObservationController(FormFactory formFactory, MessagesApi messagesApi) {
        this.formFactory = formFactory;
        this.form = formFactory.form(ObservationData.class);
        this.searchForm = formFactory.form(ObservationData.class);
        this.messagesApi = messagesApi;
        this.observations = new ObservationStore();
        this.observations.add(new Observation(1, Gender.MALE, 20000, "Orca", LocalDate.now()));
        this.observations.add(new Observation(2, Gender.FEMALE, 15000, "Orca", LocalDate.now()));
        this.observations.add(new Observation(3, Gender.MALE, 50000, "Humpback", LocalDate.now()));
        id = 3;
    }

    public Result listObservations(Http.Request request) {
        return ok(views.html.observations.render(asScala(observations.getObservations()), searchForm, form, request, messagesApi.preferred(request)));
    }

    public Result createObservation(Http.Request request) {
        final Form<ObservationData> boundForm = form.bindFromRequest(request);
        ObservationData data = boundForm.get();
        Gender g;
        if (data.getGender().equals("Male")) {
            g = Gender.MALE;
        } else {
            g = Gender.FEMALE;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(data.getSightingDate(), formatter);

        Observation observation = new Observation(++id, g, data.getEst_size(), data.getSpecies(), date);
        observations.add(observation);
        return redirect(routes.ObservationController.listObservations());
    }

    public Result searchObservations(Http.Request request) {
        final Form<ObservationData> boundForm = searchForm.bindFromRequest(request);
        ObservationData data = boundForm.get();

        if (data.getSightingDate().equals("")) {
            return redirect(routes.ObservationController.listObservations());
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(data.getSightingDate(), formatter);

        ArrayList<Observation> searchList = new ArrayList();
        int x;
        for (Observation observation : observations) {
            x = date.compareTo(observation.getSightingDate());
            if (x == 0) {
                searchList.add(new Observation(observation));
            }
        }
        return ok(views.html.observationsSearch.render(asScala(searchList), searchForm, form, request, messagesApi.preferred(request)));
    }
}
