package com.juliomesquita.infrastructure.docs;

import com.juliomesquita.application.usecases.coffee.commom.CoffeeResponse;
import com.juliomesquita.application.usecases.coffee.commom.InfoCoffee;
import com.juliomesquita.application.usecases.coffee.commom.InfoProducer;
import com.juliomesquita.application.usecases.coffee.commom.RoastingResponse;
import com.juliomesquita.application.usecases.coffee.create.CreateCoffeeInput;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/coffees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface CoffeeDoc {

    @Operation(summary = "Cria um novo café", description = "Recebe os dados do café e persiste no banco de dados")
    @APIResponse(responseCode = "201", description = "Café criado com sucesso",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CoffeeResponse.class)),
            headers = {@Header(name = "Location", description = "URI do recurso criado")})
    @POST
    Response create(CreateCoffeeInput request, @Context UriInfo uriInfo);

    @Operation(summary = "Atatualiza um café existente", description = "Recebe os dados que devem ser atualizados para o novo café.")
    @APIResponse(responseCode = "200", description = "Café atualizado com sucesso",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CoffeeResponse.class)))
    @PATCH
    @Path("/{id}")
    Response update(@PathParam("id") Long id, InfoCoffee request);

    @Operation(summary = "Atatualiza um produtor", description = "Recebe os dados de um produtor e injeta no café.")
    @APIResponse(responseCode = "200", description = "Produtor atualizado com sucesso",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CoffeeResponse.class)))
    @PATCH
    @Path("/producer/{id}")
    Response addProducer(@PathParam("id") Long id, InfoProducer request);

    @Operation(summary = "Atatualiza um torrefação", description = "Recebe os dados de uma torrefação e injetam no café.")
    @APIResponse(responseCode = "200", description = "Torrefação atualiza com sucesso",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CoffeeResponse.class)))
    @PATCH
    @Path("/roasting/{id}")
    Response addRoasting(@PathParam("id") Long id, RoastingResponse request);
}
