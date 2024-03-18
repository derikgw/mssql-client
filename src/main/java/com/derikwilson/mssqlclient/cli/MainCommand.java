package com.derikwilson.mssqlclient.cli;

import com.derikwilson.mssqlclient.MssqlClientApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.IFactory;
import picocli.CommandLine;
import org.springframework.beans.factory.annotation.Autowired;

@Component
@Command(name = "cli", subcommands = {AddAppUserCommand.class /*, other command classes here*/})
public class MainCommand implements Runnable {

    @Autowired
    private IFactory factory; // Spring-aware Picocli component factory

    @Override
    public void run() {
        // This method is called if no subcommands are specified.
        System.out.println("No command specified. Use 'cli --help' for more information.");
    }

    // Main method to bootstrap the application
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MssqlClientApplication.class, args);
        CommandLine commandLine = new CommandLine(new MainCommand(), context.getBean(IFactory.class));
        int exitCode = commandLine.execute(args);
        System.exit(exitCode);
    }
}
