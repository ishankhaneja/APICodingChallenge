package examples.CircuitsAPI;

import com.intuit.karate.junit5.Karate;

class APIRunner {
    
    @Karate.Test
    Karate testUsers() {
        return Karate.run("CircuitsAPI").relativeTo(getClass());
    }    

}
