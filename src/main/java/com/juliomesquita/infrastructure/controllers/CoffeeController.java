package com.juliomesquita.infrastructure.controllers;

import com.juliomesquita.application.usecases.coffee.addproducer.AddProducerCoffeeInput;
import com.juliomesquita.application.usecases.coffee.addproducer.AddProducerCoffeeUC;
import com.juliomesquita.application.usecases.coffee.addroasting.AddRoastingCoffeeInput;
import com.juliomesquita.application.usecases.coffee.addroasting.AddRoastingCoffeeUC;
import com.juliomesquita.application.usecases.coffee.commom.InfoCoffee;
import com.juliomesquita.application.usecases.coffee.commom.InfoProducer;
import com.juliomesquita.application.usecases.coffee.commom.RoastingResponse;
import com.juliomesquita.application.usecases.coffee.create.CreateCoffeeInput;
import com.juliomesquita.application.usecases.coffee.create.CreateCoffeeUC;
import com.juliomesquita.application.usecases.coffee.retrives.findall.FindAllCoffeeUC;
import com.juliomesquita.application.usecases.coffee.retrives.findbyid.FindCoffeeByIdUC;
import com.juliomesquita.application.usecases.coffee.update.UpdateCoffeeInput;
import com.juliomesquita.application.usecases.coffee.update.UpdateCoffeeUC;
import com.juliomesquita.domain.commom.SearchQuery;
import com.juliomesquita.infrastructure.docs.CoffeeDoc;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import static com.juliomesquita.infrastructure.utils.ControllersUtils.buildUri;


//http://localhost:8080/q/swagger-ui
@ApplicationScoped
public class CoffeeController implements CoffeeDoc {
    private final CreateCoffeeUC createCoffeeUC;
    private final UpdateCoffeeUC updateCoffeeUC;
    private final AddProducerCoffeeUC addProducerCoffeeUC;
    private final AddRoastingCoffeeUC addRoastingCoffeeUC;
    private final FindCoffeeByIdUC findCoffeeByIdUC;
    private final FindAllCoffeeUC findAllCoffeeUC;

    public CoffeeController(CreateCoffeeUC createCoffeeUC, UpdateCoffeeUC updateCoffeeUC, AddProducerCoffeeUC addProducerCoffeeUC, AddRoastingCoffeeUC addRoastingCoffeeUC, FindCoffeeByIdUC findCoffeeByIdUC, FindAllCoffeeUC findAllCoffeeUC) {
        this.createCoffeeUC = createCoffeeUC;
        this.updateCoffeeUC = updateCoffeeUC;
        this.addProducerCoffeeUC = addProducerCoffeeUC;
        this.addRoastingCoffeeUC = addRoastingCoffeeUC;
        this.findCoffeeByIdUC = findCoffeeByIdUC;
        this.findAllCoffeeUC = findAllCoffeeUC;
    }

    @Override
    public Response create(final CreateCoffeeInput request, final UriInfo uriInfo) {
        final var response = this.createCoffeeUC.execute(request);
        return Response
            .created(buildUri(uriInfo, response.coffeeResponse().id()))
            .entity(response)
            .build();
    }

    @Override
    public Response update(final Long id, final InfoCoffee request) {
        final var aCommand = new UpdateCoffeeInput(id, request);
        final var response = this.updateCoffeeUC.execute(aCommand);
        return Response.ok(response).build();
    }

    @Override
    public Response addProducer(final Long id, final InfoProducer request) {
        final var aCommand = new AddProducerCoffeeInput(id, request);
        final var response = this.addProducerCoffeeUC.execute(aCommand);
        return Response.ok(response).build();
    }

    @Override
    public Response addRoasting(final Long id, final RoastingResponse request) {
        final var aCommand = new AddRoastingCoffeeInput(id, request);
        final var response = this.addRoastingCoffeeUC.execute(aCommand);
        return Response.ok(response).build();
    }

    @Override
    public Response findById(final Long id) {
        final var response = this.findCoffeeByIdUC.execute(id);
        return Response.ok(response).build();
    }

    @Override
    public Response search(
        final int page, final int size, final String terms, final String sort, final String direction
    ) {
        final var aCommand = new SearchQuery(page, size, terms, sort, direction);
        final var response = this.findAllCoffeeUC.execute(aCommand);
        return Response.ok(response).build();
    }
}
