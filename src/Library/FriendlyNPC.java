package Library;

import java.util.HashMap;
import java.util.Map;

public class FriendlyNPC {
    private final String name;
    private final String greeting;
    private final Map<String, String> responses;

    public FriendlyNPC(String name, String greeting) {
        this.name = name;
        this.greeting = greeting;
        this.responses = new HashMap<>();
        initializeResponses();
    }

    private void initializeResponses() {
        responses.put("hello", "Hello there, traveler! How can I assist you today?");
        responses.put("directions", "If you wish to find your way, just follow the paths laid out before you!");
        responses.put("item", "I have some useful items! Perhaps you should ask around.");
        responses.put("help", "I'm here to help. Ask me anything!");
    }

    public String getName() {
        return name;
    }

    public String getGreeting() {
        return greeting;
    }

    public String respond(String input) {
        return responses.getOrDefault(input.toLowerCase(), "I'm sorry, I don't understand that.");
    }
}
