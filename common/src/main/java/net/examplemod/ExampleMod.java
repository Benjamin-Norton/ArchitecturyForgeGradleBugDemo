package net.examplemod;

import com.theokanning.openai.service.OpenAiService;

public class ExampleMod {
    public static final String MOD_ID = "examplemod";
    
    public static void init() {
        new OpenAiService("anytoken");
    }
}
