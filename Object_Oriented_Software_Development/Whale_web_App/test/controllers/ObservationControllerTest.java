package controllers;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;


import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;
import static play.test.Helpers.route;

public class ObservationControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }


    @Test
    public void testCreateObservation() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/observations")
                .bodyForm(ImmutableMap.of("species", "Orca", "est_size", "5000", "sightingDate", "20201208", "gender", "Female"));
        Result result = route(app, request);
        // SEE_OTHER because the controller redirects (HTTP 303)
        assertEquals(SEE_OTHER, result.status());
    }

    @Test
    public void testListObservations() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/observations");
        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void testSearchObservations() {
        String input = "20201207";
        String input2 = "20201208";

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/observations")
                .bodyForm(ImmutableMap.of("species", "Orca", "est_size", "5000", "sightingDate", input, "gender", "Female"))
                .bodyForm(ImmutableMap.of("species", "Orca", "est_size", "5000", "sightingDate", input, "gender", "Female"))
                .bodyForm(ImmutableMap.of("species", "Orca", "est_size", "5000", "sightingDate", input2, "gender", "Female"))

                .method(GET)
                .uri("/observations/list")
                .bodyForm(ImmutableMap.of("sightingDate", input));

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
}
