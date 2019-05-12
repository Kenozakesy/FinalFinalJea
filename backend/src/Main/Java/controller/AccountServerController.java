package controller;

import domain.accoun.AccountDTO;
import domain.accountServer.AccountServer;
import domain.accountServer.AccountServerDTO;
import repository.AccountServerRepository;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/accountServer")
public class AccountServerController {

    @EJB
    AccountServerRepository accountServerRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUserToServer(AccountServerDTO accountServerDTO) {

//        // TODO: check if koppeling already exists
//        AccountServer accountServer = new AccountServer(accountServerDTO.getAccountId(), accountServerDTO.getServerId());
//
//        accountServerRepository.save(accountServer);
        return Response.ok().entity(true).build();
    }
}
