package lsh.security.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InfoUtil {

    public static Optional<String> problemDescription(){
        
        try{
            Path path = Path.of("").toAbsolutePath().resolve("problem.txt");
    
            BufferedReader br = new BufferedReader(new FileReader(new File(path.toUri())));
    
            StringBuffer stringBuffer = new StringBuffer();
            
            String arg;
            while((arg = br.readLine()) != null){
                stringBuffer.append(arg).append("<br/>");
            }
            
            br.close();
            return Optional.of(stringBuffer.toString());
        }
        catch(Exception exception){
            log.error("infoUtil -> problemDescription 예외 발생");

            return Optional.empty();
        }
    }
}
