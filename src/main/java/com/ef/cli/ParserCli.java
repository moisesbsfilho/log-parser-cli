package com.ef.cli;

import com.ef.cli.helper.PrintOptionsHelper;
import com.ef.cli.option.ParserOptions;
import com.ef.cli.parser.CommandLineParser;
import com.ef.cli.parser.DefaultParse;
import com.ef.core.business.LogBusiness;
import com.ef.core.loader.LogFileLoader;
import com.ef.core.model.Duration;
import com.ef.core.repository.LogRepository;
import com.ef.core.business.LogFileBusiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParserCli {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParserCli.class);

    private static final String BANNER = "\n" +
            " .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------. \n" +
            "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n" +
            "| |   ______     | || |      __      | || |  _______     | || |    _______   | || |  _________   | || |  _______     | |\n" +
            "| |  |_   __ \\   | || |     /  \\     | || | |_   __ \\    | || |   /  ___  |  | || | |_   ___  |  | || | |_   __ \\    | |\n" +
            "| |    | |__) |  | || |    / /\\ \\    | || |   | |__) |   | || |  |  (__ \\_|  | || |   | |_  \\_|  | || |   | |__) |   | |\n" +
            "| |    |  ___/   | || |   / ____ \\   | || |   |  __ /    | || |   '.___`-.   | || |   |  _|  _   | || |   |  __ /    | |\n" +
            "| |   _| |_      | || | _/ /    \\ \\_ | || |  _| |  \\ \\_  | || |  |`\\____) |  | || |  _| |___/ |  | || |  _| |  \\ \\_  | |\n" +
            "| |  |_____|     | || ||____|  |____|| || | |____| |___| | || |  |_______.'  | || | |_________|  | || | |____| |___| | |\n" +
            "| |              | || |              | || |              | || |              | || |              | || |              | |\n" +
            "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n" +
            " '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' \n" +
            "                             .----------------.  .----------------.  .----------------.                                 \n" +
            "                            | .--------------. || .--------------. || .--------------. |                                \n" +
            "                            | |     ______   | || |   _____      | || |     _____    | |                                \n" +
            "                            | |   .' ___  |  | || |  |_   _|     | || |    |_   _|   | |                                \n" +
            "                            | |  / .'   \\_|  | || |    | |       | || |      | |     | |                                \n" +
            "                            | |  | |         | || |    | |   _   | || |      | |     | |                                \n" +
            "                            | |  \\ `.___.'\\  | || |   _| |__/ |  | || |     _| |_    | |                                \n" +
            "                            | |   `._____.'  | || |  |________|  | || |    |_____|   | |                                \n" +
            "                            | |              | || |              | || |              | |                                \n" +
            "                            | '--------------' || '--------------' || '--------------' |                                \n" +
            "                             '----------------'  '----------------'  '----------------'                                 \n";

    private static ParserOptions options = ParserOptions.getInstance();

    public static void start() {
        LOGGER.debug(BANNER);
        LOGGER.debug("Loading default CLI options and descriptions");
        options.addOption("--accesslog", "File location with data to load to database");
        options.addOption("--startDate", "Start date of range. Pattern: \"yyyy-MM-dd.HH:mm:ss\"");
        options.addOption("--duration", "Duration range of query. Example: \"hourly\", \"daily\"");
        options.addOption("--threshold", "Duration range of query");
        options.addOption("--help", "Instructions of use the command line");
    }

    public static void parse(String[] args) {
        CommandLineParser parser = new DefaultParse();
        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption("--help")) {

            LOGGER.debug("Help option passed as parameter to commandline");
            PrintOptionsHelper printHelper = new PrintOptionsHelper();
            printHelper.printHelper("Parser", options);

        } else if (cmd.hasOption("--accesslog")) {

            LOGGER.debug("Access log option passed as parameter to commandline");
            String filePath = String.valueOf(cmd.getValue("--accesslog"));
            new LogFileBusiness(new LogFileLoader(), filePath, new LogRepository()).load("");

        } else if (cmd.hasOption("--startDate")
                && cmd.hasOption("--duration")
                && cmd.hasOption("--threshold")) {

            LOGGER.debug("StartDate, duration and threshold option passed as parameter to commandline");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss");
            LocalDateTime startDate = LocalDateTime.parse(cmd.getValue("--startDate"), formatter);
            Duration duration = Duration.valueOf(cmd.getValue("--duration").toUpperCase());
            Integer threshold = Integer.parseInt(cmd.getValue("--threshold"));
            new LogBusiness(new LogRepository()).findByDateAndThreshold(startDate, duration, threshold);

        } else {
            System.out.println("No options found. Please check the options passing the parameter --help.");
        }

    }

}
