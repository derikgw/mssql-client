package com.derikwilson.mssqlclient.cli;

import com.derikwilson.mssqlclient.db.AppUser;
import com.derikwilson.mssqlclient.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.Date;

@Component
@Command(name = "addAppUser", description = "Add a new application user with a specified USER_ID.")
public class AddAppUserCommand implements Runnable {

    @Autowired
    private AppUserService appUserService;

    @Option(names = {"-u", "--userId"}, required = true, description = "The USER_ID for the new user, typically a unique 10-digit number used for authentication.")
    private Long userId;

    @Option(names = {"-a", "--active"}, description = "The active status for the new user. Use 1 for active, 0 for inactive. Defaults to 1.")
    private Integer active = 1; // Assuming a default active status

    @Override
    public void run() {
        AppUser newUser = new AppUser();
        newUser.setUserId(userId);
        newUser.setActive(active);
        newUser.setLastUpdate(new Date()); // Sets to current date/time

        // Persist the new AppUser instance
        AppUser savedUser = appUserService.saveAppUser(newUser);

        // Output confirmation, showing the USER_ID and auto-generated GUID
        System.out.printf("Added AppUser with GUID: %s, USER_ID: %d, and active status: %d%n", savedUser.getUserGuid().toString(), savedUser.getUserId(), savedUser.getActive());
    }
}
