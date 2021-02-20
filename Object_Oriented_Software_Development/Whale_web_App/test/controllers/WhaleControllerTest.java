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

public class WhaleControllerTest extends WithApplication{

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }


    @Test
    public void testCreateWhale() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/whales")
                .bodyForm(ImmutableMap.of("species", "Orca", "estimated_weight", "5000", "gender", "Female"));
        Result result = route(app, request);
        // SEE_OTHER because the controller redirects (HTTP 303)
        assertEquals(SEE_OTHER, result.status());
    }

    @Test
    public void testListWhales() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/whales");
        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
    @Test
    public void testSearch(){
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/whales")
                .bodyForm(ImmutableMap.of("species", "Orca", "estimated_weight", "5000", "gender", "Female"))
                .bodyForm(ImmutableMap.of("species", "Humpback", "estimated_weight", "3000", "gender", "Female"))
                .bodyForm(ImmutableMap.of("species", "Humpback", "estimated_weight", "4000", "gender", "Female"))

                .method(GET)
                .uri("/whales/search")
                .bodyForm(ImmutableMap.of("Species","Humpback"));


        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
}
