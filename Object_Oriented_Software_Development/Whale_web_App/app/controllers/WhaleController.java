package controllers;

import models.Gender;
import models.Whale;
import models.WhaleStore;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;

import static play.libs.Scala.asScala;

@Singleton
public class WhaleController extends Controller {

    private final Form<WhaleData> form;
    private final Form<WhaleData> searchForm;
    private final WhaleStore Whales;
    private MessagesApi messagesApi;
    private long id;

    @Inject
    public WhaleController(FormFactory formFactory, MessagesApi messagesApi) {
        this.form = formFactory.form(WhaleData.class);
        this.searchForm = formFactory.form((WhaleData.class));
        this.messagesApi = messagesApi;
        this.Whales = new WhaleStore();

        this.Whales.add(new Whale((long) 1, "Orca", 20000, Gender.MALE));
        this.Whales.add(new Whale((long) 2, "Orca", 15000, Gender.FEMALE));
        this.Whales.add(new Whale((long) 3, "Humpback", 50000, Gender.MALE));
        id = 3;
    }

    public Result createWhale(Http.Request request) {
        final Form<WhaleData> boundForm = form.bindFromRequest(request);
        WhaleData data = boundForm.get();
        Gender g;

        if (data.getGender().equals("Male")) {
            g = Gender.MALE;
        } else {
            g = Gender.FEMALE;
        }

        Whale whale = new Whale(++id, data.getSpecies(), data.getEst_size(), g);
        Whales.add(whale);
        return redirect(routes.WhaleController.listWhales());
    }

    public Result listWhales(Http.Request request) {
        ArrayList<Whale> whaleList = Whales.getWhales();

        if (request.accepts("text/html")) {
            return ok(views.html.whales.render(asScala(Whales.getWhales()), searchForm, form, request, messagesApi.preferred(request)));
        } else {
            return ok(Json.toJson(whaleList));
        }
    }

    public Result whaleSearch(Http.Request request) {
        final Form<WhaleData> boundForm = searchForm.bindFromRequest(request);
        WhaleData data = boundForm.get();

        String inputSpecies;
        inputSpecies = data.getSpecies();

        // get the whaleList we have
        ArrayList<Whale> myList = new ArrayList<Whale>();

        Gender g;
        // search for species in list
        for (int i = 0; i <= Whales.size() - 1; i++) {
            if ((this.Whales.getWhales().get(i).getSpecies()).equals(inputSpecies)) {
                if (this.Whales.getWhales().get(i).getGender().equals("Male")) {
                    g = Gender.MALE;
                } else {
                    g = Gender.FEMALE;
                }

                Whale whale = new Whale(this.Whales.getWhales().get(i).getId(), this.Whales.getWhales().get(i).getSpecies(),
                        this.Whales.getWhales().get(i).getEstimated_weight(), g);
                myList.add(whale);
            }
        }
        return ok(views.html.whalesSearch.render(asScala(myList), searchForm,form, request, messagesApi.preferred(request)));
    }
}
