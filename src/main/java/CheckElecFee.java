import com.sun.org.apache.xpath.internal.Arg;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.mail.MessagingException;
import java.io.IOException;

public class CheckElecFee {
    private static Logger log = LoggerFactory.getLogger(CheckElecFee.class);
    public static void main(String[] args) throws ParseException, IOException, MessagingException {
        CmdLineArgsParser.parser(args);

        String elecFee = GetElecFee.getElecFee(CmdLineArgsParser.ArgsList.locationStr,
                CmdLineArgsParser.ArgsList.buildingStr,
                CmdLineArgsParser.ArgsList.roomNumberValue);
        log.debug("elecFee is {}",elecFee);
        if(Float.valueOf(elecFee) <= CmdLineArgsParser.ArgsList.elecValue){
            SendEmail.sendMessage(CmdLineArgsParser.ArgsList.toMailStr, elecFee);
        }
    }


}
