import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CmdLineArgsParser {
    private static Logger log = LoggerFactory.getLogger(CmdLineArgsParser.class);
    public static void parser(String[] args) throws ParseException {
        Options options = new Options();
        Option location = new Option("l", "location", true, "where is the location you want to check eg. \"东区\"");
        location.setRequired(true);
        Option building = new Option("b", "building", true, "which building you want to check eg. \"沁苑12舍\"");
        building.setRequired(true);
        Option roomNumber = new Option("r", "room", true, "which room number you want to check eg. \"201\"");
        roomNumber.setRequired(true);
        Option toMail = new Option("t", "to", true, "which email address you want to receive when elec fee is not enough eg. \"XXX@qq.com\"");
        toMail.setRequired(true);
        Option warningElecValue = new Option("w", "warning", true, "the email will send to you when elec value is less than the waring value, default is 10.0 eg.\"10\"");
        warningElecValue.setRequired(false);
        warningElecValue.setType(Float.class);
        Option help = new Option("h","help",false,"print help message");
        options.addOption(location);
        options.addOption(building);
        options.addOption(roomNumber);
        options.addOption(toMail);
        options.addOption(warningElecValue);
        options.addOption(help);
        CommandLineParser commandLineParser = new DefaultParser();
        HelpFormatter helpFormatter = new HelpFormatter();
        CommandLine cmd = null;
        try {
            cmd = commandLineParser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            helpFormatter.printHelp("utility-name", options);
            System.exit(1);
        }
        if(cmd.hasOption("h")){
            helpFormatter.printHelp("utility-name",options);
            System.exit(1);
        }
        ArgsList.locationStr = cmd.getOptionValue("l");
        ArgsList.buildingStr = cmd.getOptionValue("b");
        ArgsList.roomNumberValue = cmd.getOptionValue("r");
        ArgsList.toMailStr = cmd.getOptionValue("t");
        log.debug("ArgsList.locationStr: {}", ArgsList.locationStr);
        log.debug("ArgsList.buildingStr: {}", ArgsList.buildingStr);
        log.debug("ArgsList.roomNumberValue: {}", ArgsList.roomNumberValue);
        log.debug("ArgsList.toMailStr: {}", ArgsList.toMailStr);
        if (cmd.hasOption("w")) {
            ArgsList.elecValue = Float.valueOf(cmd.getOptionValue("w"));
        } else {
            ArgsList.elecValue = ArgsList.minElecValue;
        }
        log.debug("ArgsList.elecValue: {}", ArgsList.elecValue);
    }

    static class ArgsList {
        static String locationStr;
        static String buildingStr;
        static String roomNumberValue;
        static String toMailStr;
        static float elecValue;
        static float minElecValue = 10.0f;
    }
}
