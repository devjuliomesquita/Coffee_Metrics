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
import com.juliomesquita.application.usecases.coffee.update.UpdateCoffeeInput;
import com.juliomesquita.application.usecases.coffee.update.UpdateCoffeeUC;
import com.juliomesquita.infrastructure.docs.CoffeeDoc;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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

    public CoffeeController(
        final CreateCoffeeUC createCoffeeUC,
        final UpdateCoffeeUC updateCoffeeUC,
        final AddProducerCoffeeUC addProducerCoffeeUC,
        final AddRoastingCoffeeUC addRoastingCoffeeUC
    ) {
        this.createCoffeeUC = createCoffeeUC;
        this.updateCoffeeUC = updateCoffeeUC;
        this.addProducerCoffeeUC = addProducerCoffeeUC;
        this.addRoastingCoffeeUC = addRoastingCoffeeUC;
    }

    @Override
    public Response create(@Valid final CreateCoffeeInput request, final UriInfo uriInfo) {
        final var response = this.createCoffeeUC.execute(request);
        return Response
            .created(buildUri(uriInfo, response.coffeeResponse().id()))
            .entity(response)
            .build();
    }

    @Override
    public Response update(@NotNull final Long id, @Valid final InfoCoffee request) {
        final var aCommand = new UpdateCoffeeInput(id, request);
        final var response = this.updateCoffeeUC.execute(aCommand);
        return Response.ok(response).build();
    }

    @Override
    public Response addProducer(@NotNull final Long id, @Valid final InfoProducer request) {
        final var aCommand = new AddProducerCoffeeInput(id, request);
        final var response = this.addProducerCoffeeUC.execute(aCommand);
        return Response.ok(response).build();
    }

    @Override
    public Response addRoasting(@NotNull final Long id, @Valid final RoastingResponse request) {
        final var aCommand = new AddRoastingCoffeeInput(id, request);
        final var response = this.addRoastingCoffeeUC.execute(aCommand);
        return Response.ok(response).build();

    }
}
