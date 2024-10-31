import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/ai")
public class AIController {

    @PostMapping("/predict")
    public String getPrediction(@RequestBody String inputData) {
        String prediction = "";
        try {
            ProcessBuilder pb = new ProcessBuilder("python3", "path/to/my_ai_model.py", inputData);
            Process process = pb.start();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
            prediction = output.toString();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prediction;
    }
}
