package controller;


import domain.accoun.Account;
import domain.accoun.AccountDTO;
import repository.AccountRepository;
import twoFactor.EmailClient;
import twoFactor.RandomStringGenerator;

import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/account")
public class AccountController {

    @EJB
    AccountRepository accountRepository;

    public AccountController()
    {

    }
    @GET
    @Path("/ping")
    public String item()
    {
        return "welkom bij accounts";
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccount(@PathParam("id") long id)
    {
        return accountRepository.find(id);
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendSafetyEmail(AccountDTO account)
    {
        Account user;
        try {
             user = accountRepository.getUser(account.getName(), account.getPassword());
        } catch (Exception e) {
            return Response.ok(false).build();
        }

        RandomStringGenerator generator = new RandomStringGenerator();
        String randomString = generator.randomString(8);
        EmailClient client = new EmailClient();
        try {
            client.sendAsHtml(user.getEmail(), "authentication code",
                    "<h1>new authentication code</h1>" +
                            "<p>new code: "+ randomString +" </p>");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        user.setSignInCode(randomString);
        user.setTime(System.currentTimeMillis());
        accountRepository.update(user);

        return Response.ok(true).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getAccounts()
    {
        return accountRepository.findAll();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(AccountDTO accountDTO) {
        Account account = new Account(accountDTO);
        accountRepository.update(account);
        return Response.ok().entity(account).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(AccountDTO accountDTO) {
        Account account = new Account(accountDTO);
        accountRepository.save(account);
        return Response.ok().entity(account).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        accountRepository.delete(id);
        return Response.ok(true).build();
    }
}
